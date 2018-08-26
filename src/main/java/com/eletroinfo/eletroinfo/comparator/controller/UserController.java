package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import com.eletroinfo.eletroinfo.comparator.filter.UserFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import com.eletroinfo.eletroinfo.comparator.validations.UserValidation;
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

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/usuario")
public class UserController {

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationHandler notificationHandler;

    @GetMapping
    public ModelAndView user(UserFilter userFilter) {
        ModelAndView mv = new ModelAndView("/user/list-user");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        mv.addObject("userType", UserType.values());
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView findUser(UserFilter userFilter, @PageableDefault(size = 7) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<User> userPage = new PageWrapper<>(userService.findByParameters(userFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("/user/list-user");
        mv.addObject("pageData", userPage);
        mv.addObject("request", httpServletRequest);
        mv.addObject("userType", UserType.values());
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView userNew(User user) {
        if (user.isNovo()) {
            user.setActivated(true);
        }
        ModelAndView mv = new ModelAndView("/user/save-user", "user", user);
        mv.addObject("userType", UserType.values());
        return mv;
    }

    @PostMapping({"/novo", "{\\+d}"})
    public ModelAndView salvar(@Valid User user, BindingResult result, RedirectAttributes attributes) {
        if (user.getId() == null) {
            this.userValidation.validateSave(user, result);
        } else {
            this.userValidation.validateUpdate(user, result);
        }

        if (result.hasErrors()) {
            return userNew(user);
        }

        if (user.getId() == null) {
            notificationHandler.addMessageSucessSave();
        } else {
            notificationHandler.addMessageSucessEdit();
        }

        user = this.userService.save(user);

        attributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        attributes.addFlashAttribute(user);
        return new ModelAndView("redirect:/usuario/"+user.getId());
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable Long id, User user) {
        if (user.getLogin() == null) {
            user = this.userService.findById(id).get();
        }

        ModelAndView mv = userNew(user);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
