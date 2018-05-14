package com.poolcar.callbacks;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public interface WebServiceResponseListener extends Response.Listener, Response.ErrorListener{

    void onErrorResponse(VolleyError error);


}
