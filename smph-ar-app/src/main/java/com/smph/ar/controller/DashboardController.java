package com.smph.ar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smph.ar.coupon.service.CouponService;
import com.smph.ar.customer.service.PromoPointsService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private CouponService couponService;

    @Autowired
    private PromoPointsService promoPointsService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("dashboard", couponService.dashboardStats());
        model.addAttribute("promos", promoPointsService.dashboardStats());

        LOG.debug("Dashboard! model={}", model);
        return "dashboard";
    }

}
