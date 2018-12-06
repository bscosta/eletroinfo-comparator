package com.eletroinfo.eletroinfo.comparator.interceptors;

import com.eletroinfo.eletroinfo.comparator.infra.RunTimeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component
public class RunTimeInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RunTimeSession runTimeSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //System.out.println(Calendar.getInstance(request.getLocale()).getTimeZone().toZoneId());
            runTimeSession.setZoneId(Calendar.getInstance(request.getLocale()).getTimeZone().toZoneId());
        } catch (Exception e) {

        }

        return super.preHandle(request, response, handler);
    }

}
