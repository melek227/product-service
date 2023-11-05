package com.eleiatech.stockmanagement.productservice.service;

import com.eleiatech.stockmanagement.productservice.enums.Language;

import com.eleiatech.stockmanagement.productservice.repository.entity.Product;
import com.eleiatech.stockmanagement.productservice.request.ProductCreateRequest;
import com.eleiatech.stockmanagement.productservice.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {

	//Servis yeni bir kayıt oluşturabileceği için create metodunu tanımladık
	//Geriye product döner
    Product createProduct(Language language, ProductCreateRequest productCreateRequest);
    

    //Servisin istenilen datayı göstermesini sağlar.
    Product getProduct(Language language, Long productId);
    

    //Tüm kayıtları dönecek metodu tanımladık
    List<Product> getProducts(Language language);

    
    //Eklenen kayıtları güncellemeyi sağlar
    Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest);

    //Eklenen kayıtları silmeyi sağlar
    Product deleteProduct(Language language, Long productId);

}
