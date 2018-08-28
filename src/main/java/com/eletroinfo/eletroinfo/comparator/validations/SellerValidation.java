package com.eletroinfo.eletroinfo.comparator.validations;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Bruno Costa
 */

@Component
public class SellerValidation implements Validator {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SellerService sellerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void save(Seller seller, Errors errors) {
        if (seller.getName() == null || seller.getName().isEmpty()) {
            errors.rejectValue("name", "name.vendedor.vazio");
        }
    }

    public void contact(Seller seller, Errors errors) {
        for (Contact contact : seller.getContacts()) {
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
            } else if (contact.getId() == null && sellerService.countBySellerIdAndContactValueAndDeletedIsFalse(seller.getId(), contact.getValueContact()) > 0) {
                errors.rejectValue("contacts", "value.contato.existe", new Object[]{contact.getValueContact()},"");
            }
        }
    }
}
