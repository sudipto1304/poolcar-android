package com.poolcar.callbacks;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public interface WebServiceResponseListener {

    void onResponseReceived(NetworkResponse response);


}
