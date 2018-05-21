package com.poolcar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.poolcar.PCApplication;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.model.AppData;
import com.poolcar.model.UserProfileData;
import com.poolcar.utils.AppConstant;

import org.json.JSONObject;

import java.io.Serializable;

public class OTPService extends Service {

    private static final String TAG = OTPService.class.getName();

    private String otpString;
    private transient JSONObject jsonObj;

    public OTPService(WebServiceResponseListener listener){
        AppData.getInstance().setListener(listener);
    }

    public void launchOTP(String otpString, JSONObject jsonObj){
        this.otpString = otpString;
        this.jsonObj = jsonObj;
        Intent intent = new Intent(AppConstant.OTP_INTENT_FILTER);
        intent.putExtra(AppConstant.DATA_OTP_STRING, otpString);
        intent.putExtra(AppConstant.DATA_OTP_RETURN_RESPONSE, jsonObj.toString());
        Log.d(TAG, "Lunching OTP Service");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PCApplication.getContext().startActivity(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
