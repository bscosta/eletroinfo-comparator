package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.filter.BudgetFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

public interface BudgetService {

    PageImpl<Budget> findByParameters(BudgetFilter budgetFilter, Pageable pageable);

    Budget save(Budget budget);

    Optional<Budget> findById(Long id);
}
