package com.smph.ar.coupon.service;

import java.util.Optional;

import com.mynt.core.jpa.service.MyntJpaService;
import com.smph.ar.coupon.model.Coupon;
import com.smph.ar.coupon.service.custom.CouponServiceCustom;

public interface CouponService extends MyntJpaService<Coupon>, CouponServiceCustom {

    Optional<Coupon> findByCouponCode(String code);

}
