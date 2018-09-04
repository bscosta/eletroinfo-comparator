package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Product;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE UPPER(p.name) LIKE UPPER(CONCAT('%',:name,'%')) ")
    PageImpl<Product> findByParameters(@Param("name") String name, Pageable pageable);

    @Transactional(readOnly = true)
    boolean existsByNameAndDeletedFalse(String name);
}
