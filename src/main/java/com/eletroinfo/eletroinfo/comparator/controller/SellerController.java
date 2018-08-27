package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.SellerService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
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

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/vendedor")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private NotificationHandler notificationHandler;

    @GetMapping
    public ModelAndView list(SellerFilter sellerFilter) {
        ModelAndView mv =  new ModelAndView("layout/seller/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(SellerFilter sellerFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Seller> productPage = new PageWrapper<>(this.sellerService.findByParameters(sellerFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("layout/seller/list");
        mv.addObject("pageData", productPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    @GetMapping(value = "/novo")
    public ModelAndView newSaller(Seller seller) {
        ModelAndView mv = new ModelAndView("layout/seller/save", "seller", seller);
        mv.addObject("sizeContacts", seller.getContacts().size());
        return mv;
    }

    @PostMapping(value = {"/novo", "{\\+d}"}, params = {"save"})
    public ModelAndView save(@Valid Seller seller, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        seller = sellerService.save(seller);
        notificationHandler.addMessageSucessSave();
        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(seller);
        return new ModelAndView("redirect:/vendedor/"+seller.getId());
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Seller seller) {
        if (seller.getName() == null) {
            seller = sellerService.findById(id).get();
        }

        ModelAndView mv = newSaller(seller);
        return mv;
    }

    @PostMapping(value="/{\\d}", params = {"addContact"})
    public ModelAndView addContact(@Valid Seller seller, BindingResult bindingResult) {
        seller = sellerService.save(seller);
        seller.setUpdateContact(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "contato.adicionado.sucesso");
        ModelAndView mv = newSaller(seller);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @PostMapping(value="/{\\d}", params={"delContact"})
    public ModelAndView delContact(@RequestParam("delContact") Long delContact, @Valid Seller seller, BindingResult bindingResult) {
        seller.getContacts().removeIf(contact -> contact.getId() == delContact);
        seller = sellerService.save(seller);
        seller.setUpdateContact(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "contato.removido.sucesso");
        ModelAndView mv = newSaller(seller);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

}
