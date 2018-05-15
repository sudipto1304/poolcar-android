package com.poolcar.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.model.AppData;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.WebServiceConstant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebServiceProvider {
    private static final String TAG = WebServiceProvider.class.getName();
    public static void sendPost(Context context, String URL, JSONObject request, WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.POST,
                WebServiceConstant.CONTEXT_PATH+URL,
                request,
                listener,
                listener
        ){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer "+ AppData.getInstance().getAuthToken());
                headers.put("client_id", AppConstant.ANDROID_APP_ID);
                Log.d(TAG, "Request Headers: "+headers);
                return headers;
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }


    public static void sendGet(Context context, String URL, WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.GET,
                WebServiceConstant.CONTEXT_PATH+URL,
                null,
                listener,
                listener
        ){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer "+ AppData.getInstance().getAuthToken());
                headers.put("client_id", AppConstant.ANDROID_APP_ID);
                Log.d(TAG, "Request Headers: "+headers);
                return headers;
            }
        };

        requestQueue.add(objectRequest);
    }


    public static void sendPut(Context context, String URL, JSONObject request, WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.PUT,
                WebServiceConstant.CONTEXT_PATH+URL,
                request,
                listener,
                listener
        ){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer "+ AppData.getInstance().getAuthToken());
                headers.put("client_id", AppConstant.ANDROID_APP_ID);
                Log.d(TAG, "Request Headers: "+headers);
                return headers;
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }


    public static void sendDelete(Context context, String URL, JSONObject request, WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.DELETE,
                WebServiceConstant.CONTEXT_PATH+URL,
                request,
                listener,
                listener
        ){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "bearer "+ AppData.getInstance().getAuthToken());
                headers.put("client_id", AppConstant.ANDROID_APP_ID);
                Log.d(TAG, "Request Headers: "+headers);
                return headers;
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }
}
