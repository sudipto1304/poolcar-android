package com.poolcar.model;

public class AppData {

    private static String clientId;
    private static String API_AUTH_TOKEN;

    private static final AppData instance = new AppData();

    private AppData(){}

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        AppData.clientId = clientId;
    }

    public String getAuthToken() {
        return API_AUTH_TOKEN;
    }

    public void setAuthToken(String token) {
        AppData.API_AUTH_TOKEN = token;
    }

    public static AppData getInstance(){
        return instance;

    }

    public void clearData(){
        AppData.API_AUTH_TOKEN = null;
    }
}
