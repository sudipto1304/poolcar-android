package com.poolcar.utils;

import com.poolcar.BuildConfig;
import com.poolcar.activity.LoadingActivity;

public class WebServiceConstant {

    public static String CONTEXT_PATH;
    public static String APPLICATION_AUTH_URL="app/auth";


        static{
            if(BuildConfig.BUILD_VARIANT.equals("mock")){
                CONTEXT_PATH = "https://private-ce0e2-poolcarmockservice.apiary-mock.com/";
            }
        }

}
