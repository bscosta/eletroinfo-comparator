package com.eletroinfo.eletroinfo.comparator.cptr.validation;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bruno Costa
 */

@Component
public class BudgetValidation implements Validator{
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
}
