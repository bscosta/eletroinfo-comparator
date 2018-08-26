package com.eletroinfo.eletroinfo.comparator.controller;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import com.eletroinfo.eletroinfo.comparator.notification.NotificationHandler;
import com.eletroinfo.eletroinfo.comparator.service.SellerService;
import com.eletroinfo.eletroinfo.comparator.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Bruno Costa
 */

@Controller
@RequestMapping(value = "/vendedor")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private NotificationHandler notificationHandler;

    @GetMapping
    public ModelAndView list(SellerFilter sellerFilter) {
        ModelAndView mv =  new ModelAndView("layout/seller/list");
        mv.addObject("pageData", new PageImpl(new ArrayList()));
        return mv;
    }

    @GetMapping(value = "/buscar")
    public ModelAndView find(SellerFilter sellerFilter, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
        PageWrapper<Seller> productPage = new PageWrapper<>(this.sellerService.findByParameters(sellerFilter, pageable), httpServletRequest);
        ModelAndView mv = new ModelAndView("layout/seller/list");
        mv.addObject("pageData", productPage);
        mv.addObject("request", httpServletRequest);
        return mv;
    }
}
