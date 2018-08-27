package com.eletroinfo.eletroinfo.comparator.repository.custom;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface SellerRepositoryCustom {

    PageImpl<Seller> findByParameters(String name, String contact, Pageable pageable);
}
