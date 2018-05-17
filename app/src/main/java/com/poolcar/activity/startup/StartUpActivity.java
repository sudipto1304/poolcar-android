package com.poolcar.activity.startup;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.poolcar.R;
import com.poolcar.activity.common.OuterBaseActivity;
import com.poolcar.adapter.SlideAdapter;

public class StartUpActivity extends OuterBaseActivity {


    private ViewPager viewPager;
    private RelativeLayout layout;
    SlideAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up, 0, true);
        viewPager = findViewById(R.id.viewPager);
        layout = findViewById(R.id.rootLayout);
        adapter = new SlideAdapter(this);
        viewPager.setAdapter(adapter);

    }


}
