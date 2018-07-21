package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/novo")
    public ModelAndView userNew(User user) {
        return new ModelAndView("/usuario/cadastro-usuario");
    }

    @PostMapping({"/novo", "{\\+d}"})
    public ModelAndView salvar(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return userNew(user);
        }

        user = this.userService.save(user);
        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
        return new ModelAndView("redirect:/usuario/novo");
    }
}
