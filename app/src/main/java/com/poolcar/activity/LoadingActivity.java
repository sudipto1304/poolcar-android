package com.poolcar.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.poolcar.R;

import org.w3c.dom.Text;

public class LoadingActivity extends OuterBaseActivity {

    private static final String TAG = LoadingActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading, 0, false);

        //Intent intent = new Intent(this, StartUpActivity.class);
        //startActivity(intent);


    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoader();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (null==account){
            Log.d(TAG, "No Account found for google signed in user:::checking for facebook login");
            if(!isFacebookLoggedIn()){
                Log.d(TAG, "No Account found for facebook as well. Check for shared pref for logged in user details");
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String signedInUserId = prefs.getString(SIGNED_USER_ID, null);
                String signedInEmailId = prefs.getString(SIGNED_EMAIL_ID, null);
                if(null!=signedInEmailId && null!=signedInUserId){
                    Log.d(TAG, "Signed in user found with userid ::"+signedInUserId);
                    doSignIn();
                }else{
                    Log.d(TAG, "No signed in user found. Redirected to setup screen");
                    initSetup();
                }
            }
        }
    }


    private boolean isFacebookLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void doSignIn(){

    }

    private void initSetup(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoader();
                ImageView logo = findViewById(R.id.logo);
                logo.setVisibility(View.VISIBLE);
                final Animation myAnim = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.bounce);
                logo.startAnimation(myAnim);
                TextView logoText = findViewById(R.id.logoText);
                logoText.setVisibility(View.VISIBLE);
                final Animation textAnim = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.slide_in_from_right);
                logoText.startAnimation(textAnim);
                //Intent intent = new Intent(LoadingActivity.this, StartUpActivity.class);
                //startActivity(intent);
            }

        }, 3000);
    }
}
