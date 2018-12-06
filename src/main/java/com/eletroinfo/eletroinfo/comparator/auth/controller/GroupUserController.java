package com.eletroinfo.eletroinfo.comparator.auth.controller;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.GroupUser;
import com.eletroinfo.eletroinfo.comparator.auth.filter.GroupUserFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.auth.service.GroupUserService;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.auth.validation.GroupUserValidation;
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

@Controller
@RequestMapping(value = "/grupoUsuarios")
public class GroupUserController {

    @Autowired
    private GroupUserService groupUserService;

    @Autowired
    private GroupUserValidation groupUserValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationHandler notificationHandler;

    @GetMapping
    public ModelAndView list(GroupUserFilter groupUserFilter) {
        ModelAndView mv = new ModelAndView("group-user/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(GroupUserFilter groupUserFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<GroupUser> providerPage = new PageWrapper<>(this.groupUserService.findByParameters(groupUserFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("group-user/list");
        mv.addObject("pageData", providerPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView groupUserNew(GroupUser groupUser) {
        ModelAndView mv = new ModelAndView("/group-user/save", "groupUser", groupUser);
        mv.addObject("usersGroup", groupUser.getListUsers());
        return mv;
    }

    @PostMapping({"/novo", "{\\+d}"})
    public ModelAndView save(@Valid GroupUser groupUser, BindingResult bindingResult, RedirectAttributes attributes){
        if (groupUser.isNovo()) {
            this.groupUserValidation.save(groupUser, bindingResult);
        }

        if (bindingResult.hasErrors()) {
            return groupUserNew(groupUser);
        }

        if (groupUser.isNovo()) {
            notificationHandler.addMessageSucessSave();
        } else {
            notificationHandler.addMessageSucessEdit();
        }

        groupUser = this.groupUserService.save(groupUser);

        attributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        attributes.addFlashAttribute("groupUser", groupUser);
        return new ModelAndView("redirect:/grupoUsuarios/"+groupUser.getId());
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") GroupUser groupUser) {
        if (groupUser.getName() == null) {
            groupUser = this.groupUserService.findById(groupUser.getId()).get();
        }
        ModelAndView mv = groupUserNew(groupUser);
        mv.addObject(groupUser);
        return mv;
    }
}
