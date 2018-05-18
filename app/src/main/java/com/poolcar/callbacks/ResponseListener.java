package com.poolcar.callbacks;

import org.json.JSONObject;

public interface ResponseListener {

    void onResponseReceived(JSONObject response);
    void onErrorReceived(int responseCode);
    void onErrorReceived(int responseCode, JSONObject response);
}
