package com.smph.ar.coupon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.mynt.core.jpa.model.BaseEntity;

@Entity(name = "coupon")
public class Coupon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "coupon_code", nullable = false, updatable = false, unique = true)
    private String couponCode;

    @Column(name = "promo_code", nullable = false, updatable = false)
    private String promoCode;

    @Column(name = "redeemer_uuid")
    private String redeemerUuid;

    @Column(name = "redeemer_email")
    private String redeemerEmail;

    @Column(name = "reward", nullable = false)
    private String reward;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "redemption_status")
    @Enumerated(EnumType.STRING)
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

    public String getRedeemerEmail() {
        return redeemerEmail;
    }

    public void setRedeemerEmail(String redeemerEmail) {
        this.redeemerEmail = redeemerEmail;
    }


}
