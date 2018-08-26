package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface SellerService {

    PageImpl<Seller> findByParameters(SellerFilter sellerFilter, Pageable pageable);
}
