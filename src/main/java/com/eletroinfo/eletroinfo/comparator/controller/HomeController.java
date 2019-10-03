package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import com.eletroinfo.eletroinfo.comparator.auth.filter.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    static Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value = "/")
    public ModelAndView home(){
        log.info("Iniciando renderização da página inicial do sistema");
        ModelAndView mv = new ModelAndView("dashboard/dashboard");
        log.info("Finalizando renderização da página inicial do sistema");
        return mv;
    }
}
