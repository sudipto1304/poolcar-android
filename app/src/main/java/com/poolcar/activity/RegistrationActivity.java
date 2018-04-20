package com.poolcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.poolcar.R;

public class RegistrationActivity extends OuterBaseActivity {

    private static final String TAG = RegistrationActivity.class.getClass().getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration, R.string.createAccountTitle, true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                Intent intent  = new Intent(this, AddressAndPhoneActivity.class);

            }
        }else
            onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
        if(!isInactive()) {
            Intent intent = new Intent(this, AppInitActivity.class);
            startActivity(intent);
        }
    }




}
