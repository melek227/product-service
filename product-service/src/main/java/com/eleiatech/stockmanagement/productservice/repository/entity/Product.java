package com.eleiatech.stockmanagement.productservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data  //tostring, equalsandhashcode, getter, setter ve requiredArgsConstructor annotasyonlarının hepsini barındırır
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product") //stock management şemasında product isimli bir tablo oluşturur
public class Product {

    @Id
    @Column(name = "product_id")   //tabloda kolon adı
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //Primary key değerlerinin hangi stratejiyle oluşturulacağını belirler
  	//primary keye değer atanmasından database sorumlu
  	
    
    private long productId; //tablonun primary keyi

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity") //product tablosundaki ürünün miktarını tutar
    private int quantity;

    @Column(name = "price")
    private double price; //ürünlerin fiyatını tutar

    @Builder.Default //bu alanı constructorda default olarak her zaman parametre olarak geçer
    @Column(name = "product_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productUpdatedDate = new Date(); //kaydın en son ne zaman güncellendiğini tutar

    @Builder.Default
    @Column(name = "product_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productCreatedDate = new Date(); //tabloda kaydın ne zaman oluşturulduğunu

    @Column(name = "is_deleted")
    private boolean deleted;  //tabloda soft delete yapmamızı sağlar


}
