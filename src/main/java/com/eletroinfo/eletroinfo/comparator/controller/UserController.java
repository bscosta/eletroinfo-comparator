package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import com.eletroinfo.eletroinfo.comparator.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/usuario")
public class UserController {

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private UserService userService;

    @GetMapping("/novo")
    public ModelAndView userNew(User user) {
        if (user.isNovo()) {
            user.setActivated(true);
        }
        ModelAndView mv = new ModelAndView("/usuario/cadastro-usuario", "user", user);
        mv.addObject("userType", UserType.values());
        return mv;
    }

    @PostMapping({"/novo", "{\\+d}"})
    public ModelAndView salvar(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (user.getId() == null) {
            this.userValidation.validateSave(user, result);
        } else {
            this.userValidation.validateUpdate(user, result);
        }

        if (result.hasErrors()) {
            return userNew(user);
        }

        user = this.userService.save(user);
        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
        attributes.addFlashAttribute(user);
        return new ModelAndView("redirect:/usuario/"+user.getId());
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable Long id, User user) {
        if (user.getLogin() == null) {
            user = this.userService.findById(id).get();
        }

        ModelAndView mv = userNew(user);
        return mv;
    }
}
