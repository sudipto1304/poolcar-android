package com.poolcar.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.component.RoundedImageView;

public class SlideAdapter extends PagerAdapter{


    Context context;
    LayoutInflater inflater;
    TextView slideText;
    ImageView slideImage;
    private Resources mResources;

    private int[] imageIds = new int[]{

    };

    private String[] texts;

    private int[] images;


    public SlideAdapter(Context context){
        this.context = context;
        texts = new String[]{
                context.getResources().getString(R.string.slide1Content), context.getResources().getString(R.string.slide2Content), context.getResources().getString(R.string.slide3Content)
        };
        images = new int[]{
                R.drawable.poolcar, R.drawable.map_point, R.drawable.map
        };
    }


    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_layout, container, false);
        slideImage = view.findViewById(R.id.slideImage);
        slideText = view.findViewById(R.id.slideText);
        slideText.setText(texts[position]);
        slideImage.setImageResource(images[position]);
        slideImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }






}
