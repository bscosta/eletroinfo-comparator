package com.eletroinfo.eletroinfo.comparator.cptr.controller;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.ProductFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.cptr.service.BrandService;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ProductService;
import com.eletroinfo.eletroinfo.comparator.cptr.validation.ProductValidation;
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

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private BrandService brandService;

    @Autowired
    private NotificationHandler notificationHandler;

    /**
     *
     * @param productFilter
     * @return
     */
    @GetMapping
    public ModelAndView list(ProductFilter productFilter) {
        ModelAndView mv = new ModelAndView("product/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(ProductFilter productFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageImpl<Product> productPage = this.productService.findByParameters(productFilter, pageable);
        ModelAndView mv = new ModelAndView("product/list");
        mv.addObject("pageData", productPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    @GetMapping(value = "/novo")
    public ModelAndView newProduct(Product product) {
        ModelAndView mv = new ModelAndView("product/save", "product", product);
        mv.addObject("brands", brandService.findAll());
        return mv;
    }

    /**
     *
     * @param product
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = {"/novo", "{\\+d}"})
    public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (product.getId() == null) {
            productValidation.validateSave(product, bindingResult);
        } else {
            productValidation.validateUpdate(product, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            return newProduct(product);
        }

        if (product.getId() == null) {
            notificationHandler.addMessageSucessSave();
        } else {
            notificationHandler.addMessageSucessEdit();
        }

        product = productService.save(product);

        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(product);
        return new ModelAndView("redirect:/produto/"+product.getId());
    }

    @GetMapping(value = "/{id}")
    public ModelAndView edit(@PathVariable Long id, Product product) {
        if (product.getName() == null) {
            product = this.productService.findById(id).get();
        }

        ModelAndView mv = newProduct(product);
        return mv;
    }

}
