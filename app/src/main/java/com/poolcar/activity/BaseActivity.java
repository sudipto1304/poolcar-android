package com.poolcar.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.poolcar.fragments.ActionSheetFragment;
import com.poolcar.fragments.NotificationFragment;
import com.poolcar.utils.AppConstant;

public class BaseActivity extends AppCompatActivity implements AppConstant, NotificationFragment.OnFragmentInteractionListener, ActionSheetFragment.OnFragmentInteractionListener{



    public void showError(String errorText){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NotificationFragment nf = new NotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NOTIFICATION_TYPE, ERROR_NOTIFICATION);
        bundle.putString(NOTIFICATION_TEXT, errorText);
        nf.setArguments(bundle);
        fragmentTransaction.add(android.R.id.content, nf, "HELLO");
        fragmentTransaction.addToBackStack("Banner");
        fragmentTransaction.commit();
    }

    public void cancelError(){
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
