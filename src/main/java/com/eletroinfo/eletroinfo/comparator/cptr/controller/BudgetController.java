package com.eletroinfo.eletroinfo.comparator.cptr.controller;

import com.eletroinfo.eletroinfo.comparator.cptr.Enumeration.BudgetStatusEnum;
import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Item;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.BudgetFilter;
import com.eletroinfo.eletroinfo.comparator.cptr.service.*;
import com.eletroinfo.eletroinfo.comparator.cptr.validation.ItemValidation;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.cptr.validation.BudgetValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private ItemService itemService;

    @Autowired
    private BudgetValidation budgetValidation;

    @Autowired
    private ItemValidation itemValidation;

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
        mv.addObject("sizeItems", budget.getItems().size());
        mv.addObject("products", productService.findAll());
        mv.addObject("sellers", sellerService.findAll());
        mv.addObject("providers", providerService.findAll());
        mv.addObject("budgetStatus", BudgetStatusEnum.values());
        mv.addObject("localDateTime", LocalDateTime.now());
        return mv;
    }

    @PostMapping({"/novo", "{\\+d}"})
    public ModelAndView save(@Valid Budget budget, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        budget.getItems().removeIf(item -> item.getId() == null);
        if (budget.getId() == null) {
            this.budgetValidation.save(budget, bindingResult);
        } else {
            this.budgetValidation.update(budget, bindingResult);
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

    @PostMapping(value="/{\\d}", params = {"addItem"})
    public ModelAndView addItem(@Valid Budget budget, BindingResult bindingResult) {
        Item item = new Item();
        item = budget.getItems().stream().filter(item1 -> item1.getId() == null).findFirst().get();
        itemValidation.save(item, bindingResult);
        if (bindingResult.hasErrors()) {
            budget.setUpdateItem(true);
            ModelAndView mv = newBudget(budget);
            mv.addObject("sizeFeatures", budget.getItems().size()-1);
            return mv;
        }
        List<Item> items = this.itemService.saveList(budget.getItems());
        budget.setItems(items);
        budget = budgetService.save(budget);
        budget.setUpdateItem(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "item.adicionado.sucesso");
        ModelAndView mv = newBudget(budget);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    /**
     *
     * @param delItem
     * @param budget
     * @return
     */
    @PostMapping(value="/{\\d}", params={"delItem"})
    public ModelAndView delItem(@RequestParam("delItem") Long delItem, @Valid Budget budget) {
        budget.getItems().removeIf(item -> item.getId() == delItem || item.getId() == null);
        itemService.delete(delItem);
        budget = this.budgetService.save(budget);
        budget.setUpdateItem(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "item.removido.sucesso");
        ModelAndView mv = newBudget(budget);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    /**
     *
     * @param id
     * @param budget
     * @return
     */
    @GetMapping(value = "/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Budget budget, RedirectAttributes redirectAttributes) {
        if (budget.getSeller() == null || budget.getSeller().getId() == null) {
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
