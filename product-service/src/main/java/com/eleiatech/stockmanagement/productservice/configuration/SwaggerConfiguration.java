package com.eleiatech.stockmanagement.productservice.configuration;

import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eleiatech"))
                .paths(PathSelectors.any())
                .build();
      //Select metodu Swagger tarafından sunulan endpointleri kontrol etmeyi sağlayan API Selecter Builder instancesini return eder
        //apis metodu API leri belirtilen paket altında aramasını sağlar
		//paths metodu tanımladığımız paket altındaki tüm API dökümantasyonunu oluşturur
		
        
    }

}

/*
Swagger rest API dökümanatasyon tooludur
Projeye swagger konfigurasyonu yaptığımızda swagger API bilgilerinin olduğu standart bir dökümantasyon hazırlar
Swagger classının bir configurstion classı olduğunu belirtmek için @Configuration annotasyonu eklenir
@Configuration  //Classın configuration classı olduğunu belirtmek için kullanılır

---web servis geliştirdiğimizde bu servisi kullanabilmek için bir dökümantasyon istenir
Geliştirdiğimiz servisin alacağı request formatı, response formatı, kullanılacak authentication yöntemi,
 serviste bulunan API metotları gibi bilgiler içerir.
----

 */



