package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @GetMapping(value = "/login")
    public String login(@AuthenticationPrincipal PermissionDto permissionDto) {
        if (permissionDto != null) {
            return "redirect:/usuario";
        }

        return "login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        return "403";
    }
}
