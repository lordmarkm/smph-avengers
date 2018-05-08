package com.smph.ar.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.mynt.core.jpa.model.BaseEntity;

@Entity(name = "promo_points")
public class PromoPoints extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    private Customer customer;

    @Column(name = "promo_code", nullable = false)
    private String promoCode;

    @Column(name = "points", nullable = false)
    private long points = 0;

    @Column(name = "secondary_points")
    private long secondaryPoints = 0;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public long getSecondaryPoints() {
        return secondaryPoints;
    }

    public void setSecondaryPoints(long secondaryPoints) {
        this.secondaryPoints = secondaryPoints;
    }

}
