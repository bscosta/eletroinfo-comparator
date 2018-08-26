package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Brand;
import com.eletroinfo.eletroinfo.comparator.filter.BrandFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BrandService {

    /**
     *
     * @param brandFilter
     * @param pageable
     * @return
     */
    PageImpl<Brand> findByParameters(BrandFilter brandFilter, Pageable pageable);

    /**
     *
     * @param name
     * @return Boolean
     */
    Boolean existsByNameAndAndDeletedFalse(String name);

    /**
     *
     * @param brand
     * @return
     */
    Brand save(Brand brand);

    /**
     *
     * @param id
     * @return
     */
    Optional<Brand> findById(Long id);

    /**
     *
     * @param id
     * @return
     */
    Brand delete(Long id);
}
