package com.poolcar.model;

import com.poolcar.callbacks.WebServiceResponseListener;

public class AppData {

    private  String clientId;
    private  String API_AUTH_TOKEN;
    private  WebServiceResponseListener listener;

    public WebServiceResponseListener getListener() {
        return listener;
    }

    private static final AppData instance = new AppData();

    public void setListener(WebServiceResponseListener listener) {
        this.listener = listener;
    }

    private AppData(){}

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthToken() {
        return API_AUTH_TOKEN;
    }

    public void setAuthToken(String token) {
        this.API_AUTH_TOKEN = token;
    }

    public static AppData getInstance(){
        return instance;

    }

    public void clearData(){
        this.API_AUTH_TOKEN = null;
    }
}
