package com.eletroinfo.eletroinfo.comparator.auth.validation;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.Menu;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MenuValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void save(Menu menu, Errors errors) {
        if (menu.getName() == null || menu.getName().isEmpty()) {
            errors.rejectValue("name", "nome.menu.vazio");
        }

        if (menu.getUrl() == null || menu.getUrl().isEmpty()) {
            errors.rejectValue("url", "url.menu.vazio");
        }
    }
}
