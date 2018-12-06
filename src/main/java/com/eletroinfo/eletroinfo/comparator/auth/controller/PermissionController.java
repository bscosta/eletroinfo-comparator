package com.eletroinfo.eletroinfo.comparator.auth.controller;

import com.eletroinfo.eletroinfo.comparator.dto.MenuChildDto;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/permissao")
public class PermissionController {

    @Autowired
    private NotificationHandler notificationHandler;

    @Autowired
    private UserPermissionService userPermissionService;

    @Autowired
    private UserMenuService userMenuService;

    @GetMapping(value = "/usuario/{id}")
    public ModelAndView userPermission(@PathVariable("id") Long id, @AuthenticationPrincipal UserLogged userLogged, PermissionDto permissionDto, RedirectAttributes redirectAttributes) {
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

        Set<MenuChildDto> menuChildDtoSet = new HashSet<>();
        for (Long childMenuId : permissionDto.getListConsultedsMenusChildIds()) {
            for (MenuChildDto menuChildDto : userLogged.getPermissionDto().getParentMenusDto()
                    .stream().map(parentMenuDto -> parentMenuDto.getMenuChildDtoList())
                        .flatMap(Collection::stream).collect(Collectors.toSet())) {
                if (menuChildDto.getId().equals(childMenuId)) {
                    menuChildDtoSet.add(menuChildDto);
                }
            }
        }

        permissionDto.setListSavedMenusFeaturesIds(permissionDto.getListConsultedMenusFeaturesIds());
        Set<String> menusFeaturesIds = new HashSet<>();
        menusFeaturesIds.addAll(permissionDto.getListConsultedMenusFeaturesIds());
        for (String menuFeature : userLogged.getPermissionDto().getListConsultedMenusFeaturesIds()) {
            menusFeaturesIds.removeIf(aLong -> aLong.equals(menuFeature));
        }

        ModelAndView mv = new ModelAndView("permission/save", "permissionDto", permissionDto);
        if (permissionDto.getUserDto().getUserType() <= 1) {
            notificationHandler.addMessageinternationalized(TypeMessage.message_warn, "usuario.developer");
            mv.addObject(notificationHandler.getType().name(), notificationHandler.getMessages());
        }
        mv.addObject("permissionUserLogged", menuChildDtoSet);
        mv.addObject("permissionUserConsulted", menusFeaturesIds);
        return mv;
    }

    @PostMapping(value = "/usuario/{\\+d}")
    public ModelAndView saveUserAccess(PermissionDto permissionDto, RedirectAttributes redirectAttributes) {
        permissionDto = this.userMenuService.saveUserPermission(permissionDto);

        notificationHandler.addMessageSucessEdit();

        redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
        redirectAttributes.addFlashAttribute(permissionDto);
        return new ModelAndView("redirect:/permissao/usuario/"+permissionDto.getUserDto().getId());
    }

}
