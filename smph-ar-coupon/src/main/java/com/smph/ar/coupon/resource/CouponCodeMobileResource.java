package com.smph.ar.coupon.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smph.ar.coupon.dto.CouponInfo;
import com.smph.ar.coupon.dto.RequestCouponCodeRequest;
import com.smph.ar.coupon.dto.RequestCouponCodeResponse;
import com.smph.ar.coupon.service.CouponService;

@RestController
@RequestMapping("/mobile/coupon-code")
public class CouponCodeMobileResource {

    private static final Logger LOG = LoggerFactory.getLogger(CouponCodeMobileResource.class);

    @Autowired
    private CouponService couponService;

    @GetMapping
    public ResponseEntity<RequestCouponCodeResponse> requestCouponCode(RequestCouponCodeRequest request) {
        LOG.debug("Coupon code request received. request={}", request);
        RequestCouponCodeResponse response = new RequestCouponCodeResponse();

        CouponInfo coupon = couponService.redeem(request.getPromoCode(), request.getUuid(), request.getEmail());
        if (null == coupon) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        response.setPromoCode(request.getPromoCode());
        response.setUuid(request.getUuid());
        response.setCouponCode(coupon.getCouponCode());
        response.setReward(coupon.getReward());
        response.setEmail(coupon.getRedeemerEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
