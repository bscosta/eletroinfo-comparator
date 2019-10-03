package com.eletroinfo.eletroinfo.comparator.cptr.service.impl;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.ProductFilter;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.ProductRepository;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.ProductRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepositoryCustom productRepositoryCustom;

    public PageImpl<Product> findByParameters(ProductFilter productFilter, Pageable pageable) {
        return this.productRepositoryCustom.findByParameters(productFilter.getName(), productFilter.getBarcode(), pageable);
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

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }
}
