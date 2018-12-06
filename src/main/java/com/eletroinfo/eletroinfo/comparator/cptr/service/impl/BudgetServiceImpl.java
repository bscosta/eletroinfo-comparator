package com.eletroinfo.eletroinfo.comparator.cptr.service.impl;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.BudgetFilter;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.BudgetRepository;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.BudgetRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.cptr.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetRepositoryCustom budgetRepositoryCustom;

    public PageImpl<Budget> findByParameters(BudgetFilter budgetFilter, Pageable pageable) {
        return this.budgetRepositoryCustom.findByParameters(budgetFilter.getProductName(), budgetFilter.getProviderName(), budgetFilter.getSellerName(), budgetFilter.getBarcode(), pageable);
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Optional<Budget> findById(Long id) {
        return budgetRepository.findById(id);
    }
}
