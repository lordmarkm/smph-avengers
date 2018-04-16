package com.smph.ar.coupon.dto;

import org.springframework.core.style.ToStringCreator;

public class RequestCouponCodeRequest {

    private String promoCode;
    private String uuid;
    private String email;

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("promoCode", promoCode)
                .append("uuid", uuid)
                .append("email", email)
                .toString();
    }
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
