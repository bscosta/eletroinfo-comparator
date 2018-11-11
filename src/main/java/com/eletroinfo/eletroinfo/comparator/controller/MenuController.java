package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.Feature;
import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.filter.MenuFeatureFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.FeatureService;
import com.eletroinfo.eletroinfo.comparator.service.MenuService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.validations.FeatureValidation;
import com.eletroinfo.eletroinfo.comparator.validations.MenuValidation;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private MenuValidation menuValidation;

    @Autowired
    private FeatureValidation featureValidation;

    @Autowired
    private NotificationHandler notificationHandler;

    @GetMapping
    public ModelAndView list(MenuFeatureFilter menuFeatureFilter) {
        ModelAndView mv = new ModelAndView("menu/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(MenuFeatureFilter menuFeatureFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Menu> providerPage = new PageWrapper<>(this.menuService.findByParameters(menuFeatureFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("menu/list");
        mv.addObject("pageData", providerPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    @GetMapping(value = "/novo")
    public ModelAndView newMenu(Menu menu) {
        ModelAndView mv = new ModelAndView("menu/save", "menu", menu);
        mv.addObject("parentMenus", menuService.findAllParentMenus());
        mv.addObject("sizeFeatures", menu.getFeatures().size());
        return mv;
    }

    @PostMapping(value = {"/novo", "{\\+d}"}, params = {"save"})
    public ModelAndView save(@Valid Menu menu, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        menu.getFeatures().removeIf(featureDto -> featureDto.getId() == null);
        menuValidation.save(menu, bindingResult);
        if (bindingResult.hasErrors()) {
            return newMenu(menu);
        }

        menu = menuService.save(menu);
        notificationHandler.addMessageSucessSave();
        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(menu);

        return new ModelAndView("redirect:/menu/"+menu.getId());
    }

    @PostMapping(value="/{\\d}", params = {"addFeature"})
    public ModelAndView addFeature(@Valid Menu menu, BindingResult bindingResult) {
        Feature feature = new Feature();
        feature = menu.getFeatures().stream().filter(feature1 -> feature1.getId() == null).findFirst().get();
        featureValidation.save(feature, bindingResult);
        if (bindingResult.hasErrors()) {
            menu.setUpdateFeature(true);
            ModelAndView mv = newMenu(menu);
            mv.addObject("sizeFeatures", menu.getFeatures().size()-1);
            return mv;
        }
        List<Feature> features = this.featureService.saveList(menu.getFeatures());
        menu.setFeatures(features);
        menu = menuService.save(menu);
        menu.setUpdateFeature(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "feature.adicionada.sucesso");
        ModelAndView mv = newMenu(menu);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @PostMapping(value="/{\\d}", params={"delFeature"})
    public ModelAndView delFeature(@RequestParam("delFeature") Long delFeature, @Valid Menu menu) {
        menu.getFeatures().removeIf(featureDto -> featureDto.getId() == delFeature || featureDto.getId() == null);
        featureService.delete(delFeature);
        menu.setUpdateFeature(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "feature.removida.sucesso");
        ModelAndView mv = newMenu(menu);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Menu menu, RedirectAttributes redirectAttributes) {
        if (menu.getName() == null) {
            Optional<Menu> optionalMenu = this.menuService.findById(id);
            if (!optionalMenu.isPresent()) {
                notificationHandler.getMessage404NotFound();
                ModelAndView mv = new ModelAndView("redirect:/menu");
                redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
                return mv;
            }

            menu = optionalMenu.get();
        }

        ModelAndView mv = newMenu(menu);
        return mv;
    }
}
