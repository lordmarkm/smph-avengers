package com.smph.coupon.service.custom;

import com.mynt.core.jpa.service.MyntJpaServiceCustom;
import com.smph.coupon.dto.CouponInfo;
import com.smph.coupon.model.Coupon;

public interface CouponServiceCustom extends MyntJpaServiceCustom<Coupon, CouponInfo> {

    public CouponInfo redeem(String promoCode, String uuid);

}
