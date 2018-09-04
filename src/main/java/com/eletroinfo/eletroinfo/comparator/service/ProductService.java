package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.filter.ProductFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    /**
     *
     * @param productFilter
     * @param pageable
     * @return
     */
    PageImpl<Product> findByParameters(ProductFilter productFilter, Pageable pageable);

    /**
     *
     * @param name
     * @return
     */
    boolean existsByNameAndDeletedFalse(String name);

    /**
     *
     * @param product
     * @return
     */
    Product save(Product product);

    /**
     *
     * @param id
     * @return
     */
    Optional<Product> findById(Long id);
}
