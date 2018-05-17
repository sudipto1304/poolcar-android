package com.poolcar.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.poolcar.R;

public class OTPActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp, R.string.OTP_Title, true);
    }
}
