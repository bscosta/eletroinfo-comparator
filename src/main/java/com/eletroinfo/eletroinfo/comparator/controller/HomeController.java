package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import com.eletroinfo.eletroinfo.comparator.filter.UserFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public ModelAndView home(UserFilter userFilter){
        ModelAndView mv = new ModelAndView("user/list-user");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        mv.addObject("userType", UserType.values());
        return mv;
    }
}
