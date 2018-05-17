package com.poolcar.model;

import java.io.Serializable;



public class Address implements Serializable{

    private String addressText;

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }
}
