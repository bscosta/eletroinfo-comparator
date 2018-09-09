package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.filter.BudgetFilter;
import com.eletroinfo.eletroinfo.comparator.repository.BudgetRepository;
import com.eletroinfo.eletroinfo.comparator.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Bruno Costa
 */

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public PageImpl<Budget> findByParameters(BudgetFilter budgetFilter, Pageable pageable) {
        return this.budgetRepository.findByParameters(budgetFilter.getProductName(), budgetFilter.getProviderName(), budgetFilter.getSellerName(), budgetFilter.getBarcode(), pageable);
    }
}
