package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.filter.BudgetFilter;
import com.eletroinfo.eletroinfo.comparator.service.BudgetService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/orcamento")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ModelAndView list(BudgetFilter budgetFilter) {
        ModelAndView mv = new ModelAndView("budget/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(BudgetFilter budgetFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Budget> budgetPage = new PageWrapper<>(this.budgetService.findByParameters(budgetFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("budget/list");
        mv.addObject("pageData", budgetPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }
}
