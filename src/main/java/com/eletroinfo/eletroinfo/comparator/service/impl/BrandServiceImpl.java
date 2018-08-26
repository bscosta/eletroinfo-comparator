package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Brand;
import com.eletroinfo.eletroinfo.comparator.filter.BrandFilter;
import com.eletroinfo.eletroinfo.comparator.repository.BrandRepository;
import com.eletroinfo.eletroinfo.comparator.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public PageImpl<Brand> findByParameters(BrandFilter brandFilter, Pageable pageable) {
        return this.brandRepository.findByParameters(brandFilter.getName(), pageable);
    }

    public Boolean existsByNameAndAndDeletedFalse(String name) {
        return brandRepository.existsByNameAndDeletedFalse(name);
    }

    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }

    public Optional<Brand> findById(Long id) {
        return this.brandRepository.findById(id);
    }

    public Brand delete(Long id) {
        Optional<Brand> brand = this.brandRepository.findById(id);
        if (brand.isPresent()) {
            brand.get().setDeleted(true);
            this.brandRepository.save(brand.get());

            return brand.get();
        }

        return null;
    }
}
