package com.eleiatech.stockmanagement.productservice.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse<T> {
//RestApisi için tek tipte response yapısı oluşturduk.Diğer responseler için de kullanabiliriz
//Response, hata mesajı, success mesajı, httpstatus gibi alanları barındırır.
	
	
	
	private FriendlyMessage friendlyMessage; 
	//kullanılacak response
	//Burada mesajın title ve descriptionu bulunur
	
	
    private T payload;

   //Generic classın tipi
   //payload alanına response verir
   //Response her controller için değişebilir.Bu sebeple tür olarak T dedik
   //İstediğimiz her tipte data verebiliriz.

    
    private boolean hasError;
    //Error oluşup oluşmadığını tutan alan
    
    private List<String> errorMessages;
    //Errorlar hakkında bilgi verir
    
    private HttpStatus httpStatus;
    //notFound, addRequest, OK, Created gibi httpstatuslarını tutar
    

}
