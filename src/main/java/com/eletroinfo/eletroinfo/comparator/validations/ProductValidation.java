package com.eletroinfo.eletroinfo.comparator.validations;

import com.eletroinfo.eletroinfo.comparator.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Component
public class ProductValidation implements Validator {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void validateSave(Product product, Errors errors) {
        validateName(product, errors);
        validateUnitMeasure(product, errors);
        validateMeasuredQuantity(product, errors);
        validateBrand(product, errors);
    }

    public void validateUpdate(Product product, Errors errors) {
        Optional<Product> productSaved = productService.findById(product.getId());
        if (!productSaved.get().getName().equals(product.getName())) {
            validateName(product, errors);
            productSaved.get().setName(product.getName());
        }

        if (!productSaved.get().getUnitMeasure().equals(product.getUnitMeasure())) {
            validateUnitMeasure(product, errors);
            productSaved.get().setUnitMeasure(product.getUnitMeasure());
        }

        if (!productSaved.get().getMeasuredQuantity().equals(product.getMeasuredQuantity())) {
            validateMeasuredQuantity(product, errors);
            productSaved.get().setMeasuredQuantity(product.getMeasuredQuantity());
        }

        if (!productSaved.get().getBrand().getId().equals(product.getBrand().getId())) {
            validateBrand(product, errors);
            productSaved.get().getBrand().setId(product.getBrand().getId());
        }

        if (!errors.hasErrors()) {
            product = productSaved.get();
        }
    }

    public void validateName(Product product, Errors errors) {
        if (product.getName() == null || product.getName().isEmpty()) {
            errors.rejectValue("name", "nome.produto.vazio");
        } else if (productService.existsByNameAndDeletedFalse(product.getName())) {
            errors.rejectValue("name", "nome.produto.repetido", new Object[]{product.getName()}, "");
        }
    }

    public void validateUnitMeasure(Product product, Errors errors) {
        if (product.getUnitMeasure() == null || product.getUnitMeasure().isEmpty()) {
            errors.rejectValue("unitMeasure", "medida.produto.vazio");
        }
    }

    public void validateMeasuredQuantity(Product product, Errors errors) {
        if (product.getMeasuredQuantity() == null) {
            errors.rejectValue("measuredQuantity", "quantidade.produto.vazio");
        }
    }

    public void validateBrand(Product product, Errors errors) {
        if (product.getBrand() == null || product.getBrand().getId() == null) {
            errors.rejectValue("brand", "marca.produto.vazio");
        }
    }
}
