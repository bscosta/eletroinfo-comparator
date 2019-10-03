package com.eletroinfo.eletroinfo.comparator.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author Bruno Costa
 */

@Component
public class Securityhandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserLogged userLogged = (UserLogged) authentication.getPrincipal();
        userLogged.setIpAddr(request.getRemoteAddr());
        userLogged.setZoneId(Calendar.getInstance(request.getLocale()).getTimeZone().toZoneId());

        response.sendRedirect("/");
    }
}
