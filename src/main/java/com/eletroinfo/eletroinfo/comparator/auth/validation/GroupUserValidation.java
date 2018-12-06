package com.eletroinfo.eletroinfo.comparator.auth.validation;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.GroupUser;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GroupUserValidation implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void save(GroupUser groupUser, Errors errors) {
        if (groupUser.getName() == null || groupUser.getName().isEmpty()) {
            errors.rejectValue("name", "nome.vazio");
        }
    }
}
