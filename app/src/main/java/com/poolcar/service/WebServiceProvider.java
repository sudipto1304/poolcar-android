package com.poolcar.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.model.AppData;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.WebServiceConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebServiceProvider {
    private static final String TAG = WebServiceProvider.class.getName();
    public static void sendPost(Context context, String URL, JSONObject request, final WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.POST,
                WebServiceConstant.CONTEXT_PATH+URL,
                request,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
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
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                listener.onResponseReceived(response);
                return super.parseNetworkResponse(response);
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }


    public static void sendGet(Context context, String URL, final WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.GET,
                WebServiceConstant.CONTEXT_PATH+URL,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
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
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                listener.onResponseReceived(response);
                return super.parseNetworkResponse(response);
            }
        };

        requestQueue.add(objectRequest);
    }


    public static void sendPut(Context context, String URL, JSONObject request, final WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.PUT,
                WebServiceConstant.CONTEXT_PATH+URL,
                request,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
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
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                listener.onResponseReceived(response);
                return super.parseNetworkResponse(response);
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }


    public static void sendDelete(Context context, String URL, JSONObject request, final WebServiceResponseListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest  = new JsonObjectRequest(
                Request.Method.DELETE,
                WebServiceConstant.CONTEXT_PATH+URL,
                request,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
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

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                listener.onResponseReceived(response);
                return super.parseNetworkResponse(response);
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }
}
