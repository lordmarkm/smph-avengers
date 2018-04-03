package com.smph.ar.coupon.dto;

import com.mynt.core.dto.BaseInfo;
import com.smph.ar.coupon.model.RedemptionStatus;

public class CouponInfo extends BaseInfo {

    private String couponCode;
    private String promoCode;
    private String redeemerUuid;
    private RedemptionStatus status = RedemptionStatus.VALID;
    private String reward;
    private int priority;

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
    public String getCouponCode() {
        return couponCode;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    public String getReward() {
        return reward;
    }
    public void setReward(String reward) {
        this.reward = reward;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

}
