package com.smph.coupon.service.custom.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mynt.core.jpa.service.MyntJpaServiceCustomImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.smph.coupon.dto.CouponInfo;
import com.smph.coupon.model.Coupon;
import com.smph.coupon.service.CouponService;
import com.smph.coupon.service.custom.CouponServiceCustom;
import static com.smph.coupon.model.QCoupon.coupon;
import static com.smph.coupon.model.RedemptionStatus.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
