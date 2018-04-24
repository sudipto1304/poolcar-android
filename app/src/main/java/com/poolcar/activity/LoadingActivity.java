package com.poolcar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.poolcar.R;

public class LoadingActivity extends OuterBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading, 0, false);
        showLoader();
        //Intent intent = new Intent(this, StartUpActivity.class);
        //startActivity(intent);


    }


}
