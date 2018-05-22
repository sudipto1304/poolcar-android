package com.poolcar.model;

import java.io.Serializable;

public class Status implements Serializable{
    private boolean isAuthRequired;
    private String responseCode;
    private String responseMessage;
    private String OTPString;
    private String responseMessageDetails;

    public boolean isAuthRequired() {
        return isAuthRequired;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getOTPString() {
        return OTPString;
    }

    public String getResponseMessageDetails() {
        return responseMessageDetails;
    }

    public void setAuthRequired(boolean authRequired) {
        isAuthRequired = authRequired;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setOTPString(String OTPString) {
        this.OTPString = OTPString;
    }

    public void setResponseMessageDetails(String responseMessageDetails) {
        this.responseMessageDetails = responseMessageDetails;
    }
}
