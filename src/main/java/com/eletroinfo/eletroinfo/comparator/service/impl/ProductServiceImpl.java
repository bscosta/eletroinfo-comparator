package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.filter.ProductFilter;
import com.eletroinfo.eletroinfo.comparator.repository.ProductRepository;
import com.eletroinfo.eletroinfo.comparator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public PageImpl<Product> findByParameters(ProductFilter productFilter, Pageable pageable) {
        return this.productRepository.findByParameters(productFilter.getName(), pageable);
    }

    public boolean existsByNameAndDeletedFalse(String name) {
        return productRepository.existsByNameAndDeletedFalse(name);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
