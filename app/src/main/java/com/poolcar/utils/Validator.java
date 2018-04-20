package com.poolcar.utils;

public class Validator implements AppConstant{

    public boolean validatePhone(String phoneNumber){
        return phoneNumber.matches(PHONE_VALIDATOR_REGEX);
    }

    public boolean validateEmailId(String emailId){
        return emailId.matches(EMAIL_PATTERN);
    }
}
