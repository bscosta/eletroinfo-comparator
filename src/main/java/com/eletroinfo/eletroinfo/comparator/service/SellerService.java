package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SellerService {

    PageImpl<Seller> findByParameters(SellerFilter sellerFilter, Pageable pageable);

    Long countBySellerIdAndContactValueAndDeletedIsFalse(Long id, String contactValue);

    Seller save(Seller seller);

    Optional<Seller> findById(Long id);
}
