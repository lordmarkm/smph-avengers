package com.smph.ar.coupon.dto;

public class DashboardStats {

    private int couponCodeValid;
    private int couponCodeInvalid;
    private int couponCodeRedeemed;
    private int couponCodeTotal;

    public int getCouponCodeValid() {
        return couponCodeValid;
    }
    public void setCouponCodeValid(int couponCodeValid) {
        this.couponCodeValid = couponCodeValid;
    }
    public int getCouponCodeInvalid() {
        return couponCodeInvalid;
    }
    public void setCouponCodeInvalid(int couponCodeInvalid) {
        this.couponCodeInvalid = couponCodeInvalid;
    }
    public int getCouponCodeRedeemed() {
        return couponCodeRedeemed;
    }
    public void setCouponCodeRedeemed(int couponCodeRedeemed) {
        this.couponCodeRedeemed = couponCodeRedeemed;
    }
    public int getCouponCodeTotal() {
        return couponCodeTotal;
    }
    public void setCouponCodeTotal(int couponCodeTotal) {
        this.couponCodeTotal = couponCodeTotal;
    }

}
