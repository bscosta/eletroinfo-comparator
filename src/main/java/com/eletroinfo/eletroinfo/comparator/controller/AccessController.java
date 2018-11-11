package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.UserMenuService;
import com.eletroinfo.eletroinfo.comparator.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public ModelAndView userAccess(@PathVariable("id") Long id, PermissionDto permissionDto, RedirectAttributes redirectAttributes) {
        PermissionDto permissionDtoResult = this.userPermissionService.findByPermissionById(id);
        if (permissionDtoResult.getUserDto() != null) {
            permissionDto = permissionDtoResult;
        } else {
            notificationHandler.getMessage404NotFound();
            ModelAndView mv = new ModelAndView("redirect:/usuario");
            redirectAttributes.addFlashAttribute(notificationHandler.getType().name(), notificationHandler.getMessages());
            return mv;
        }

        return new ModelAndView("access/save", "permissionDto", permissionDto);
    }
}
