package com.poolcar.activity.startup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poolcar.R;
import com.poolcar.activity.common.OuterBaseActivity;
import com.poolcar.callbacks.ResponseListener;
import com.poolcar.model.AppData;
import com.poolcar.model.ClientDetails;
import com.poolcar.service.WebServiceManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LoadingActivity extends OuterBaseActivity {

    private static final String TAG = LoadingActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppData.getInstance().clearData();
        setContentView(R.layout.activity_loading, 0, false);


    }

    @Override
    protected void onStart() {
        super.onStart();
        showLoader();
        WebServiceManager manager = new WebServiceManager(getApplicationContext());

        manager.authoriseApplication(new ResponseListener() {
            @Override
            public void onResponseReceived(JSONObject response) {
                attemptLogin();
            }


            @Override
            public void onErrorReceived() {
                new AlertDialog.Builder(LoadingActivity.this)
                        .setCancelable(false)
                        .setMessage(getResources().getString(R.string.internet_connection_error))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                        .show();
            }


        });

    }


    private void attemptLogin() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (null == account) {
            Log.d(TAG, "No Account found for google signed in user:::checking for facebook login");
            if (!isFacebookLoggedIn()) {
                Log.d(TAG, "No Account found for facebook as well. Check for shared pref for logged in user details");
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String signedInUserId = prefs.getString(SIGNED_USER_ID, null);
                String signedInEmailId = prefs.getString(SIGNED_EMAIL_ID, null);
                String password = prefs.getString(PASSWORD, null);
                if (null != signedInEmailId && null != signedInUserId) {
                    Log.d(TAG, "Signed in user found with userid ::" + signedInUserId);
                    doSignIn(prefs);
                } else {
                    Log.d(TAG, "No signed in user found. Redirected to setup screen");
                    initSetup();
                }
            } else {
                doSignIn(AccessToken.getCurrentAccessToken());
            }
        } else {
            doSignIn(account);
        }
    }


    private boolean isFacebookLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void doSignIn(Object obj) {
        if (obj instanceof AccessToken) {
            Log.d(TAG, "Logging in with Facebook");
        } else if (obj instanceof GoogleSignInAccount) {
            Log.d(TAG, "Logging in with Google");
        } else if (obj instanceof SharedPreferences) {
            Log.d(TAG, "Logging in with Userid Password");
        } else {
            initSetup();
        }

    }

    private void initSetup() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                hideLoader();
                ImageView logo = findViewById(R.id.logo);
                logo.setVisibility(View.VISIBLE);
                final Animation myAnim = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.bounce);
                logo.startAnimation(myAnim);
                Handler hl = new Handler();
                hl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoadingActivity.this, StartUpActivity.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });


    }
}



