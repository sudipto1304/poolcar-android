package com.poolcar.activity.common;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;

import com.poolcar.R;
import com.poolcar.utils.LocationUtils;

public class InnerBaseActivity extends BaseActivity{

    private ViewStub stub;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentView(int layoutId, int title, boolean isDockerRequired) {
        stub = findViewById(R.id.baseLayout);
        stub.setLayoutResource(layoutId);
        stub.inflate();
        if(title!=0)
            getSupportActionBar().setTitle(title);
        else
            getSupportActionBar().hide();

        if(!isDockerRequired){
            if (Build.VERSION.SDK_INT < 16) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }else{
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }else{
            if (Build.VERSION.SDK_INT < 16) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }else{
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }

    }
}
