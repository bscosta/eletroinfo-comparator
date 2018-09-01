package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.service.SellerService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.validations.SellerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    private ContactService contactService;

    @Autowired
    private SellerValidation sellerValidation;

    @Autowired
    private NotificationHandler notificationHandler;

    /**
     *
     * @param sellerFilter
     * @return
     */
    @GetMapping
    public ModelAndView list(SellerFilter sellerFilter) {
        ModelAndView mv =  new ModelAndView("layout/seller/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    /**
     *
     * @param sellerFilter
     * @param pageable
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "/buscar")
    public ModelAndView find(SellerFilter sellerFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Seller> productPage = new PageWrapper<>(this.sellerService.findByParameters(sellerFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("layout/seller/list");
        mv.addObject("pageData", productPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    /**
     *
     * @param seller
     * @return
     */
    @GetMapping(value = "/novo")
    public ModelAndView newSaller(Seller seller) {
        ModelAndView mv = new ModelAndView("layout/seller/save", "seller", seller);
        mv.addObject("sizeContacts", seller.getContacts().size());
        return mv;
    }

    /**
     *
     * @param seller
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = {"/novo", "{\\+d}"}, params = {"save"})
    public ModelAndView save(@Valid Seller seller, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        seller.getContacts().removeIf(contact -> contact.getId() == null);
        sellerValidation.save(seller, bindingResult);
        if (bindingResult.hasErrors()) {
            return newSaller(seller);
        }
        seller = sellerService.save(seller);
        notificationHandler.addMessageSucessSave();
        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(seller);
        return new ModelAndView("redirect:/vendedor/"+seller.getId());
    }

    /**
     *
     * @param id
     * @param seller
     * @return
     */
    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Seller seller) {
        if (seller.getName() == null) {
            seller = sellerService.findById(id).get();
        }

        ModelAndView mv = newSaller(seller);
        return mv;
    }

    /**
     *
     * @param seller
     * @param bindingResult
     * @return
     */
    @PostMapping(value="/{\\d}", params = {"addContact"})
    public ModelAndView addContact(@Valid Seller seller, BindingResult bindingResult) {
        sellerValidation.contact(seller, bindingResult);
        if (bindingResult.hasErrors()) {
            seller.setUpdateContact(true);
            ModelAndView mv = newSaller(seller);
            mv.addObject("sizeContacts", seller.getContacts().size()-1);
            return mv;
        }
        seller = sellerService.save(seller);
        seller.setUpdateContact(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "contato.adicionado.sucesso");
        ModelAndView mv = newSaller(seller);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    /**
     *
     * @param delContact
     * @param seller
     * @param bindingResult
     * @return
     */
    @PostMapping(value="/{\\d}", params={"delContact"})
    public ModelAndView delContact(@RequestParam("delContact") Long delContact, @Valid Seller seller, BindingResult bindingResult) {
        seller.getContacts().removeIf(contact -> contact.getId() == delContact);
        seller.getContacts().removeIf(contact -> contact.getId() == null);
        contactService.delete(delContact, "seller", seller.getId(), seller.getName());
        sellerService.save(seller);
        seller.setUpdateContact(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "contato.removido.sucesso");
        ModelAndView mv = newSaller(seller);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.sellerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
