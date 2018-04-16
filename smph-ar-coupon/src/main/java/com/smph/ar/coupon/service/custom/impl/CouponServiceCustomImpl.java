package com.smph.ar.coupon.service.custom.impl;

import static com.smph.ar.coupon.model.QCoupon.coupon;
import static com.smph.ar.coupon.model.RedemptionStatus.INVALID;
import static com.smph.ar.coupon.model.RedemptionStatus.REDEEMED;
import static com.smph.ar.coupon.model.RedemptionStatus.VALID;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mynt.core.jpa.service.MyntJpaServiceCustomImpl;
import com.opencsv.CSVReader;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.smph.ar.coupon.dto.CouponInfo;
import com.smph.ar.coupon.dto.DashboardStats;
import com.smph.ar.coupon.model.Coupon;
import com.smph.ar.coupon.model.RedemptionStatus;
import com.smph.ar.coupon.service.CouponService;
import com.smph.ar.coupon.service.custom.CouponServiceCustom;

public class CouponServiceCustomImpl extends MyntJpaServiceCustomImpl<Coupon, CouponInfo, CouponService>
    implements CouponServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(CouponServiceCustomImpl.class);

    @Override
    public CouponInfo redeem(String promoCode, String uuid, String email) {
        List<Coupon> redeemed = (List<Coupon>) repo.findAll(coupon.promoCode.eq(promoCode).and(coupon.redeemerUuid.eq(uuid)));
        if (redeemed.size() > 0) {
            Coupon oldCoupon = Iterables.getFirst(redeemed, null);
            LOG.warn("Duplicate redemption attempt. Returning old coupon code. code={}, promoCode={}, uuid={}", oldCoupon.getCouponCode(), promoCode, uuid);
            return toDto(oldCoupon);
        }

        BooleanExpression predicate = coupon.status.eq(VALID)
                .and(coupon.promoCode.eq(promoCode));
        PageRequest page = new PageRequest(0, 1, Direction.ASC, "priority");
        Page<Coupon> results = repo.findAll(predicate, page);

        LOG.debug("Found valid coupons for redemption: {}", results);
        if (results.getContent().size() < 1) {
            return null;
        } else {
            Coupon redeemedCoupon = Iterables.getFirst(results.getContent(), null);
            redeemedCoupon.setStatus(REDEEMED);
            redeemedCoupon.setRedeemerUuid(uuid);
            redeemedCoupon.setRedeemerEmail(email);
            return toDto(redeemedCoupon);
        }
    }

    @Override
    public DashboardStats dashboardStats() {
        DashboardStats stats = new DashboardStats();
        stats.setCouponCodeInvalid((int) repo.count(coupon.status.eq(INVALID)));
        stats.setCouponCodeRedeemed((int) repo.count(coupon.status.eq(REDEEMED)));
        stats.setCouponCodeTotal((int) repo.count());
        stats.setCouponCodeValid((int) repo.count(coupon.status.eq(VALID)));
        return stats;
    }

    @Override
    public String[] addCouponCodes(MultipartFile csv) {

        CSVReader reader = null;
        int line = 0;
        try {
            List<String> results = Lists.newArrayList();
            reader = new CSVReader(new InputStreamReader(csv.getInputStream()));
            List<Coupon> newCoupons = Lists.newArrayList();

            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                line++;
                String couponCode = nextLine[0].trim();
                if (repo.findByCouponCode(couponCode).isPresent()) {
                    results.add("Coupon code " + couponCode + " already exists. Ignored.");
                    continue;
                }

                Coupon newCoupon = new Coupon();
                newCoupon.setCouponCode(couponCode);
                newCoupon.setReward(nextLine[1].trim());
                newCoupon.setPriority(Integer.parseInt(nextLine[2].trim()));
                newCoupon.setStatus(RedemptionStatus.VALID);
                newCoupon.setPromoCode("avengers");

                newCoupons.add(newCoupon);
            }

            results.add("Success. New coupon codes: " + newCoupons.size());
            repo.save(newCoupons);

            return results.toArray(new String[] {});
        } catch (Exception e) {
            LOG.error("Error reading file", e);
            return new String[] {"Error reading file: " + e.getMessage(), "Line number: " + line};
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
