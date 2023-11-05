package com.eleiatech.stockmanagement.productservice.request;

import lombok.Data;

@Data
public class ProductUpdateRequest {
	
	
    private Long productId; //Kayda ıd değerinden ulaşıp güncellemek için
    private String productName;
    private Integer quantity;
    private Double price;
}
