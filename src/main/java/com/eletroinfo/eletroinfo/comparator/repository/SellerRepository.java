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

}
