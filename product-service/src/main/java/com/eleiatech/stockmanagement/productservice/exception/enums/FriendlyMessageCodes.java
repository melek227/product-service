package com.eleiatech.stockmanagement.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {
    OK(1000),
	ERROR(1001),
	PRODUCT_NOT_CREATED_EXCEPTİON(1500);
	
	
	/*metod integer return eder.Bizim girdiğimiz value de integer.
	 * Bu value yı return etmek için final değişken tanımladık
	*/
	
    private final int value;
    
  //değişken final olduğu için initialize etmemiz gerekiyor.
  	//initialize işlemini constructorda yaptık

    FriendlyMessageCodes(int value) {
        this.value = value;
    }

  //override metodunun üzerine noktalı virgül koyar
  	//anlamı bu bir enum.Enum tanımlarını yapmalısın
  	//mesaj kodlarını yazarız
    
    @Override
    public int getFriendlyMessageCode() {
        return value;
      //enumda tanımladığımız değerlere ulaşabiliriz
    }
}
