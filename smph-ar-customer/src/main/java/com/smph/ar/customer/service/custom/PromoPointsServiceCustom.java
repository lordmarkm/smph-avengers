package com.smph.ar.customer.service.custom;

import java.util.Optional;

import com.mynt.core.jpa.service.MyntJpaServiceCustom;
import com.smph.ar.customer.dto.PromoPointsInfo;
import com.smph.ar.customer.model.PromoPoints;
import com.smph.ar.shared.enums.DeviceType;

/**
 * Because of PromoPointsServiceAspect, all public methods in this interface must start with String customerId, DeviceType deviceType !
 * @author mbmartinez
 */
public interface PromoPointsServiceCustom extends MyntJpaServiceCustom<PromoPoints, PromoPointsInfo> {

    Optional<PromoPointsInfo> addPoints(String customerCode, DeviceType deviceType, String promoCode, long additionalPoints);
    Optional<PromoPointsInfo> getPoints(String customerCode, DeviceType deviceType, String promoCode);

}
