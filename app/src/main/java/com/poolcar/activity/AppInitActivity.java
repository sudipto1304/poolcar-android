package com.poolcar.activity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.poolcar.R;

public class AppInitActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_init);
        imageView = (ImageView) findViewById(R.id.imageView);
        Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        imageView.setAnimation(zoomout);
        imageView.startAnimation(zoomout);
    }
}
