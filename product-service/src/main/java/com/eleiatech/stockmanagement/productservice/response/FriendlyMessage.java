package com.eleiatech.stockmanagement.productservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendlyMessage {
	/*ExceptionHandling yaparken yada controllerde success mesajlarını belirtirken
	InternalApiResponse classının içinde kullanılacak model class
	*/
	
	//title error success gibi mesaj titlelerini içerir
    private String title;
    
    //productNotCreated, productNotSuccessfully gibi hatanın veya başarılı olayın açıklamasını yapar
    private String description;
    
    //UI tarafında kullanılır
    //Butonun durumunu belirler
    private String buttonPositive;
}
