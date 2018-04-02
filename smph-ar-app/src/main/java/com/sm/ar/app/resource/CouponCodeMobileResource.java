package com.sm.ar.app.resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.ar.app.dto.RequestCouponCodeRequest;
import com.sm.ar.app.dto.RequestCouponCodeResponse;

@RestController
@RequestMapping("/mobile/coupon-code")
public class CouponCodeMobileResource {

    private static final Logger LOG = LoggerFactory.getLogger(CouponCodeMobileResource.class);

    @GetMapping
    public ResponseEntity<RequestCouponCodeResponse> requestCouponCode(RequestCouponCodeRequest request) {
        LOG.debug("Coupon code request received. request={}", request);

        RequestCouponCodeResponse response = new RequestCouponCodeResponse();
        response.setPromoCode(request.getPromoCode());
        response.setUuid(request.getUuid());
        response.setCouponCode(RandomStringUtils.randomAlphanumeric(10));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
