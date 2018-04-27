package com.poolcar.activity;

import android.net.Uri;
import android.os.Build;
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
import android.view.WindowManager;

import com.poolcar.R;
import com.poolcar.component.Loader;
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

    public void setContentView(int layoutId, int title, boolean isDockerRequired) {
        stub = findViewById(R.id.baseLayout);
        stub.setLayoutResource(layoutId);
        stub.inflate();
        if(title!=0)
            getSupportActionBar().setTitle(title);
        else
            getSupportActionBar().hide();

        if(!isDockerRequired){
            if (Build.VERSION.SDK_INT < 16) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }else{
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }else{
            if (Build.VERSION.SDK_INT < 16) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }else{
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }

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


    public void showLoader(){
        Loader loader = findViewById(R.id.loader);
        loader.setVisibility(View.VISIBLE);
    }

    public void hideLoader(){
        Loader loader = findViewById(R.id.loader);
        loader.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
    }

    public boolean isInactive(){
        return this.inactive;
    }


}
