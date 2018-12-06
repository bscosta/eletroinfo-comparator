package com.eletroinfo.eletroinfo.comparator.cptr.repository;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
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
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT b FROM Budget b WHERE UPPER(b.product.name) LIKE UPPER(CONCAT('%',:productName,'%')) "
            + " AND UPPER(b.provider.name) LIKE UPPER(CONCAT('%',:providerName,'%')) "
            + " AND UPPER(b.seller.name) LIKE UPPER(CONCAT('%',:sellerName,'%')) "
            + " AND b.barcode = :barcode "
            + " AND b.deleted is false ")
    PageImpl<Budget> findByParameters(@Param("productName") String productName, @Param("providerName") String providerName, @Param("sellerName") String sellerName, @Param("barcode") Long barcode, Pageable pageable);
}
