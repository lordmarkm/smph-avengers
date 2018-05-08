package com.smph.ar.customer.service;

import java.util.Optional;

import com.mynt.core.jpa.service.MyntJpaService;
import com.smph.ar.customer.model.PromoPoints;
import com.smph.ar.customer.service.custom.PromoPointsServiceCustom;

public interface PromoPointsService extends MyntJpaService<PromoPoints>, PromoPointsServiceCustom {

    Optional<PromoPoints> findByCustomerCodeAndPromoCode(String customerCode, String promoCode);

}
