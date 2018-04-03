package com.smph.ar.coupon.service.custom.impl;

import static com.smph.ar.coupon.model.RedemptionStatus.REDEEMED;
import static com.smph.ar.coupon.model.RedemptionStatus.VALID;
import static com.smph.ar.coupon.model.QCoupon.coupon;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Iterables;
import com.mynt.core.jpa.service.MyntJpaServiceCustomImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.smph.ar.coupon.dto.CouponInfo;
import com.smph.ar.coupon.model.Coupon;
import com.smph.ar.coupon.service.CouponService;
import com.smph.ar.coupon.service.custom.CouponServiceCustom;

public class CouponServiceCustomImpl extends MyntJpaServiceCustomImpl<Coupon, CouponInfo, CouponService>
    implements CouponServiceCustom {

    @PersistenceUnit
    private EntityManager em;

    @Override
    public CouponInfo redeem(String promoCode, String uuid) {
        BooleanExpression predicate = coupon.status.eq(VALID);
        PageRequest page = new PageRequest(1, 1);
        Page<Coupon> results = repo.findAll(predicate, page);

        if (results.getSize() < 1) {
            return null;
        } else {
            Coupon redeemedCoupon = Iterables.getFirst(results.getContent(), null);
            redeemedCoupon.setStatus(REDEEMED);
            redeemedCoupon.setRedeemerUuid(uuid);
            return toDto(redeemedCoupon);
        }
    }

}
