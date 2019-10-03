package com.eletroinfo.eletroinfo.comparator.cptr.validation;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bruno Costa
 */

@Component
public class ItemValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void save(Item item, Errors errors) {
        if (item.getProduct() == null || item.getProduct().getId() == null) {
            errors.rejectValue("items", "produto.vazio");
        }

        if (item.getPrice() == null) {
            errors.rejectValue("items", "preco.vazio");
        }

        if (item.getQuantity() == null) {
            errors.rejectValue("items", "quantidade.vazia");
        }
    }
}
