package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(" SELECT count(s) FROM Seller s WHERE "
         + " s.id IN (SELECT sc.sellerId FROM SellerContact sc, Contact c "
         +  " WHERE sc.contactId = c.id AND c.valueContact = :contactValue )"
         +  " AND s.id = :id "
         +  " AND s.deleted is false ")
    Long countBySellerIdAndContactValueAndDeletedIsFalse(@Param("id") Long id, @Param("contactValue") String contactValue);

    @Query(" SELECT s FROM Seller s WHERE " +
           " s.id IN (SELECT sc.sellerId FROM SellerContact sc, Contact c "
            +  " WHERE sc.contactId = c.id)"
            +  " AND s.id = :id "
            +  " AND s.deleted is false ")
    Optional<Seller> findByIdAndSellerDeletedIsFalse(@Param("id") Long id);

    Optional<Seller> findByIdAndDeletedIsFalse(Long id);
}
