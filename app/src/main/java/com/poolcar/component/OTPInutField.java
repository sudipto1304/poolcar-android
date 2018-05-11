package com.poolcar.component;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.poolcar.R;

import org.apache.commons.lang3.StringUtils;

public class OTPInutField extends RelativeLayout{


    private EditText otp1;
    private EditText otp2;
    private EditText otp3;
    private EditText otp4;
    private EditText otp5;
    private EditText ofFocus;


    public OTPInutField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }



    private void init(final Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.otp_input_fields, this);
        changeFocus(context);
    }

    public String getOTP(){
        String otp="";
        if(StringUtils.isNotEmpty(otp1.getText().toString())){
            otp+=otp1.getText().toString();
        }
        if(StringUtils.isNotEmpty(otp2.getText().toString())){
            otp+=otp2.getText().toString();
        }
        if(StringUtils.isNotEmpty(otp3.getText().toString())){
            otp+=otp3.getText().toString();
        }
        if(StringUtils.isNotEmpty(otp4.getText().toString())){
            otp+=otp4.getText().toString();
        }
        if(StringUtils.isNotEmpty(otp5.getText().toString())){
            otp+=otp5.getText().toString();
        }
        return otp;

    }


    private void changeFocus(final Context context){
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        ofFocus = findViewById(R.id.ofFocusButton);


        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otp1.getText().toString().length()==1){
                    otp1.clearFocus();
                    otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otp2.getText().toString().length()==1){
                    otp2.clearFocus();
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otp3.getText().toString().length()==1){
                    otp3.clearFocus();
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otp4.getText().toString().length()==1){
                    otp4.clearFocus();
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(otp5.getText().toString().length()==1){
                    otp5.clearFocus();
                    ofFocus.requestFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(ofFocus.getWindowToken(), 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
