package com.eleiatech.stockmanagement.productservice.controller;

import com.eleiatech.stockmanagement.productservice.enums.Language;

import com.eleiatech.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.eleiatech.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.eleiatech.stockmanagement.productservice.repository.entity.Product;
import com.eleiatech.stockmanagement.productservice.request.ProductCreateRequest;
import com.eleiatech.stockmanagement.productservice.request.ProductUpdateRequest;
import com.eleiatech.stockmanagement.productservice.response.FriendlyMessage;
import com.eleiatech.stockmanagement.productservice.response.InternalApiResponse;
import com.eleiatech.stockmanagement.productservice.response.ProductResponse;
import com.eleiatech.stockmanagement.productservice.service.IProductRepositoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j//log eklemek için tanımladık

@RestController
//java classının bir restful web servisi olarak işlev gördüğünü belirtir.
//Sınıfın içinde bulunan tüm metotların HTTP isteklerine cevap verebilecek şekilde yapılandırıldığını gösterir.
//Bu annotasyonu eklediğimizde servisimizin typei json olur
//yani request ve response jsondur

//proje mikroservis değil monolitik yapıda olsaydı birden fazla controller kullanabilirdik.
//Diğer controller için farklı requestMappingler vermemiz gerekirdi.
//Yani product yerine kategori gibi tekil isimler verilir


@RequestMapping(value = "/api/1.0/product")
//Springboot web uygulamalarında bir url e istek yapıldığında o isteği nasıl işleyeceğini belirtmek için kullanılır.
//Bu annotasyon sayesinde belirtilen url e yapılan isteğe bir controller bağlayabiliriz
//1.0 yazdığımız API versiyonudur.



@RequiredArgsConstructor

/*Controller classı springboot mikroservis mimarilerili projelerde bir web servisin istekleri alıp cevapları döndürmesini
sağlar.Bu classta web servisin gerçekleştirceği işlemleri yöneten metotlar tanımlanır
*/
class ProductController {
	
    private final IProductRepositoryService productRepositoryService;//Servisi ekledik

    
    
    
    @ResponseStatus(HttpStatus.CREATED)
    //web servisin döndürdüğü yanıtın http durum kodunu belirtmek için kullanılır
    //HTTP durum kodları bir web isteğinin sonucunu belirtir ve istek yapan uygulamanın bu sonucu anlamasına yardımcı olur
    /*Örneğin bu metot product kaydetme işlemini gerçekleştirecek.
    Bu işlem başarılı şekilde sonuçlandırıldığında web servisin responsestatus annotasyonu kullanarak döndüreceği
    yanıt 201 Created HTTP durum kodunu belirtir
    */
    //İstek yapan uygulama bu yanıtı aldıktan sonra productun başarılı şekilde kaydedildiğini anlar
    
    
    
    //------HTTPStatus durum kodları-----//
    /*

     --> 100 - Continue (Devam Et): İstemci, isteğini gönderdi ve sunucu, işlemi devam ettirmesi gerektiğini bildirir.

     --> 200 - OK (Başarılı): İstek başarıyla işlendi ve sonuçlar geri döndü.

     --> 201 - Created (Oluşturuldu): İstek sonucunda yeni bir kaynak başarıyla oluşturuldu.

     --> 204 - No Content (İçerik Yok): İstek başarılıydı, ancak yanıtta içerik yok.

     --> 400 - Bad Request (Geçersiz İstek): İstek hatalı veya geçersiz formatlı.

     --> 401 - Unauthorized (Yetkisiz): İstek yetkilendirme gerektiriyor ve kullanıcı yetkilendirilmemiş.

     --> 403 - Forbidden (Yasak): İstek reddedildi, çünkü kaynağa erişim izni yok.

     --> 404 - Not Found (Bulunamadı): İstek yapılan kaynak bulunamadı.

     --> 500 - Internal Server Error (Sunucu Hatası): Sunucu bir işlemi tamamlarken bir hata meydana geldi.

     --> 502 - Bad Gateway (Kötü Geçit): Sunucu, başka bir sunucudan yanıt alırken hatalı bir yanıt aldı.

     --> 503 - Service Unavailable (Hizmet Kullanılamaz): Sunucu şu anki isteği işlemek için kullanılamaz durumda.

     --> 504 - Gateway Timeout (Geçit Zaman Aşımı): Sunucu, başka bir sunucudan yanıt alırken zaman aşımına uğradı.
    
     */
    
    
    @PostMapping(value = "/{language}/create")
    //bir web servisinin bir url e yapılan http post isteklerini algılamasını ve işlemesini sağlar
    //Requestmapping annotasyonunun bir alt sınıfıdır ve aynı özelliklere sahip
    //web servis metotlarına eklenerek bu metotların bir url e yapılan post isteklerini algılamasını ve bu istekleri işlemesini sağlar

    
    
 
    
