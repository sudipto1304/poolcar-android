package com.poolcar.activity.startup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.poolcar.R;
import com.poolcar.activity.common.OuterBaseActivity;
import com.poolcar.callbacks.ResponseListener;
import com.poolcar.component.PhoneNumberField;
import com.poolcar.fragments.AddressVerifyFragment;
import com.poolcar.fragments.PhoneVerifyInput;
import com.poolcar.model.AppData;
import com.poolcar.model.UserProfileData;
import com.poolcar.service.FetchAddressIntentService;
import com.poolcar.service.WebServiceManager;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.AppUtils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddressAndPhoneActivity extends OuterBaseActivity implements PhoneVerifyInput.OnFragmentInteractionListener, AddressVerifyFragment.OnFragmentInteractionListener {

    private FetchAddressIntentService service;
    private final String TAG = this.getClass().getName();
    private boolean isBackDisable=false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_and_phone, R.string.addressNPnoneTitle, true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showDarkLoader();
        getCurrentAddress(this);

        PhoneVerifyInput pvi = new PhoneVerifyInput();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.phoneSection, pvi, "phone_verify_input");
        transaction.commit();


    }



    @Override
    public void onLocationResultReceived(String address) {
        Log.d(TAG, "Current address is "+address);
        Bundle addressBundle = new Bundle();
        if(address==null){
            addressBundle.putBoolean(ADDRESS_DATA_ERROR, true);
        }else{
            addressBundle.putBoolean(ADDRESS_DATA_ERROR, false);
            addressBundle.putString(LOCATION_ADDRESS, address);
        }
        AddressVerifyFragment avf = new AddressVerifyFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        avf.setArguments(addressBundle);
        transaction.add(R.id.addressConfirmSection, avf, "address_verify_input");
        transaction.commit();
        hideLoader();
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
                final PhoneNumberField phoneNumber =findViewById(R.id.phoneNumberField);
                final UserProfileData profileData = (UserProfileData)getIntent().getSerializableExtra(DATA_USER_PROFILE);
                profileData.setPhoneNumber(phoneNumber.getNumber());
                profileData.setAddress(((EditText)findViewById(R.id.locationText)).getText().toString());
                WebServiceManager service = new WebServiceManager(getApplicationContext());
                Log.d(TAG, "Calling service for register user");
                showDarkLoader();
                service.registerUser(profileData, new ResponseListener() {
                    @Override
                    public void onResponseReceived(JSONObject response) {
                        storeDataForApp(profileData);
                        invokeDashboard();
                    }

                    @Override
                    public void onErrorReceived(int responseCode) {
                        hideLoader();
                    }

                    @Override
                    public void onErrorReceived(int responseCode, JSONObject response) {

                    }
                });

            }
        }else
            onBackPressed();

        return true;
    }


    @Override
    public void onBackPressed() {
        if(!isBackDisable) {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            }
            if (!isInactive()) {
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);
            }
        }
    }


    protected void invokeDashboard(){
        WebServiceManager service = new WebServiceManager(getApplicationContext());
        Log.d(TAG, "Invoking user dashboard");
        service.getUserProfile(this, new ResponseListener() {
            @Override
            public void onResponseReceived(JSONObject response) {
                Intent intent = new Intent(AddressAndPhoneActivity.this, DashboardActivity.class);
                intent.putExtra(DATA_DASHBOARD, AppUtils.parseProfileResponse(response));
                startActivity(intent);
            }

            @Override
            public void onErrorReceived(int responseCode) {

            }

            @Override
            public void onErrorReceived(int responseCode, JSONObject response) {

            }
        });
    }

    protected void storeDataForApp(UserProfileData profileData){
        showDarkLoader();
        SharedPreferences sharedPref = this.getSharedPreferences(AppConstant.PC_SF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(AppConstant.DATA_USER_ID, profileData.getUserId());
        editor.putString(AppConstant.DATA_EMAIL_ID, profileData.getEmailId());
        editor.putString(AppConstant.DATA_CONTACT_NUMBER, profileData.getPhoneNumber());
        editor.commit();

    }



}
