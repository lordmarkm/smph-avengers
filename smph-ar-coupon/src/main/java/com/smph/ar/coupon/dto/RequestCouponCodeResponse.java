package com.smph.ar.coupon.dto;

public class RequestCouponCodeResponse {

    private String promoCode;
    private String uuid;
    private String email;
    private String couponCode;
    private String reward;

    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
