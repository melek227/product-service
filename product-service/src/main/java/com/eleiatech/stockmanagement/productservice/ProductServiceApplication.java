package com.eleiatech.stockmanagement.productservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

@EnableEurekaClient 
//servisin eureka client olarak yapılandırılmasını sağlar.
//Böylece eureka-servere bağlanabilecek ve eureka veritabanında kayıtlı olmayı sağlar
//Uygulamanın diğer eureka clientleri tarafından keşfedilebilir hale gelmesini sağlar
//Bu clientler arasında bilgi paylaşımı yapılabilir

@EnabelDiscoveryClient 
//springboot uygulamasının bir eureka servera bağlanarak servislerin keşfedilmesine ve yönetilmesine izin verir
//uygulamanın diğer servisler taraından keşfedilebilir hale gelmesini sağlar
//Servisler arasında iletişim kurulmasını ve birbirlerine bağlı olarak çalışmasını sağlar


@EnableFeignClient
//Springboot uygulamalarında feign clientlerin kullanılmasını sağlar
//feign clientleri yapılandırır ve spring contextine dahil eder
//Böylece diğer servislerle feign client olarak iletişime açılır


public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}

