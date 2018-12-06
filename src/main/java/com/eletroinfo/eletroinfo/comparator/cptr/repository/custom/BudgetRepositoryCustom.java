package com.eletroinfo.eletroinfo.comparator.cptr.repository.custom;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface BudgetRepositoryCustom {

    PageImpl<Budget> findByParameters(String productName, String providerName, String sellerName, Long barcode, Pageable pageable);
}
