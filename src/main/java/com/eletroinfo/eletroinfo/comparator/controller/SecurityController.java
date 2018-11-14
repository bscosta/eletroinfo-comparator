package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(value = "/login")
    public String login(@AuthenticationPrincipal PermissionDto permissionDto) {
        if (permissionDto != null) {
            return "redirect:/usuario";
        }

        return "login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
