package com.poolcar.model;

import java.io.Serializable;

public class ProfileRequest implements Serializable{

    private String userId;
    private String emailId;
    private String contactNumber;

    public String getUserId() {
        return userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
