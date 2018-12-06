package com.eletroinfo.eletroinfo.comparator.auth.controller;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.enumeration.TypeMessage;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.security.UserLogged;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserMenuService;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/acessos")
public class AccessController {

    @Autowired
    private NotificationHandler notificationHandler;

    @Autowired
    private UserMenuService userMenuService;

    @Autowired
    private UserPermissionService userPermissionService;

    @GetMapping(value = "/usuario/{id}")
    public ModelAndView userAccess(@PathVariable("id") Long id, PermissionDto permissionDto, @AuthenticationPrincipal UserLogged userLogged, RedirectAttributes redirectAttributes) {
        List<Long> longList = new ArrayList<>();
        PermissionDto permissionDtoResult = this.userPermissionService.findByPermissionById(id);
        if (permissionDtoResult.getUserDto() != null) {
            permissionDto = permissionDtoResult;
        } else {
            notificationHandler.getMessage404NotFound();
            ModelAndView mv = new ModelAndView("redirect:/usuario");
            redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
            return mv;
        }
        permissionDto.setListSavedIds(permissionDto.getListConsultedsMenusChildIds());
        Set<Long> menusChildIds = new HashSet<>();
        menusChildIds.addAll(permissionDto.getListConsultedsMenusChildIds());
        for (Long menuChildId : userLogged.getPermissionDto().getListConsultedsMenusChildIds()) {
            menusChildIds.removeIf(aLong -> aLong.equals(menuChildId));
        }
        ModelAndView mv = new ModelAndView("access/save", "permissionDto", permissionDto);
        if (permissionDto.getUserDto().getUserType() <= 1) {
            notificationHandler.addMessageinternationalized(TypeMessage.message_warn, "usuario.developer");
            mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        }
        mv.addObject("accessUserLogged", userLogged.getPermissionDto().getParentMenusDto());
        mv.addObject("accessUserConsulted", menusChildIds);
        return mv;
    }

    @PostMapping(value = "/usuario/{\\+d}")
    public ModelAndView saveUserAccess(PermissionDto permissionDto, RedirectAttributes redirectAttributes) {
        permissionDto = this.userMenuService.saveUserAccess(permissionDto);

        notificationHandler.addMessageSucessEdit();

        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(permissionDto);
        return new ModelAndView("redirect:/acessos/usuario/"+permissionDto.getUserDto().getId());
    }
}
