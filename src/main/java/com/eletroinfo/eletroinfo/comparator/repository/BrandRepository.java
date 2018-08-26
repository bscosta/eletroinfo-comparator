package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Brand;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bruno Costa
 */

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Transactional(readOnly = true)
    Boolean existsByNameAndDeletedFalse(String name);


    @Query("SELECT b FROM Brand b WHERE UPPER(b.name) LIKE UPPER(CONCAT('%',:name,'%')) and b.deleted is false")
    PageImpl<Brand> findByParameters(@Param("name") String name, Pageable pageable);
}
