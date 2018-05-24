package com.poolcar.activity.startup;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.activity.common.InnerBaseActivity;

public class DashboardActivity extends InnerBaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard, 0, false);
        RelativeLayout layout = findViewById(R.id.dashboardHeader);
        TextView header = findViewById(R.id.appHeader);
        TextView userName = findViewById(R.id.userName);
        userName.setText(getResources().getString(R.string.welcome_pre)+" ");
        Typeface tf =Typeface.createFromAsset(getAssets(),"fonts/Dense-Regular.otf");
        header.setTypeface(tf);
    }

}
