package com.eleiatech.stockmanagement.productservice.repository;

import com.eleiatech.stockmanagement.productservice.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	/*JpaRepository Spring Data jpa'dan verileri sorgulamak için ek yöntemlerle birlikte birkaç 
	 CRUD (Oluştur, Oku, Güncelle, Sil) yöntemini devralır.
     */
    Product getByProductIdAndDeletedFalse(Long productId);
    /*
     ----"getBy" öneki, yöntemin veri almak için kullanıldığını belirtir.
     ----Yöntem adının geri kalanı, verileri filtrelemek için gereken ölçütleri gösterir.
     ----yöntem bir Ürün varlığını productId değerine göre alır.
     ---- DeletedFalse kısmı yalnızca deleted özelliği false olarak ayarlanmış ürünlerin döndürülmesini sağlar.
     
     */

    List<Product> getAllByDeletedFalse();
    
    /*
     * isDeleted alanı false olan bütün dataları databaseden çeker
     */
    
}

/*
 * Spring repository  CRUD işlemlerini sağlayan interfacedir
 * Repository interfacesi OOP de abstractiona örnektir
 * repository interfacesini CRUD Repository, JPA Repository ve PagingandsortingRepository
 olarak 3 farklı şekilde extend edebiliriz
 *CRUD Repository CRUD işlemlerini sağlar yani save, saveall, findbyid, findall, delete 
 gibi metotlar barındırır
 *PagingAndSortingRepository pagination ve kayıtları sıralama işlemleri içerir.
 İçerisinde findByAll metotları bulunur.
 *JPA Repository ise CRUD Repository, paginationAndSortingRepository nin içerdiği tüm işlemleri içinde barındırır
 
 
 */
