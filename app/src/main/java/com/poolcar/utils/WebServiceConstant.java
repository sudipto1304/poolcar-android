package com.poolcar.utils;

import com.poolcar.BuildConfig;

public class WebServiceConstant {

    public static String CONTEXT_PATH;
    public static String APPLICATION_AUTH_URL="app/auth";
    public static String USER_REGISTRATION_URL="manageuser/register";
    public static String VALIDATE_OTP="app/validateOTP";
    public static String VALIDATE_OTP_ERROR="app/validateOTP/error";


        static{
            if(BuildConfig.BUILD_VARIANT.equals("mock")){
                CONTEXT_PATH = "https://private-ce0e2-poolcarmockservice.apiary-mock.com/";
            }
        }

}
