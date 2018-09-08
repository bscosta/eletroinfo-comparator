package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.Provider;
import com.eletroinfo.eletroinfo.comparator.enumeration.FederativeUnit;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.filter.ProviderFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.AddressService;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.service.ProviderService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.validations.ProviderValidation;
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
import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/fornecedor")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private NotificationHandler notificationHandler;

    @Autowired
    private ProviderValidation providerValidation;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ModelAndView list(ProviderFilter providerFilter) {
        ModelAndView mv = new ModelAndView("provider/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(ProviderFilter providerFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Provider> providerPage = new PageWrapper<>(this.providerService.findByParameters(providerFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("provider/list");
        mv.addObject("pageData", providerPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    @GetMapping(value = "/novo")
    public ModelAndView newProvider(Provider provider) {
        ModelAndView mv = new ModelAndView("provider/save", "provider", provider);
        mv.addObject("sizeAddresses", provider.getAddresses().size());
        mv.addObject("sizeContacts", provider.getContacts().size());
        mv.addObject("ufs", FederativeUnit.values());
        return mv;
    }

    @PostMapping(value = {"/novo", "{\\+d}"}, params = {"save"})
    public ModelAndView save(@Valid Provider provider, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        provider.getAddresses().removeIf(address -> address.getId() == null);
        provider.getContacts().removeIf(contact -> contact.getId() == null);
        providerValidation.save(provider, bindingResult);
        if (bindingResult.hasErrors()) {
            return newProvider(provider);
        }
        provider = providerService.save(provider);
        notificationHandler.addMessageSucessSave();
        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(provider);
        return new ModelAndView("redirect:/fornecedor/"+provider.getId());
    }

    @GetMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Provider provider, RedirectAttributes redirectAttributes) {
        if (provider.getName() == null) {
            Optional<Provider> optionalProvider = providerService.findById(id);
            if (!optionalProvider.isPresent()) {
                notificationHandler.addMessageinternationalized(TypeMessage.message_warn, "fornecedor.nao.encontrado");
                redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
                return new ModelAndView("redirect:/fornecedor/");
            }
            provider = optionalProvider.get();
        }

        ModelAndView mv = newProvider(provider);
        return mv;
    }

    @PostMapping(value="/{\\d}", params = {"addAddress"})
    public ModelAndView addAddress(@Valid Provider provider, BindingResult bindingResult) {
        provider.getContacts().removeIf(contact -> contact.getId() == null);
        providerValidation.address(provider, bindingResult);
        if (bindingResult.hasErrors()) {
            provider.setUpdateAddress(true);
            ModelAndView mv = newProvider(provider);
            mv.addObject("sizeAddresses", provider.getAddresses().size()-1);
            return mv;
        }
        provider = providerService.save(provider);
        provider.setUpdateAddress(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "endereco.adicionado.sucesso");
        ModelAndView mv = newProvider(provider);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @PostMapping(value="/{\\d}", params={"delAddress"})
    public ModelAndView delAddress(@RequestParam("delAddress") Long delAddress, @Valid Provider provider, BindingResult bindingResult) {
        provider.getAddresses().removeIf(address -> address.getId() == delAddress || address.getId() == null);
        provider.getContacts().removeIf(contact -> contact.getId() == null);
        contactService.delete(delAddress, "seller", provider.getId(), provider.getName());
        providerService.save(provider);
        provider.setUpdateAddress(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "endereco.removido.sucesso");
        ModelAndView mv = newProvider(provider);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @PostMapping(value="/{\\d}", params = {"addContact"})
    public ModelAndView addContact(@Valid Provider provider, BindingResult bindingResult) {
        provider.getAddresses().removeIf(address -> address.getId() == null);
        providerValidation.contact(provider, bindingResult);
        if (bindingResult.hasErrors()) {
            provider.setUpdateContact(true);
            ModelAndView mv = newProvider(provider);
            mv.addObject("sizeContacts", provider.getContacts().size()-1);
            return mv;
        }
        provider = providerService.save(provider);
        provider.setUpdateContact(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "contato.adicionado.sucesso");
        ModelAndView mv = newProvider(provider);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }

    @PostMapping(value="/{\\d}", params={"delContact"})
    public ModelAndView delContact(@RequestParam("delContact") Long delContact, @Valid Provider provider, BindingResult bindingResult) {
        provider.getContacts().removeIf(contact -> contact.getId() == delContact || contact.getId() == null);
        provider.getAddresses().removeIf(address -> address.getId() == null);
        contactService.delete(delContact, "seller", provider.getId(), provider.getName());
        providerService.save(provider);
        provider.setUpdateContact(true);
        notificationHandler.addMessageinternationalized(TypeMessage.message_sucess, "contato.removido.sucesso");
        ModelAndView mv = newProvider(provider);
        mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        return mv;
    }
}
