package com.eletroinfo.eletroinfo.comparator.cptr.repository;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Product;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM cptr.product p where UPPER(p.name) LIKE UPPER(CONCAT('%',:name,'%')) AND IFNULL(p.barcode, '[0-9]' REGEXP :barcode) ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM cptr.product p WHERE UPPER(p.name) LIKE UPPER(CONCAT('%',:name,'%')) AND p.barcode REGEXP CONCAT('[',:barcode,']')",
            nativeQuery = true)
    PageImpl<Product> findByParameters(@Param("name") String name, @Param("barcode") String barcode, Pageable pageable);

    @Transactional(readOnly = true)
    boolean existsByNameAndDeletedFalse(String name);
}
