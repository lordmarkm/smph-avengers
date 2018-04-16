package com.smph.ar.coupon.service.custom;

import org.springframework.web.multipart.MultipartFile;

import com.mynt.core.jpa.service.MyntJpaServiceCustom;
import com.smph.ar.coupon.dto.CouponInfo;
import com.smph.ar.coupon.dto.DashboardStats;
import com.smph.ar.coupon.model.Coupon;

public interface CouponServiceCustom extends MyntJpaServiceCustom<Coupon, CouponInfo> {

    public CouponInfo redeem(String promoCode, String uuid, String email);
    public DashboardStats dashboardStats();
    public String[] addCouponCodes(MultipartFile csv);

}
