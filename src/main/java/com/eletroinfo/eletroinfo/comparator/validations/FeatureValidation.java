package com.eletroinfo.eletroinfo.comparator.validations;

import com.eletroinfo.eletroinfo.comparator.entitie.Feature;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bruno Costa
 */

@Component
public class FeatureValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void save(Feature feature, Errors errors) {
        if (feature.getName().isEmpty()) {
            errors.rejectValue("features", "nome.feature.vazio");
        }

        if (feature.getCode().isEmpty()) {
            errors.rejectValue("features", "codigo.feature.vazio");
        }

    }
}
