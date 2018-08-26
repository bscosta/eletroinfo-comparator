package com.eletroinfo.eletroinfo.comparator.validations;

import com.eletroinfo.eletroinfo.comparator.entitie.Brand;
import com.eletroinfo.eletroinfo.comparator.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Component
public class BrandValidation implements Validator {

    @Autowired
    private BrandService brandService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void validateSave(Brand brand, Errors errors) {
        validateName(brand, errors);
    }

    public void validateUpdate(Brand brand, Errors errors) {
        Optional<Brand> brandSaved = this.brandService.findById(brand.getId());
        if (!brandSaved.get().getName().equals(brand.getName())) {
            validateName(brand, errors);
        }
    }

    public void validateName(Brand brand, Errors errors) {
        if (brand.getName() == null || brand.getName().isEmpty()) {
            errors.rejectValue("name","nome.vazio", "");
        } else if (this.brandService.existsByNameAndAndDeletedFalse(brand.getName())) {
            errors.rejectValue("name","nome.repetido", new Object[]{brand.getName()},"");
        }
    }

}
