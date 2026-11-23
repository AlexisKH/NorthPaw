package com.northpaw;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {

    boolean existsBySku(String sku);

    boolean existsByBarcode(String barcode);

   
    @Query("SELECT v FROM ItemVariant v WHERE (:brandId IS NULL OR v.item.brand.id = :brandId) "
         + "AND (:petTypeId IS NULL OR v.item.petType.id = :petTypeId) "
         + "AND (:q IS NULL OR LOWER(v.item.name) LIKE :q OR LOWER(v.sku) LIKE :q "
         + "OR LOWER(v.item.brand.name) LIKE :q)")
    Page<ItemVariant> search(@Param("q") String q, @Param("brandId") Long brandId,
                             @Param("petTypeId") Long petTypeId, Pageable pageable);
}
