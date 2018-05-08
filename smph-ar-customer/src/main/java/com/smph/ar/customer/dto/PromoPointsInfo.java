package com.smph.ar.customer.dto;

import com.mynt.core.dto.BaseInfo;

public class PromoPointsInfo extends BaseInfo {

    private String customerCode;
    private String promoCode;
    private long points;
    private long secondaryPoints;

    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public long getPoints() {
        return points;
    }
    public void setPoints(long points) {
        this.points = points;
    }
    public String getCustomerCode() {
        return customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    public long getSecondaryPoints() {
        return secondaryPoints;
    }
    public void setSecondaryPoints(long secondaryPoints) {
        this.secondaryPoints = secondaryPoints;
    }

}
