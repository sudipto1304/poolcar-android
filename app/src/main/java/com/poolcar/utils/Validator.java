package com.poolcar.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.regex.Pattern;

public class Validator implements AppConstant{

    public static boolean validatePhone(String phoneNumber){
        return phoneNumber.matches(PHONE_VALIDATOR_REGEX);
    }


    public static boolean validateEmailId(String emailId){
        final Pattern EMAIL_REGEX = Pattern.compile(AppConstant.EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(emailId).matches();
    }

    public static boolean validateMinLength(String text, int length){
        return text.length()>=length;
    }

    public static boolean validateMaxLength(String text, int length){
        return text.length()<=length;
    }

}
