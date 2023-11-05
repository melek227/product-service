package com.eleiatech.stockmanagement.productservice.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
	//Classa productId eklemememizin sebebi productId yi otomatik artacak şekilde tanımlamamız

    private String productName;
    private Integer quantity;
    private Double price;
}