    public InternalApiResponse<ProductResponse> createProduct(
    //metodumuzu tanımladık
    //Metotun bir tane path variable annotasyonlu parametresi olur.
    		
    		
    		
       @PathVariable("language") Language language,
        //Pathvariable annotasyonu bir web servisinin url inde bulunan değişkenleri algılamasını ve bu değişkenlere ulaşabilmesini sağlar.
		//PathVariable olarak verdiğimiz parametreleri süslü parantezle url e ekleriz
		//PathVariable annotasyonu içinde tırnak içinde parametre verilmesinin sebebi bu annotasyon sayesinde web servisinin url inde bulunan değişkenlerin değerlerine ulaşılır
		//Tırnak içindeki isimle url de geçen isim aynı olmalıdır
		//Yazdığımız mikroservis çoklu dil desteklediği için tüm controller metotlarımıza pathvariable olarak language i default ekleriz

       
       @RequestBody ProductCreateRequest productCreateRequest
       //RequestBody annotasyonu bir web servise yapılan isteklerin içeriğinin algılanmasını ve işlenmesini sağlar.
       //İstekte bulunulan yada bir uygulama tarafından gönderilen veriler alınır ve web servisin işlemesi için kullanılabilir
       
       
       ){
    	
           log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
           
           /*
           -----log.debug(...): Bu kod, günlükleme düzeyini temsil eder ve genellikle hata ayıklama veya 
           geliştirme sırasında kullanılır.
           -----[{}][createProduct] -> request: {}": Bu bir formatlı günlükleme ifadesidir.
           -----{} yer tutucular, sonraki argümanlarla yer değiştirecek değerlerin yerini tutar.
           ----İlk {} içinde this.getClass().getSimpleName() ifadesi, sınıfın adını alır 
           ----ikinci {} içinde productCreateRequest ifadesi, productCreateRequest değişkeninin değerini alır.
           ----this.getClass().getSimpleName(): Bu ifade, kodun bulunduğu sınıfın adını alır. 
           ----getClass() sınıfın sınıf nesnesini alır ve getSimpleName() ise bu sınıf nesnesinin adını döndürür.
           ----getClass() sınıfın sınıf nesnesini alır ve getSimpleName() ise bu sınıf nesnesinin adını döndürür.
           ----Sonuç olarak, bu kod bloğu, düşük düzeyde bir günlükleme ifadesini kullanarak sınıfın adını ve
            productCreateRequest nesnesinin içeriğini içeren bir günlük kaydı oluşturur.
            ----kodun hata ayıklama veya geliştirme sırasında çalışırken ne tür isteklerin yapıldığını gösterir
            */
           
           
           Product product = productRepositoryServiceImpl.createProduct(language, productCreateRequest);
           //servicesImpl classındaki createProduct endpointi
           
           /*
           ---Yeni bir Product ürünü oluşturulur ve veritabanına erişim ve depolama işlemleri gerçekleşir
           ----ürünün dili veya lokalizasyonu ile ilgili bilgileri nesne oluşturma fonksiyonuna parametre
            olarak verilir.
           ----productCreateRequest, yeni ürünün oluşturulmasında kullanılacak olan bilgileri içeren bir 
            istek nesnesidir.
            
            */
           
           
           ProductResponse productResponse = convertProductResponse(product);
           //convertProductResponse ProductResponse de alanları setleme işlemini yapar
           /* Alanları setleme işlemini diğer metotlarda da kullanacağımız için extract 
           method a tıklayıp yeni bir metot oluşturup convertProductResponse ismini verdik */
           
           
           
           log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
           //kodun hata ayıklama veya geliştirme sırasında çalışırken karşılaştığı isteklere döndürdüğü cevap
           
           
           
           return InternalApiResponse.<ProductResponse>builder()
           //bir ürün oluşturma işlemi için başarılı bir işlemi temsil eden bir yanıt nesnesi oluşturuyor.
           //bir mesaj, HTTP durum kodu ve ürün yanıt verilerini içerir.
           //API yanıtlarını oluşturmak ve çeşitli veriler ve durum kodları ile yanıtlar oluşturmak için kullanılır
          		   
                .friendlyMessage(FriendlyMessage.builder()
                //mesajın başlığını ve açıklamasını içerir	
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS)) //Başlık SUCCESS
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        //Açıklama ürün başarıyla oluşturuldu
                        .build())
                
                .httpStatus(HttpStatus.CREATED)
                //HTTP durum kodu tanımlandı.Kaynağın başarılı şekilde oluşturduğunu ifade eder
                .hasError(false)
                //Yanıtın bir hata yanıtı olmadığını göstermek için hasError bayrağı false olarak ayarlandı
                .payload(productResponse)
                //Yanıtın veri yükü ayarlandı
                .build();

    }
    
    
    
    
    
 //----------------TEK KAYIT İÇİN ALANLARI SETLEMEK İÇİN TANIMLANAN FONKSİYON-------------------------//
    
    
    private ProductResponse convertProductResponse(Product product) {
    	
    //Product nesnesini alıp productResponse nesnesi döndüren metot
    	
    	
    	
    	
        return ProductResponse.builder()
        		
        //ProductResponse sınıfının bir örnek oluşturmak için kullanılan bir builder nesnesini başlatır.
        // Builder tasarım deseni, nesne oluşturmayı kolaylaştırmak ve isteğe bağlı özellikleri ayarlar.		
        		
        		//Product nesnesinin ıdsi adı niteliği fiyatı oluşturma ve güncelleme tarihi ayarlandı
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                
                
                .build();
                //Builder nesnesini kullanarak ProductResponse nesnesini oluşturur
                //Dönüştürülen verileri içerir.
    }
    
    
    //--------BİRDEN FAZLA KAYIT İÇİN ALANLARI SETLEYEN FONKSİYON-------------------//
    
    //deleted false olan kayıtlardan dönecek değer list olduğu için convertProductResponseList metodu tanımladık
    
    
    private List<ProductResponse> convertProductResponseList(List<Product> productList) {
    //productlist adlı parametre olarak verilen liste içindeki ürünleri productresponse sınıfına dönüştürür
    	
    	
        return productList.stream()
        //stream productList içindeki Product nesnelerini işlemek için kullanılır
        //liste içindeki her bir eleman bir periyot halinde gezilir
        		
                .map(arg -> ProductResponse.builder()
                //map metodu her Product nesnesini bir ProductResponse nesnesine dönüştürmek için kullanılır
                // Lambda ifadesi (arg -> ...) ile her bir Product nesnesi ProductResponse nesnesine dönüştürülür
                //Product nesnesinin özellikleri, ProductResponse nesnesinin özelliklerine kopyalanır.
                		
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .quantity(arg.getQuantity())
                        .price(arg.getPrice())
                        .productCreatedDate(arg.getProductCreatedDate().getTime())
                        .productUpdatedDate(arg.getProductUpdatedDate().getTime())
                        .build())
                
                .collect(Collectors.toList());
                //.collect(Collectors.toList()) Stream içinde bulunan tüm ProductResponse nesnelerini bir liste olarak toplar ve geri döndürür.
   
    }
    
    
    
    
    
    
    //---------------------PRODUCT NESNESİNİ ALMAK İÇİN FONKSİYON--------------------// 

    
    @ResponseStatus(HttpStatus.OK)
    //200 OKEY anlamındadır.Get isteği olduğu için OK kullandık
    
    @GetMapping(value = "/{language}/get/{productId}")
    
    
    
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language") Language language,
                                                           @PathVariable("productId") Long productId) {
    	
        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        
        Product product = productRepositoryServiceImpl.getProduct(language, productId);
        
        
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        
        
        return InternalApiResponse.<ProductResponse>builder()
        		//get metodu olduğu için FriendlyMessage setlemesi yapmadık
        		//get metodu datalarda değişiklik yapmayacağı için setlemedik
        		
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    
    
    
    
    //--------------------PRODUCT NESNESİNİ GÜNCELLEMEK İÇİN FONKSİYON----------------//
    
    
    @ResponseStatus(HttpStatus.OK)
    //Bu metodun ürün güncellediği ve güncelleme işleminin başarılı olduğunu varsayıyoruz
    
    @PutMapping(value = "/{language}/update/{productId}")
    //putMapping annotasyonu bir HTTP put isteğine yanıt verecek metodu belirtir.
    //put isteği bir kaynak üzerinde bir güncelleme gerçekleştirir
    
    
    public InternalApiResponse<ProductResponse> updateProduct(
    		                                                  @PathVariable("language") Language language,
                                                              @PathVariable("productId") Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest
                                                              ) {
    	
    	
        log.debug("[{}][updateProduct] -> request: {} {}", this.getClass().getSimpleName(), productId, productUpdateRequest);
        
        Product product = productRepositoryService.updateProduct(language, productId, productUpdateRequest);
        
        ProductResponse productResponse = convertProductResponse(product);
        
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        
        
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    
    
    //------------------------DELETED FALSE OLAN BÜTÜN KAYITLARI DÖNDÜRMEK İÇİN---------------------------------------//
    
    @ApiOperation(value = "This endpoint get all product.")
    //swagger apı belgeleme aracıdır bir resAPI işleminin açıklamasını ve özelliklerini belirtmek için kullanılır.
    //Bu endpointin databasedeki tüm kayıtları döndüreceği bilgisini ekledik
    //Diğer endpointler için de eklenebilirdi
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/products")
    
    
    public InternalApiResponse<List<ProductResponse>> getProducts(@PathVariable("language") Language language){
    	
        log.debug("[{}][getProducts]", this.getClass().getSimpleName());
        
        List<Product> products = productRepositoryService.getProducts(language);
        
        List<ProductResponse> productResponses = convertProductResponseList(products);
        //dönecek değer list olduğu için convertProductResponseList metodu tanımladık
        
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }

    
    
    
    //----------------------PRODUCT NESNESİNİ SİLMEK İÇİN FONKSİYON-----------------------//
    
    
    @ResponseStatus(HttpStatus.OK)
    //Delete işleminin gerçekleştiğini varsayarız
    
    @DeleteMapping(value = "/{language}/delete/{productId}")
    //Delete isteklerini yönlendirmek için kullanılır
    //RequestMapping annotasyonunun bir varyantıdır
    
   
    
    public InternalApiResponse<ProductResponse> deleteProduct(
    		                                                  @PathVariable("language") Language language,
                                                              @PathVariable("productId") Long productId) {
    	
        log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        
        Product product = productRepositoryService.deleteProduct(language, productId);
        
        ProductResponse productResponse = convertProductResponse(product);
        
        log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        
        
        return InternalApiResponse.<ProductResponse>builder()
        		
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

     

}
