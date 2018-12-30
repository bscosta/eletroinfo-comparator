package com.eletroinfo.eletroinfo.comparator.cptr.validation;

import com.eletroinfo.eletroinfo.comparator.auth.service.UserService;
import com.eletroinfo.eletroinfo.comparator.auth.validation.UserValidation;
import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.cptr.service.SellerService;
import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import com.eletroinfo.eletroinfo.comparator.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Component
public class SellerValidation implements Validator {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

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

        if (!errors.hasErrors() && seller.getUser() != null) {
            seller.getUser().setName(seller.getName());
            seller.getUser().setUserType(UserType.ADMINISTRATIVE);
            validateLogin(seller, errors);
            validateEmail(seller, errors);
            if (seller.getUser().getPassword() == null || seller.getUser().getPassword().isEmpty()) {
                errors.rejectValue("user.password","senha.vazio", "Campo de senha não pode ser vazio");
            } else {
                validatePassword(seller, errors);
                if (!errors.hasErrors()) {
                    seller.getUser().setPassword(PasswordUtils.generateBCrypt(seller.getUser().getPassword()));
                }
            }
        }
    }

    public void update(Seller seller, Errors errors) {
        Optional<Seller> sellerDb = this.sellerService.findById(seller.getId());
        if (!sellerDb.isPresent()) {
            errors.rejectValue("id", "vendedor.nao.encontrado", "Vendedor Não existe!");
        } else {
            if (seller.getName() == null || seller.getName().isEmpty()) {
                errors.rejectValue("name", "name.vendedor.vazio", "Nome do Vendedor vazio!");
            }
            if (seller.getUser() != null) {
                if (sellerDb.get().getUser() != null) {

                    if (!seller.getUser().getEmail().equals(sellerDb.get().getUser().getEmail())) {
                        validateEmail(seller, errors);
                    }
                    if (!seller.getUser().getLogin().equals(sellerDb.get().getUser().getLogin())) {
                        validateLogin(seller, errors);
                    }
                    if (seller.getUser().getPassword() != null && !seller.getUser().getPassword().isEmpty()) {
                        validatePassword(seller, errors);
                        if (!errors.hasErrors()) {
                            seller.getUser().setPassword(PasswordUtils.generateBCrypt(seller.getUser().getPassword()));
                        }
                    } else {
                        seller.getUser().setPassword(sellerDb.get().getUser().getPassword());
                    }
                    if (!errors.hasErrors()) {
                        seller.getUser().setName(seller.getName());
                        seller.getUser().setUserType(sellerDb.get().getUser().getUserType());
                        seller.getUser().setListGroupUsers(sellerDb.get().getUser().getListGroupUsers());
                    }
                }
            }
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
        if (!errors.hasErrors()) {

        }
    }

    public void validateLogin(Seller seller, Errors errors) {
        if (seller.getUser().getLogin() == null || seller.getUser().getLogin().isEmpty()) {
            errors.rejectValue("user.login","login.vazio", "");
        } else if(this.userService.existsByLoginAndDeletedFalse(seller.getUser().getLogin())){
            errors.rejectValue("user.login","login.repetido", new Object[] {seller.getUser().getLogin()}, "");
        }
    }

    public void validatePassword(Seller seller, Errors errors) {
        if (!new PasswordUtils().validatePassword(seller.getUser().getPassword())) {
            errors.rejectValue("user.password","senha.exigencias", "");
        }
    }

    public void validateEmail(Seller seller, Errors errors) {
        if(!seller.getUser().getEmail().isEmpty() && this.userService.existsByEmailAndDeletedFalse(seller.getUser().getEmail())){
            errors.rejectValue("user.email","email.repetido", new Object[] {seller.getUser().getEmail()},"");
        }
    }

    public void validateUserType(Seller seller, Errors errors) {
        if (seller.getUser().getUserType() == null) {
            errors.rejectValue("user.userType","userType.vazio","");
        }
    }
}
