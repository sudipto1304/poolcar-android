package com.poolcar.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.poolcar.R;
import com.poolcar.fragments.ActionSheetFragment;
import com.poolcar.fragments.NotificationFragment;
import com.poolcar.utils.AppConstant;

public class BaseActivity extends AppCompatActivity implements AppConstant, NotificationFragment.OnFragmentInteractionListener, ActionSheetFragment.OnFragmentInteractionListener{

    private ViewStub stub;
    private ConstraintLayout layout;
    private boolean inactive = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);
        layout=findViewById(R.id.parentLayout);
    }

    public void setContentView(int layoutId, int title, boolean isTitleRequired) {
        stub = findViewById(R.id.baseLayout);
        stub.setLayoutResource(layoutId);
        stub.inflate();
        if(isTitleRequired)
            getSupportActionBar().setTitle(title);
        else
            getSupportActionBar().hide();

    }


    public void showError(String errorText){
        if(null!=layout) {
            toogleView(layout, true);
            inactive=true;
        }
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
        if(null!=layout) {
            toogleView(layout, false);
            inactive=false;

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private static void toogleView(View v, final boolean isDisable) {

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isDisable;
            }
        });

        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                toogleView(child, isDisable);
            }
        }
    }


    public boolean isInactive(){
        return this.inactive;
    }


}
