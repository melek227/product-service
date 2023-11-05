package com.eleiatech.stockmanagement.productservice.exception.utils;

import com.eleiatech.stockmanagement.productservice.enums.Language;

import com.eleiatech.stockmanagement.productservice.exception.enums.IFriendlyMessageCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 *Bu calassın içinde dile göre FriendlyMessageBundleden mesajımızı çağıracağız

 */

/*================LOGLAMA======================
 * uygulamanın çalışma zamanında bilgileri, hataları ve diğer durumları kaydetmek için kullanılır.
 * uygulamanın nasıl çalıştığını izleme ve sorunları teşhis etme konusunda yardımcı olur.
 * log.debug(...): Hata ayıklama amacıyla kullanılır ve en düşük günlükleme düzeyidir.
 * log.info(...): Uygulamanın normal işleyişi hakkında bilgi vermek için kullanılır.
 * log.warn(...): Uygulamanın hala çalıştığı ancak potansiyel sorunlar veya uyarılar içerdiği durumlarda
 kullanılır.
 * log.error(...): Hata ve istisnaları kaydetmek için kullanılır.
 */



@Slf4j //Hata verdiği blokta log ekleyeceğiz
@UtilityClass  //class final olarak işaretlenir.Classtaki bütün metotlar fieldler inner classlar statik olarak işaretlenir
public class FriendlyMessageUtils {

    private static final String RESOURCE_BUNDLE_NAME = "FriendlyMessage"; //resource bundlenin ismini girdik
    private static final String SPECIAL_CHARACTER = "__"; //paternde bulunan __

    public static String getFriendlyMessage(Language language, IFriendlyMessageCode messageCode) {
        String messageKey = null;
        try {
            Locale locale = new Locale(language.name());
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
          //Hangi resource bundlenin seçileceğini belirledik
			//frinedlyMessage__TR mi yoksa friendlyMessage__en mi
            
            
            messageKey = messageCode.getClass().getSimpleName()  //buradan friendlyMessagecode geşir
            		+ SPECIAL_CHARACTER 
            		+ messageCode;   //burada friendlyMesssageCode __OK gibi bir string oluşturulur
            return resourceBundle.getString(messageKey); //friendlyMesssageCode __OK değerini döndürür
            
            
        } catch (MissingResourceException missingResourceException) {
            log.error("Friendly message not found for key: {}", messageKey);
            return null;
        }
    }
}
