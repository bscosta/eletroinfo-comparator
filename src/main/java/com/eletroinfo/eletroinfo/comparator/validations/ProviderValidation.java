package com.eletroinfo.eletroinfo.comparator.validations;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.entitie.Provider;
import com.eletroinfo.eletroinfo.comparator.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bruno Costa
 */

@Component
public class ProviderValidation implements Validator {

    @Autowired
    private ProviderService providerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void save(Provider provider, Errors errors) {
        validateName(provider, errors);
    }

    public void validateName(Provider provider, Errors errors) {
        if (provider.getName() == null || provider.getName().isEmpty()) {
            errors.rejectValue("name", "nome.fornecedor.vazio");
        } else if(providerService.existsByNameAndDeletedFalse(provider.getName())) {
            errors.rejectValue("name", "nome.fornecedor.existe", new Object[]{provider.getName()}, "");
        }
    }

    public void contact(Provider provider, Errors errors) {
        for (Contact contact : provider.getContacts()) {
            if (contact.getTypeContact() == null || contact.getTypeContact().isEmpty()) {
                errors.rejectValue("contacts", "tipo.contato.vazio");
            }
            if (contact.getName() == null || contact.getName().isEmpty()) {
                errors.rejectValue("contacts", "nome.contato.vazio");
            }
            if (contact.getFormContact() == null || contact.getFormContact().isEmpty()) {
                errors.rejectValue("contacts", "forma.contato.vazio");
            }
            if (contact.getValueContact() == null || contact.getValueContact().isEmpty()) {
                errors.rejectValue("contacts", "value.contato.vazio");
            } else if (contact.getId() == null && providerService.countByProviderIdAndContactValueAndDeletedIsFalse(provider.getId(), contact.getValueContact()) > 0) {
                errors.rejectValue("contacts", "value.contato.existe", new Object[]{contact.getValueContact()},"");
            }
        }
    }
}
