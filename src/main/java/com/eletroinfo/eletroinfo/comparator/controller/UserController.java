package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/novo")
    public ModelAndView userNew(User user) {
        return new ModelAndView("/usuario/cadastro-usuario");
    }
}
