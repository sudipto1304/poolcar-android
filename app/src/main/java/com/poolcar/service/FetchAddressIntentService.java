package com.poolcar.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.poolcar.R;
import com.poolcar.utils.AppConstant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class FetchAddressIntentService extends IntentService {

    private final String TAG = this.getClass().getName();
    protected ResultReceiver mReceiver;


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        mReceiver= intent.getParcelableExtra(AppConstant.RECEIVER);
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        String errorMessage = "";
        Location location = intent.getParcelableExtra(AppConstant.LOCATION_DATA_EXTRA);
        List<Address> addresses = null;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        }catch (IOException ioException) {
            errorMessage = "Service Not available";
            Log.e(TAG, errorMessage, ioException);
        }catch (IllegalArgumentException illegalArgumentException) {
            errorMessage = "Invalid Lat Long";
            Log.e(TAG, errorMessage + ". " +"Latitude = " + location.getLatitude() +", Longitude = " +location.getLongitude(), illegalArgumentException);
        }

        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "No Address found";
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(AppConstant.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, "Address found");
            deliverResultToReceiver(AppConstant.SUCCESS_RESULT, TextUtils.join(System.getProperty("line.separator"), addressFragments));
        }
    }

    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }


    public FetchAddressIntentService(){
        super("FetchAddressIntentService");
    }










}
