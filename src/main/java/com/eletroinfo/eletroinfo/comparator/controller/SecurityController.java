package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.security.UserLogged;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    static Logger log = LoggerFactory.getLogger(SecurityController.class);

    @GetMapping(value = "/login")
    public String login(@AuthenticationPrincipal UserLogged userLogged) {
        if (userLogged != null) {
            log.info("Usuário id {} já está logado, redirecionando para a página inicial do sistema", userLogged.getPermissionDto().getUserDto().getId());
            return "redirect:/usuario";
        }

        log.info("Renderizando a página de login");

        return "login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        log.info("Renderizando a página de acesso negado");
        return "403";
    }
}
