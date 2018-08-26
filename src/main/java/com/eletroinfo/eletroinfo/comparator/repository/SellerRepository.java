package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Costa
 */

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query("SELECT s FROM Seller s INNER JOIN s.contacts sc WHERE UPPER(s.name) LIKE UPPER(CONCAT('%',:name,'%'))" +
           " and UPPER(sc.valueContact) LIKE UPPER(CONCAT('%',:contact,'%')) and s.deleted is false")
    PageImpl<Seller> findByParameters(@Param("name") String name, @Param("contact") String contact, Pageable pageable);
}
