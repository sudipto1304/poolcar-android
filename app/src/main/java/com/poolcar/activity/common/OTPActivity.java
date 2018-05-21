package com.poolcar.activity.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.callbacks.ResponseListener;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.component.OTPInutField;
import com.poolcar.model.AppData;
import com.poolcar.model.OTPValidationRequest;
import com.poolcar.service.OTPService;
import com.poolcar.service.WebServiceManager;
import com.poolcar.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class OTPActivity extends OuterBaseActivity {
    private static final String TAG = OTPActivity.class.getName();
    private WebServiceResponseListener listener;
    private JSONObject jsonObj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp, R.string.OTP_Title, true);
        final OTPInutField otpField = findViewById(R.id.otpInput);
        this.listener = AppData.getInstance().getListener();
        AppData.getInstance().setListener(null);
        try {
            this.jsonObj = new JSONObject(getIntent().getStringExtra(DATA_OTP_RETURN_RESPONSE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "otpString received:::"+getIntent().getStringExtra(DATA_OTP_STRING));
        StringTokenizer token = new StringTokenizer(getIntent().getStringExtra(DATA_OTP_STRING), "|");
        final List<String> otpData = new ArrayList<>();
        while(token.hasMoreTokens()){
            otpData.add(token.nextToken());
        }
        Log.d(TAG, "otpData:::"+otpData);
        String phoneNumber = AppUtils.formatNumber(otpData.get(1), otpData.get(0));
        String phoneCode = AppUtils.getCountryPhoneCodeByCountry(this, otpData.get(1));
        TextView mobileNumber = findViewById(R.id.mobileNumber);
        mobileNumber.setText("+"+phoneCode+" "+phoneNumber);

        Button submitOtp = findViewById(R.id.submitOTP);
        submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otpField.getOTP();
                Log.d(TAG, "User given OTP is::"+otp);
                if(otp==null || (otp!=null && otp.length()!=5)){
                    return;
                }
                OTPValidationRequest otpRequest = new OTPValidationRequest();
                otpRequest.setUserid(otpData.get(2));
                otpRequest.setCountryCode(otpData.get(1));
                otpRequest.setPhoneNumber(otpData.get(0));
                otpRequest.setOtp(otp);
                Log.d(TAG, "calling OTP validate service");
                WebServiceManager service = new WebServiceManager(OTPActivity.this);
                showDarkLoader();
                service.validateOTP(otpRequest, new ResponseListener() {
                    @Override
                    public void onResponseReceived(JSONObject response) {
                        listener.onResponseReceived(jsonObj);
                    }

                    @Override
                    public void onErrorReceived(int responseCode) {
                        hideLoader();
                    }

                    @Override
                    public void onErrorReceived(int responseCode, JSONObject response) {

                    }
                });

            }
        });

    }
}
