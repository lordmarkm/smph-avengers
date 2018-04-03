package com.smph.ar.coupon.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smph.ar.coupon.service.CouponService;

@Controller
@RequestMapping("/coupon-code")
public class CouponCodeResource {

    @Autowired
    private CouponService couponService;

    @PostMapping("/upload")
    public String upload(RedirectAttributes model, @RequestParam("file") MultipartFile csv) {
        String[] msgs = couponService.addCouponCodes(csv);
        model.addFlashAttribute("msgs", msgs);
        return "redirect:/dashboard";
    }

}
