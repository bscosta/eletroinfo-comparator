package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.SellerFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SellerService {

    PageImpl<Seller> findByParameters(SellerFilter sellerFilter, Pageable pageable);

    Long countBySellerIdAndContactValueAndDeletedIsFalse(Long id, String contactValue);

    Seller save(Seller seller);

    Optional<Seller> findById(Long id);

    List<Seller> findAll();

    void delete(Long id);
}