package com.eleiatech.stockmanagement.productservice.exception.exceptions;

import com.eleiatech.stockmanagement.productservice.enums.Language;

import com.eleiatech.stockmanagement.productservice.exception.enums.IFriendlyMessageCode;
import com.eleiatech.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j //Classın içine log satırı ekleyeceğimiz için 
@Getter
public class ProductNotCreatedException extends RuntimeException {
//Servisimizde producta gönderdiğimiz requestlerin eklenememe durumu unCheckedException olduğu için classı RunTimeException ile extend ettik
	
    private final Language language;
    //Çoklu dil desteğini sağlaması için tanımladığımız yapı
    
    private final IFriendlyMessageCode friendlyMessageCode;
    //

    public ProductNotCreatedException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
    	//Constructorda initialize edilen değişkenlere ek olarak String message ekledik
    	
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        //Super keywordu ile miras alınan üst classın constructoruna ulaştık
        //RunTimeExceptiona mesajımızı gönderdik
        
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        
        //mesaj hata aldığımızda çalışacağı için error logu ekledik
        log.error("[ProductNotCreatedException] -> message: {} developer message: {}", FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode), message);
        //message developer mesajıdır
    
    }
}
