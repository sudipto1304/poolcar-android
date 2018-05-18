package com.poolcar.callbacks;

import com.android.volley.NetworkResponse;

import java.io.Serializable;

public interface WebServiceResponseListener extends Serializable{

    void onResponseReceived(NetworkResponse response);
    void onError();
    

}
