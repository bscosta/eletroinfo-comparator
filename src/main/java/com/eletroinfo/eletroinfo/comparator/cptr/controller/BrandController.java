package com.eletroinfo.eletroinfo.comparator.cptr.controller;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Brand;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.BrandFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.cptr.service.BrandService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.validations.BrandValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/marca")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandValidation brandValidation;

    @Autowired
    private NotificationHandler notificationHandler;

    @GetMapping
    public ModelAndView list(BrandFilter brandFilter) {
        ModelAndView mv = new ModelAndView("brand/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(BrandFilter brandFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Brand> productPage = new PageWrapper<>(this.brandService.findByParameters(brandFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("brand/list");
        mv.addObject("pageData", productPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    @GetMapping(value = "/novo")
    public ModelAndView newBrand(Brand brand) {
        return new ModelAndView("brand/save", "brand", brand);
    }

    @PostMapping(value = {"/novo", "{\\+d}"})
    public ModelAndView save(@Valid Brand brand, BindingResult result, RedirectAttributes redirectAttributes) {
        if (brand.getId() == null) {
            this.brandValidation.validateSave(brand, result);
        } else {
            this.brandValidation.validateUpdate(brand, result);
        }

        if (result.hasErrors()) {
            return newBrand(brand);
        }

        if (brand.getId() == null) {
            notificationHandler.addMessageSucessSave();
        } else {
            notificationHandler.addMessageSucessEdit();
        }

        brand = this.brandService.save(brand);

        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(brand);
        return new ModelAndView("redirect:/marca/"+brand.getId());
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable Long id, Brand brand) {
        if (brand.getName() == null) {
            brand = this.brandService.findById(id).get();
        }

        ModelAndView mv = newBrand(brand);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.brandService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     *
     * @param brand
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/cadastroRapido", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<?> quiskSave(@RequestBody @Valid Brand brand, BindingResult bindingResult) {
        this.brandValidation.validateSave(brand, bindingResult);
        if (bindingResult.hasErrors()) {
            for (ObjectError message : bindingResult.getAllErrors()) {
                notificationHandler.addMessageinternationalized(TypeMessage.message_error, message.getCode());
            }
            return ResponseEntity.badRequest().body(notificationHandler.getMessages());
        }

        brand = brandService.save(brand);
        return ResponseEntity.ok(brand);
    }
}
