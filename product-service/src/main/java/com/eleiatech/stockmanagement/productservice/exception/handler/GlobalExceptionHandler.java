package com.eleiatech.stockmanagement.productservice.exception.handler;

import com.eleiatech.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.eleiatech.stockmanagement.productservice.exception.exceptions.ProductAlreadyDeletedException;
import com.eleiatech.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.eleiatech.stockmanagement.productservice.exception.exceptions.ProductNotFoundException;
import com.eleiatech.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.eleiatech.stockmanagement.productservice.response.FriendlyMessage;
import com.eleiatech.stockmanagement.productservice.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice //ExceptionHandling yapabilmek için ekledik

public class GlobalExceptionHandler {
//Exceptionları tek bir hata componentinde birleştirmemize olanak sağkar

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //Productun ortada bir request varken oluşturulamaması BAD_REQUEST
    //Requestin uygun olarak döndürülmemesine BAD_REQUEST denir
    
    @ExceptionHandler(ProductNotCreatedException.class)
    //Exception Handlerleri tek bir çatı altında birleştirdiğimizi gösterir
    
    
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception) {
    	
  
        return InternalApiResponse.<String>builder()
        		//Gerekli alanları setleriz
        		
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        //FriendlyMessageCodes.ERROR tanımlanan enum
                        
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        //Eğer bir product create edilemezse masaj içeriğini yaz
                        
                        
                        .build())
                      //-------------FRİENDLYMESSAGE AİT BUİLDER----//
                
                
                //--------------APIRESPONSE SETLEMEYE DEVAM EDİYORUZ-----//
                .httpStatus(HttpStatus.BAD_REQUEST)   
                .hasError(true)
                .errorMessages(Collections.singletonList(exception.getMessage()))
                //Burada collection singleton list kullandığımız için gelen liste değiştirilemez
                //Liste singleton hale gelir
                //Mesaj liste değilde string olduğu için singletonListi kullandık
                //Singletonlist generic type alır.Stringi de kabul eder
                .build();
    }
    
    
    

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    
    public InternalApiResponse<String> handleProductNotFoundException(ProductNotFoundException exception) {
    	
        return InternalApiResponse.<String>builder()
        		
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                
                .httpStatus(HttpStatus.NOT_FOUND) //İstekte bulunulan kaynağın bulunamadığını gösterir
                .hasError(true)
                .errorMessages(Collections.singletonList(exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //Kayıt önceden silindiği için silme işlemi geçersiz olacak
    
    @ExceptionHandler(ProductAlreadyDeletedException.class)
    
    
    public InternalApiResponse<String> handleProductAlreadyDeletedException(ProductAlreadyDeletedException exception) {
    	
        return InternalApiResponse.<String>builder()
        		
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(exception.getMessage()))
                .build();
    }
}
