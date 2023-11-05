package com.eleiatech.stockmanagement.productservice.service.impl;

import com.eleiatech.stockmanagement.productservice.enums.Language;
import com.eleiatech.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.eleiatech.stockmanagement.productservice.exception.exceptions.ProductAlreadyDeletedException;
import com.eleiatech.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.eleiatech.stockmanagement.productservice.exception.exceptions.ProductNotFoundException;
import com.eleiatech.stockmanagement.productservice.repository.ProductRepository;
import com.eleiatech.stockmanagement.productservice.repository.entity.Product;
import com.eleiatech.stockmanagement.productservice.request.ProductCreateRequest;
import com.eleiatech.stockmanagement.productservice.request.ProductUpdateRequest;
import com.eleiatech.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j //oluşan hataları terminalden izleyebilmek için log ekleriz

/*
 5 tane SLF4J log tipi var:
   * Trace En ayrıntılı log seviyesidir ve uygulama içindeki işlemlerin adım adım izlenmesi gerektiğinde
   kullanılır. Genellikle geliştirme ve hata ayıklama aşamalarında kullanılır.
   
   * Debug Uygulamanın geliştirme ve hata ayıklama süreçlerinde kullanılır.
    DEBUG logları, uygulama içindeki önemli adımları ve değişken değerlerini gösterir.
    
   * İnfo Genel bilgi amaçlı kullanılır ve uygulamanın normal çalışma durumlarını belgelemek için kullanılır. 
   Bu tür loglar, uygulamanın nasıl çalıştığını ve hangi işlemlerin gerçekleştiğini gösterir.
   
   * Warn Uygulamanın çalışmasını engellemeyen ancak dikkat edilmesi gereken durumları işaret eder. 
   Uygulamada potansiyel sorunlara işaret eden bu tür loglar kullanılır. 
   
   * Error Uygulamanın normal çalışmasını engelleyen hataları işaret eder.
   Hata logları, uygulamanın çökmesine veya istenmeyen sonuçlara neden olan sorunları işaret eder.
   
 */



@Service //Bu classta bussiness logiclerin bulunduğunu belli eder
@RequiredArgsConstructor //final class variable ı constructorda initialize etmek için ekledik

/*
--interface tanımlayıp implemente etme sebebimiz
    * controller tanımladığımızda bu metotlara interface ile ulaşacağız 
    * yeni soyutlama yapmış olacağız
    * Böylece controllerde services interfacesini kullanırken ımpl classında metot gövdelerine
    yazdıklarımızı gizlemiş oluruz
*/

public class ProductRepositoryServiceImpl implements IProductRepositoryService {
	
    private final ProductRepository productRepository;

    @Override
    
    
    //--------------------------------cretaProduct ENDPOİNTİ-------------------//
    
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {
    //Tabloya yeni kayıt eklemek için oluşturduğumuz metot	
    	
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        //loga classın ismi metodun ismi ve request içeriğini yazdık
        //metotda sorun olursa metoda girdiğini ve requestin ne olduğunu logdan okuyabiliriz
        
        
        try {
            Product product = Product.builder() //Product nesnesini tanımladık.
            		//İlgili alanları set edeceğiz.
   
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            
            Product productResponse = productRepository.save(product);
            //Repositorye verdiğimiz dataları databaseye kaydedecek
            
            
            log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
            
        } catch (Exception exception) {
        	
        	//Oluşturduğumuz özel exceptionu fırlattık
            throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION, "product request: " + productCreateRequest.toString());
        }
    }

    
    
    //---------------------getProduct ENDPOİNTİ----------------------//
    
    @Override
    public Product getProduct(Language language, Long productId) {
    	
        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        //Hata oluşursa hata oluşan nesneyi loglarda görebilmemiz için metodun ismi ve gelen parametreyi log satırına yazarız
        
        Product product = productRepository.getByProductIdAndDeletedFalse(productId);
        //getByProductIdAndDeletedFalse silinmeyen ürünlerin idsi
        //productRepository nesnesinden ürünü sorgular
        //productId ile eşleşen ürünü döndürür
        
        if (Objects.isNull(product)) {
        //Sorgu sonucunda hiç bir ürün döndürülmezse oluşturduğumuz ProductNotFoundExceptionu fırlatırız
        //eklenen ürün bulunamadı mesajını gösteririz
            throw new ProductNotFoundException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Product not found for product id: " + productId);
        }
        
        
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), product);
        //metot çağırıldığında metodun çalıştığı ve ürünün sorgulandığı bilgisini gösterir
        
        return product;
    }
    
    
    //-------------------GET ALL PRODUCTS ENDPOİNTİ-------------------------//
    
 
    @Override
    public List<Product> getProducts(Language language) {
    	
        log.debug("[{}][getProducts]", this.getClass().getSimpleName());
        
        List<Product> products = productRepository.getAllByDeletedFalse();
        //databasedeki tüm deletedFalse olan kayıtları görebilmeyi sağlar
        //Birden fazla kayıt olabileceği için liste tanımladık
        
        
        //Product nesnesinin boş olma durumuna göre hata döndürülecek
        if (products.isEmpty()) {
        	
            throw new ProductNotFoundException(language, FriendlyMessageCodes.PRODUCT_NOT_FOUND_EXCEPTION, "Products not found");
        }
        
        
        
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), products);
        return products;
    }
    
    
    
    
    //--------------------------------updateProduct ENDPOİNTİ--------------------//

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
    	
        log.debug("[{}][updateProduct] -> request: {} {}", this.getClass().getSimpleName(), productId, productUpdateRequest);
        
        Product product = getProduct(language, productId);
        
        product.setProductName(productUpdateRequest.getProductName());
        product.setQuantity(productUpdateRequest.getQuantity());
        product.setPrice(productUpdateRequest.getPrice());
        product.setProductCreatedDate(product.getProductCreatedDate());
        product.setProductUpdatedDate(new Date());
        Product productResponse = productRepository.save(product);
        
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return productResponse;
    }

    
    
    
    //------------------------------------------------deleteProduct Endpointi--------------------//
    
    @Override
    public Product deleteProduct(Language language, Long productId) {
        log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product;
        try {
        	
        	//silme işlemi soft deleted olarak yapılacak yani sadece deleted alanı true olarak değiştirilecek
        	//silinmiş kaydı tekrar silmeye çalışabiliriz.Bunu engellemek için productAlreadyDeleted exceptionu tanımlarız
            product = getProduct(language, productId);
            product.setDeleted(true);
            product.setProductUpdatedDate(new Date());
            Product productResponse = productRepository.save(product);
            
            log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
            
            return productResponse;
            
        } catch (ProductNotFoundException productNotFoundException) {
          //Kaydın önceden silinmiş olma durumuna karşı tanımladığımız özel hatayı fırlatıyoruz
        	
            throw new ProductAlreadyDeletedException(language, FriendlyMessageCodes.PRODUCT_ALREADY_DELETED, "Product already deleted product id: " + productId);
        }
    }
}
