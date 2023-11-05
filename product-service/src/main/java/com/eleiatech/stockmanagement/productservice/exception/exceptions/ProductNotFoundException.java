package com.eleiatech.stockmanagement.productservice.exception.exceptions;

import com.eleiatech.stockmanagement.productservice.enums.Language;
import com.eleiatech.stockmanagement.productservice.exception.enums.IFriendlyMessageCode;
import com.eleiatech.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter



//Get product endpointi bir web API hizmetinde belirli bir ürünün bilgilerini almak için kullanılan HTTP isteğidir
//Get product endpointi için oluşabilecek hata productun bulunamaması olabilir

public class ProductNotFoundException extends RuntimeException {
    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;

    public ProductNotFoundException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[ProductNotFoundException] -> message:{} developer message: {}", FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode), message);
    }
}
