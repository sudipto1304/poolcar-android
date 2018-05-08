package com.poolcar.activity;

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

import com.poolcar.R;
import com.poolcar.fragments.AddressVerifyFragment;
import com.poolcar.fragments.PhoneVerifyInput;
import com.poolcar.service.FetchAddressIntentService;

public class AddressAndPhoneActivity extends OuterBaseActivity implements PhoneVerifyInput.OnFragmentInteractionListener, AddressVerifyFragment.OnFragmentInteractionListener {

    private FetchAddressIntentService service;
    private final String TAG = this.getClass().getName();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_and_phone, R.string.addressNPnoneTitle, true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showLoader();
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




}
