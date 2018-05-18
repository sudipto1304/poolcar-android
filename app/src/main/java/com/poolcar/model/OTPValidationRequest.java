package com.poolcar.model;

public class OTPValidationRequest extends ClientDetails{

    private String phoneNumber;
    private String otp;
    private String userid;
    private String countryCode;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
