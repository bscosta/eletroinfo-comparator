package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.ProductFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

    /**
     *
     * @return
     */
    List<Product> findAll();
}
