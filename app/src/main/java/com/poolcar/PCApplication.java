package com.poolcar;

import android.app.Application;

import com.poolcar.utils.TypefaceUtil;

/**
 * Created by sudip on 4/18/2018.
 */

public class PCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
    }
}
