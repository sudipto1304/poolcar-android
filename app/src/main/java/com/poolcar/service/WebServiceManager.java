package com.poolcar.service;


import android.content.Context;
import android.util.Log;


import com.android.volley.VolleyError;
import com.poolcar.activity.LoadingActivity;
import com.poolcar.callbacks.ResponseListener;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.utils.WebServiceConstant;

import org.json.JSONObject;

public class WebServiceManager {
    private static final String TAG = WebServiceManager.class.getName();
    private Context context;
    public WebServiceManager(Context context){
        this.context=context;
    }


        public void authoriseApplication(JSONObject request, final ResponseListener listener){
            String URL = WebServiceConstant.APPLICATION_AUTH_URL;
            URL = WebServiceConstant.CONTEXT_PATH+URL;
            WebServiceProvider.sendPost(context, URL, request, new WebServiceResponseListener(){

                @Override
                public void onResponse(Object response) {
                    Log.d(TAG, "Response Received::"+response.toString());
                    listener.onResponseReceived((JSONObject)response);
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error Response Received::"+error.getMessage());
                    listener.onErrorReceived();
                }
            });
        }

}
