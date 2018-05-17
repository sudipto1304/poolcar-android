package com.poolcar.service;


import android.content.Context;
import android.util.Log;


import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poolcar.callbacks.ResponseListener;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.model.ClientDetails;
import com.poolcar.model.UserProfileData;
import com.poolcar.utils.WebServiceConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class WebServiceManager {
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

                String json =null;
                JSONObject jsonObj=null;
                @Override
                public void onResponseReceived(NetworkResponse response) {

                    try {
                        json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        jsonObj = new JSONObject(json);
                    }  catch (UnsupportedEncodingException e) {
                        listener.onErrorReceived();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "HTTP Response Code::"+response.statusCode+" Response received::"+json);
                    if(response!=null && response.statusCode==201){
                        listener.onResponseReceived(jsonObj);
                    }
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

            String json =null;
            JSONObject jsonObj=null;
            @Override
            public void onResponseReceived(NetworkResponse response) {

                try {
                    json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    jsonObj = new JSONObject(json);
                }  catch (UnsupportedEncodingException e) {
                    listener.onErrorReceived();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "HTTP Response Code::"+response.statusCode+" Response received::"+json);
                if(response!=null && response.statusCode==201){
                    listener.onResponseReceived(jsonObj);
                }
            }
        });
    }

}
