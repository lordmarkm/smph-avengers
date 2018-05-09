package com.smph.ar.customer.dto;

import org.springframework.core.style.ToStringCreator;

public class SinglePromoDashboardStats {
    private String promoCode;
    private Long androidUsers;
    private Long iosUsers;
    private Long androidPoints;
    private Long iosPoints;

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("promoCode", promoCode)
                .append("android", androidUsers + ":" + androidPoints)
                .append("ios", iosUsers + ":" + iosPoints)
                .toString();
    }
    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public Long getAndroidUsers() {
        return androidUsers;
    }
    public void setAndroidUsers(Long androidUsers) {
        this.androidUsers = androidUsers;
    }
    public Long getIosUsers() {
        return iosUsers;
    }
    public void setIosUsers(Long iosUsers) {
        this.iosUsers = iosUsers;
    }
    public Long getAndroidPoints() {
        return androidPoints;
    }
    public void setAndroidPoints(Long androidPoints) {
        this.androidPoints = androidPoints;
    }
    public Long getIosPoints() {
        return iosPoints;
    }
    public void setIosPoints(Long iosPoints) {
        this.iosPoints = iosPoints;
    }
}