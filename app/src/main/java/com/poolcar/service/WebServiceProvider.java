package com.poolcar.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.poolcar.PCApplication;
import com.poolcar.callbacks.WebServiceResponseListener;
import com.poolcar.model.AppData;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.WebServiceConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
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
                String json=null;
                JSONObject jsonObj=null;
                try{
                    json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    jsonObj = new JSONObject(json);
                    Log.d(TAG, "HTTP Response body: "+jsonObj.toString());
                    if(jsonObj.get(AppConstant.DATA_AUTHORIZATION)!=null){
                        AppData.getInstance().setAuthToken((String)jsonObj.get(AppConstant.DATA_AUTHORIZATION));
                    }
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    listener.onError();
                }
                if(response.statusCode==AppConstant.HTTP_AUTH_REQUIRED){
                    Log.d(TAG, "HTTP Response code is ::"+AppConstant.HTTP_AUTH_REQUIRED+" redirecting to OTP");
                    try{
                        JSONObject statusObj = (JSONObject)jsonObj.get(AppConstant.DATA_STATUS);
                        OTPService otpService = new OTPService(listener);
                        Log.d(TAG, "Redirecting to OTP with "+json);
                        otpService.launchOTP((String)statusObj.get(AppConstant.DATA_OTP_STRING), jsonObj);
                    }catch(Exception e){
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                        listener.onError();
                    }
                }else if(AppConstant.HTTP_SUCCESS_STATUS_CODES.contains(response.statusCode)){
                    listener.onResponseReceived(jsonObj);
                }else{
                    listener.onError(response);
                }
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
                String json=null;
                JSONObject jsonObj=null;
                try{
                    json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    jsonObj = new JSONObject(json);
                    Log.d(TAG, "HTTP Response body: "+jsonObj.toString());
                    if(jsonObj.get(AppConstant.DATA_AUTHORIZATION)!=null){
                        AppData.getInstance().setAuthToken((String)jsonObj.get(AppConstant.DATA_AUTHORIZATION));
                    }
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    listener.onError();
                }
                if(response.statusCode==AppConstant.HTTP_AUTH_REQUIRED){
                    Log.d(TAG, "HTTP Response code is ::"+AppConstant.HTTP_AUTH_REQUIRED+" redirecting to OTP");
                    try{
                        JSONObject statusObj = (JSONObject)jsonObj.get(AppConstant.DATA_STATUS);
                        OTPService otpService = new OTPService(listener);
                        Log.d(TAG, "Redirecting to OTP with "+json);
                        otpService.launchOTP((String)statusObj.get(AppConstant.DATA_OTP_STRING), jsonObj);
                    }catch(Exception e){
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                        listener.onError();
                    }
                }else if(AppConstant.HTTP_SUCCESS_STATUS_CODES.contains(response.statusCode)){
                    listener.onResponseReceived(jsonObj);
                }else{
                    listener.onError(response);
                }
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
                String json=null;
                JSONObject jsonObj=null;
                try{
                    json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    jsonObj = new JSONObject(json);
                    Log.d(TAG, "HTTP Response body: "+jsonObj.toString());
                    if(jsonObj.get(AppConstant.DATA_AUTHORIZATION)!=null){
                        AppData.getInstance().setAuthToken((String)jsonObj.get(AppConstant.DATA_AUTHORIZATION));
                    }
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    listener.onError();
                }
                if(response.statusCode==AppConstant.HTTP_AUTH_REQUIRED){
                    Log.d(TAG, "HTTP Response code is ::"+AppConstant.HTTP_AUTH_REQUIRED+" redirecting to OTP");
                    try{
                        JSONObject statusObj = (JSONObject)jsonObj.get(AppConstant.DATA_STATUS);
                        OTPService otpService = new OTPService(listener);
                        Log.d(TAG, "Redirecting to OTP with "+json);
                        otpService.launchOTP((String)statusObj.get(AppConstant.DATA_OTP_STRING), jsonObj);
                    }catch(Exception e){
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                        listener.onError();
                    }
                }else if(AppConstant.HTTP_SUCCESS_STATUS_CODES.contains(response.statusCode)){
                    listener.onResponseReceived(jsonObj);
                }else{
                    listener.onError(response);
                }
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
                String json=null;
                JSONObject jsonObj=null;
                try{
                    json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    jsonObj = new JSONObject(json);
                    Log.d(TAG, "HTTP Response body: "+jsonObj.toString());
                    if(jsonObj.get(AppConstant.DATA_AUTHORIZATION)!=null){
                        AppData.getInstance().setAuthToken((String)jsonObj.get(AppConstant.DATA_AUTHORIZATION));
                    }
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                    listener.onError();
                }
                if(response.statusCode==AppConstant.HTTP_AUTH_REQUIRED){
                    Log.d(TAG, "HTTP Response code is ::"+AppConstant.HTTP_AUTH_REQUIRED+" redirecting to OTP");
                    try{
                        JSONObject statusObj = (JSONObject)jsonObj.get(AppConstant.DATA_STATUS);
                        OTPService otpService = new OTPService(listener);
                        Log.d(TAG, "Redirecting to OTP with "+json);
                        otpService.launchOTP((String)statusObj.get(AppConstant.DATA_OTP_STRING), jsonObj);
                    }catch(Exception e){
                        Log.e(TAG, e.getMessage());
                        e.printStackTrace();
                        listener.onError();
                    }
                }else if(AppConstant.HTTP_SUCCESS_STATUS_CODES.contains(response.statusCode)){
                    listener.onResponseReceived(jsonObj);
                }else{
                    listener.onError(response);
                }
                return super.parseNetworkResponse(response);
            }
        };
        Log.d(TAG, "Request body: "+request.toString());
        requestQueue.add(objectRequest);
    }



}
