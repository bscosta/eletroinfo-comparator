package com.eletroinfo.eletroinfo.comparator.validations;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Component
public class UserValidation implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void validateSave(User user, Errors errors) {
        validateLogin(user, errors);
        validateEmail(user, errors);
        if (user.getPassword().isEmpty()) {
            errors.rejectValue("password","", "Campo de senha não pode ser vazio");
        }
    }

    public void validateUpdate(User user, Errors errors) {
        Optional<User> userSaved = this.userService.findById(user.getId());
        if (!userSaved.get().getEmail().equals(user.getEmail())) {
            validateEmail(user, errors);
        }
        if (!userSaved.get().getLogin().equals(user.getLogin())) {
            validateLogin(user, errors);
        }
        if (user.getPassword() == null) {
            user.setPassword(userSaved.get().getPassword());
        }
    }

    public void validateLogin(User user, Errors errors) {
        if(this.userService.existsByLoginAndDeletedFalse(user.getLogin())){
            errors.rejectValue("login","", "Login "+ user.getLogin() +" já existe");
        }
    }

    public void validateEmail(User user, Errors errors) {
        if(this.userService.existsByEmailAndDeletedFalse(user.getEmail())){
            errors.rejectValue("email","", "Email "+ user.getEmail() +" já existe");
        }
    }
}
