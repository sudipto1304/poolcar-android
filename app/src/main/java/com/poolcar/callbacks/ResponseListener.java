package com.poolcar.callbacks;

import org.json.JSONObject;

import java.io.Serializable;

public interface ResponseListener extends Serializable{

    void onResponseReceived(JSONObject response);
    void onErrorReceived(int responseCode);
    void onErrorReceived(int responseCode, JSONObject response);
}
