package com.poolcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poolcar.R;

public class SlideAdapter extends PagerAdapter{


    Context context;
    LayoutInflater inflater;
    TextView slideText;
    ImageView slideImage;

    private int[] imageIds = new int[]{

    };

    private String[] texts = new String[]{
            "Sudipto", "Tania"
    };


    public SlideAdapter(Context context){
        this.context = context;
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
        //slideImage.setImageResource(R.drawable.com_facebook_button_icon);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }




}
