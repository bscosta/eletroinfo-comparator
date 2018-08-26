package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import com.eletroinfo.eletroinfo.comparator.repository.SellerRepository;
import com.eletroinfo.eletroinfo.comparator.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public PageImpl<Seller> findByParameters(SellerFilter sellerFilter, Pageable pageable) {
        return sellerRepository.findByParameters(sellerFilter.getName(), sellerFilter.getContact(), pageable);
    }
}
