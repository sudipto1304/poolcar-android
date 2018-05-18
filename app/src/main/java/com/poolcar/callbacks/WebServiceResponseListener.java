package com.poolcar.callbacks;

import android.os.Parcelable;

import com.android.volley.NetworkResponse;

import org.json.JSONObject;

import java.io.Serializable;

public interface WebServiceResponseListener extends Serializable {

    void onResponseReceived(JSONObject response);
    void onError();
    void onError(NetworkResponse response);


}
