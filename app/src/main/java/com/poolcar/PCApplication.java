package com.poolcar;

import android.app.Application;
import android.content.Context;


import com.poolcar.model.AppData;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.TypefaceUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by sudip on 4/18/2018.
 */

public class PCApplication extends Application {


    static{
        if(StringUtils.isEmpty(AppData.getInstance().getClientId())) {
            UUID uuid = UUID.randomUUID();
            AppData.getInstance().setClientId(AppConstant.ANDROID_APP_ID + "_" + uuid.toString());
        }
    }

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }



    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/helvetica.ttf"); // font from assets: "assets/fonts/roboto-regular.ttf
    }
}
