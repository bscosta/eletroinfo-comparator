package com.eletroinfo.eletroinfo.comparator.cptr.validation;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.cptr.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Component
public class BudgetValidation implements Validator{

    @Autowired
    private BudgetService budgetService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void save(Budget budget, Errors errors) {
        if (budget.getProvider() == null || budget.getProvider().getId() == null) {
            errors.rejectValue("provider", "fornecedor.vazio");
        }
        if (budget.getSeller() == null || budget.getSeller().getId() == null) {
            errors.rejectValue("seller", "vendedor.vazio");
        }

    }

    public void update(Budget budget, Errors errors) {
        if (budget.getProvider() == null || budget.getProvider().getId() == null) {
            errors.rejectValue("provider", "fornecedor.vazio");
        }
        if (budget.getSeller() == null || budget.getSeller().getId() == null) {
            errors.rejectValue("seller", "vendedor.vazio");
        }
        if (!errors.hasErrors() && budget.getId() != null) {
            Optional<Budget> optionalBudget = this.budgetService.findById(budget.getId());
            if (optionalBudget.isPresent()) {
                if (!budget.getDateExpire().isEqual(optionalBudget.get().getDateExpire())) {
                    errors.rejectValue("dateExpire", "data.expira.diferente");
                }
            }
        }
    }
}
