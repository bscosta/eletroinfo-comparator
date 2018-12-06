package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.BudgetFilter;
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
