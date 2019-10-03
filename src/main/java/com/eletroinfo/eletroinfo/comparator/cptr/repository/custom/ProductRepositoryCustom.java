package com.eletroinfo.eletroinfo.comparator.cptr.repository.custom;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Product;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    /**
     *
     * @param name
     * @param barcode
     * @param pageable
     * @return
     */
    PageImpl<Product> findByParameters(String name, String barcode, Pageable pageable);
}
