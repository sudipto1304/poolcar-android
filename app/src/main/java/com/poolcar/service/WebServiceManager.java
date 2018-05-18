package com.poolcar.service;


import android.content.Context;
import android.os.Parcel;
import android.util.Log;


import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poolcar.callbacks.ResponseListener;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.model.ClientDetails;
import com.poolcar.model.OTPValidationRequest;
import com.poolcar.model.UserProfileData;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.WebServiceConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class WebServiceManager implements AppConstant, Serializable{
    private static final String TAG = WebServiceManager.class.getName();
    private Context context;
    public WebServiceManager(Context context){
        this.context=context;
    }


        public void authoriseApplication(final ResponseListener listener){
            String URL = WebServiceConstant.APPLICATION_AUTH_URL;

            final Gson gson = new GsonBuilder().create();
            String json = gson.toJson(new ClientDetails());
            JSONObject jsonObj=null;
            try {
                jsonObj = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            WebServiceProvider.sendPost(context, URL, jsonObj, new WebServiceResponseListener(){


                @Override
                public void onResponseReceived(JSONObject response) {
                    listener.onResponseReceived(response);
                }

                @Override
                public void onError() {

                }

                @Override
                public void onError(NetworkResponse response) {
                    Log.d(TAG, "HTTP Response Code::"+response.statusCode);
                    listener.onErrorReceived(response.statusCode);
                }
            });
        }



    public void registerUser(UserProfileData request, final ResponseListener listener){
        String URL = WebServiceConstant.USER_REGISTRATION_URL;

        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(request);
        JSONObject jsonObj=null;
        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebServiceProvider.sendPut(context, URL, jsonObj, new WebServiceResponseListener(){
            @Override
            public void onResponseReceived(JSONObject response) {
                listener.onResponseReceived(response);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onError(NetworkResponse response) {
                Log.d(TAG, "HTTP Response Code::"+response.statusCode);
                listener.onErrorReceived(response.statusCode);
            }
        });
    }

    public void validateOTP(OTPValidationRequest request, final ResponseListener listener){
        String URL = WebServiceConstant.VALIDATE_OTP;

        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(request);
        JSONObject jsonObj=null;
        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WebServiceProvider.sendPost(context, URL, jsonObj, new WebServiceResponseListener(){
            @Override
            public void onResponseReceived(JSONObject response) {
                listener.onResponseReceived(response);
            }

            @Override
            public void onError() {

            }

            @Override
            public void onError(NetworkResponse response) {
                Log.d(TAG, "HTTP Response Code::"+response.statusCode);
                listener.onErrorReceived(response.statusCode);
            }
        });
    }


}
