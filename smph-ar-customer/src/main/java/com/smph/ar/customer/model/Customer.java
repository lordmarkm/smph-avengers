package com.smph.ar.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.mynt.core.jpa.model.BaseEntity;

@Entity
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * This ID provided by mobile app
     */
    @Column(name = "customer_code", nullable = false)
    private String code;

    @Column(name = "android_user")
    @Type(type = "yes_no")
    private boolean androidUser;

    @Column(name = "ios_user")
    @Type(type = "yes_no")
    private boolean iosUser;

    public boolean isAndroidUser() {
        return androidUser;
    }

    public void setAndroidUser(boolean androidUser) {
        this.androidUser = androidUser;
    }

    public boolean isIosUser() {
        return iosUser;
    }

    public void setIosUser(boolean iosUser) {
        this.iosUser = iosUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
