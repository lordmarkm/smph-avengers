package com.smph.ar.customer.resource;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smph.ar.customer.dto.PromoPointsInfo;
import com.smph.ar.customer.service.PromoPointsService;
import com.smph.ar.shared.enums.DeviceType;

@RestController
@RequestMapping("/mobile/promo-points")
@Validated
public class PromoPointsMobileResource {

    private static final Logger LOG = LoggerFactory.getLogger(PromoPointsMobileResource.class);

    @Autowired
    private PromoPointsService service;

    @PutMapping
    public ResponseEntity<PromoPointsInfo> put(@RequestParam String customerCode,
            @RequestParam DeviceType deviceType,
            @RequestParam String promoCode,
            @RequestParam @Min(0) @Max(1000) long addedPoints,
            @RequestParam @Min(0) @Max(1000) long addedSecondaryPoints) {
        LOG.info("PromoPointsMobileResource::put({}, {}, {}, {}, {})", customerCode, deviceType, promoCode, addedPoints,
                addedSecondaryPoints);
        return service.addPoints(customerCode, deviceType, promoCode, addedPoints, addedSecondaryPoints)
                .map(p -> new ResponseEntity<>(p, OK))

                //404 when service returns empty optional, but this never happens
                .orElse(new ResponseEntity<>(null, NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<PromoPointsInfo> get(@RequestParam String customerCode,
            @RequestParam DeviceType deviceType,
            @RequestParam String promoCode) {
        LOG.info("PromoPointsMobileResource::get({}, {}, {})", customerCode, deviceType, promoCode);
        return service.getPoints(customerCode, deviceType, promoCode)
                .map(p -> new ResponseEntity<>(p, OK))

                //404 when customer + promo code combination not found
                .orElse(new ResponseEntity<>(null, NOT_FOUND));
    }

}
