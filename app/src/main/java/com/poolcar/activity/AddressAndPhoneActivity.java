package com.poolcar.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.poolcar.R;
import com.poolcar.fragments.AddressVerifyFragment;
import com.poolcar.fragments.PhoneVerifyInput;
import com.poolcar.service.FetchAddressIntentService;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.AppUtils;
import com.poolcar.utils.LocationUtils;

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



    public void autoSearchAddress(){
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY).setCountry(AppUtils.getCountryCode(getApplicationContext()).toUpperCase())
                .build();

        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }


}
