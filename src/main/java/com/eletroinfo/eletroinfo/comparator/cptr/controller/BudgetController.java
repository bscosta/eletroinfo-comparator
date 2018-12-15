package com.eletroinfo.eletroinfo.comparator.cptr.controller;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.BudgetFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.cptr.service.BudgetService;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ProductService;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ProviderService;
import com.eletroinfo.eletroinfo.comparator.cptr.service.SellerService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.cptr.validation.BudgetValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/orcamento")
public class BudgetController {

    @Autowired
    private NotificationHandler notificationHandler;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private BudgetValidation budgetValidation;

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

    @GetMapping(value = "/novo")
    public ModelAndView newBudget(Budget budget) {
        ModelAndView mv = new ModelAndView("budget/save", "budget", budget);
        mv.addObject("products", productService.findAll());
        mv.addObject("sellers", sellerService.findAll());
        mv.addObject("providers", providerService.findAll());
        return mv;
    }

    @PostMapping({"/novo", "{\\+d}"})
    public ModelAndView save(@Valid Budget budget, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (budget.getId() == null) {
            this.budgetValidation.save(budget, bindingResult);
        } else {
            this.budgetValidation.save(budget, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            return newBudget(budget);
        }
        if (budget.getId() == null) {
            notificationHandler.addMessageSucessSave();
        } else {
            notificationHandler.addMessageSucessEdit();
        }

        budget = this.budgetService.save(budget);
        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(budget);

        return new ModelAndView("redirect:/orcamento/"+budget.getId());
    }

    /**
     *
     * @param id
     * @param budget
     * @return
     */
    @GetMapping(value = "/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Budget budget, RedirectAttributes redirectAttributes) {
        if (budget.getItems() == null && !budget.getItems().isEmpty()) {
            Optional<Budget> optionalBudget = this.budgetService.findById(id);
            if (!optionalBudget.isPresent()) {
                notificationHandler.getMessage404NotFound();
                ModelAndView mv = new ModelAndView("redirect:/orcamento");
                redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
                return mv;
            }

            budget = optionalBudget.get();
        }

        return newBudget(budget);
    }
}
