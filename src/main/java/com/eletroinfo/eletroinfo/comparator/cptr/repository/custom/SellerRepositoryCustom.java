package com.eletroinfo.eletroinfo.comparator.cptr.repository.custom;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Seller;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellerRepositoryCustom {

    PageImpl<Seller> findByParameters(String name, String contact, Pageable pageable);

    List<Seller> findSellers();
}
