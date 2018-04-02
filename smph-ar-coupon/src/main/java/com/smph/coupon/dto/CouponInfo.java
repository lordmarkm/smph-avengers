package com.smph.coupon.dto;

import com.mynt.core.dto.BaseInfo;
import com.smph.coupon.model.RedemptionStatus;

public class CouponInfo extends BaseInfo {

    private String promoCode;
    private String redeemerUuid;
    private RedemptionStatus status = RedemptionStatus.VALID;

    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public String getRedeemerUuid() {
        return redeemerUuid;
    }
    public void setRedeemerUuid(String redeemerUuid) {
        this.redeemerUuid = redeemerUuid;
    }
    public RedemptionStatus getStatus() {
        return status;
    }
    public void setStatus(RedemptionStatus status) {
        this.status = status;
    }

}
