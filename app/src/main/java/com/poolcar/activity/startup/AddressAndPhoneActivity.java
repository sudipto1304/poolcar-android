package com.poolcar.activity.startup;

import android.content.Context;
import android.content.Intent;
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
import com.poolcar.model.UserProfileData;
import com.poolcar.service.FetchAddressIntentService;
import com.poolcar.service.WebServiceManager;

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
                UserProfileData profileData = (UserProfileData)getIntent().getSerializableExtra(DATA_USER_PROFILE);
                profileData.setPhoneNumber(phoneNumber.getNumber());
                profileData.setAddress(((EditText)findViewById(R.id.locationText)).getText().toString());
                WebServiceManager service = new WebServiceManager(getApplicationContext());
                service.registerUser(profileData, new ResponseListener() {
                    @Override
                    public void onResponseReceived(JSONObject response) {

                    }

                    @Override
                    public void onErrorReceived() {

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




}
