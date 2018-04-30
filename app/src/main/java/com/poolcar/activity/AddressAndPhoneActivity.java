package com.poolcar.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.poolcar.R;
import com.poolcar.service.FetchAddressIntentService;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.LocationUtils;

public class AddressAndPhoneActivity extends OuterBaseActivity {

    private FetchAddressIntentService service;
    private final String TAG = this.getClass().getName();
    protected Location mLastLocation;
    private AddressResultReceiver mResultReceiver;
    private String mAddressOutput;
    private LocationManager locationManager;
    private LocationUtils locationUtils;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_and_phone, R.string.addressNPnoneTitle, true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationUtils=new LocationUtils();
        showPermissionDialog();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.continue_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.continueMenu){
            if(!isInactive()) {


            }
        }else
            onBackPressed();

        return true;
    }


    @Override
    public void onBackPressed() {
        View view = this.getCurrentFocus();
        if(view!=null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
        if(!isInactive()) {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }


    private void showPermissionDialog() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_ACCESS_CODE);
        }else{
            startIntentService();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==LOCATION_ACCESS_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                startIntentService() ;
            }else{
                Toast.makeText(this, "not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }




    protected void startIntentService() {
        mLastLocation =locationUtils.getLocation(getApplicationContext());
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(AppConstant.RECEIVER, new AddressResultReceiver(new Handler()));
        intent.putExtra(AppConstant.LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }



    class AddressResultReceiver extends ResultReceiver {


        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultData == null) {
                return;
            }
            mAddressOutput = resultData.getString(AppConstant.RESULT_DATA_KEY);
            if (mAddressOutput == null) {
                mAddressOutput = "";
            }
        }
    }

}
