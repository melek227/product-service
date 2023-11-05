package com.eleiatech.stockmanagement.productservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
//Response da görmek istediğimiz alanları ekleriz
	
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    
    //response u setlerken controllerde gelen değerin gettime metodunu kullanacağımız için long türündeler
    private Long productCreatedDate;
    private Long productUpdatedDate;
}
