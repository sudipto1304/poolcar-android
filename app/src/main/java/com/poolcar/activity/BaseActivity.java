package com.poolcar.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.poolcar.R;
import com.poolcar.component.Loader;
import com.poolcar.fragments.ActionSheetFragment;
import com.poolcar.fragments.NotificationFragment;
import com.poolcar.service.FetchAddressIntentService;
import com.poolcar.utils.AppConstant;
import com.poolcar.utils.LocationUtils;

public class BaseActivity extends AppCompatActivity implements AppConstant, NotificationFragment.OnFragmentInteractionListener, ActionSheetFragment.OnFragmentInteractionListener{

    private ViewStub stub;
    private ConstraintLayout layout;
    private boolean inactive = false;
    protected Location mLastLocation;
    private AddressResultReceiver mResultReceiver;
    private String mAddressOutput;
    private LocationManager locationManager;
    private LocationUtils locationUtils;
    private BaseActivity locationResultActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);
        layout=findViewById(R.id.parentLayout);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationUtils=new LocationUtils();
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


    public void showSuccess(String successText){
        if(null!=layout) {
            toogleView(layout, true);
            inactive=true;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NotificationFragment nf = new NotificationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NOTIFICATION_TYPE, SUCCESS_NOTIFICATION);
        bundle.putString(NOTIFICATION_TEXT, successText);
        nf.setArguments(bundle);
        fragmentTransaction.add(android.R.id.content, nf, "NOTIFICATION");
        fragmentTransaction.commit();
    }

    public void showActionSheet(View view){
        if(null!=layout) {
            toogleView(layout, true);
            inactive=true;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ActionSheetFragment actionSheet = new ActionSheetFragment();
        actionSheet.setView(view);
        fragmentTransaction.add(android.R.id.content, actionSheet, "ACTIONSHEET");
        fragmentTransaction.commit();
    }



    public void cancelFragment(String tag){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_down, R.anim.slide_down);
        fragmentTransaction.remove(getSupportFragmentManager().findFragmentByTag(tag)).commit();
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


    public void getCurrentAddress(BaseActivity activity) {
        locationResultActivity = activity;
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
                startIntentService();
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
            locationResultActivity.onLocationResultReceived(mAddressOutput);
        }
    }


    public void onLocationResultReceived(String address){

    }


}
