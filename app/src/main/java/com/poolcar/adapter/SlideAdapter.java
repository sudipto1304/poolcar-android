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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.component.RoundedImageView;

public class SlideAdapter extends PagerAdapter{


    Context context;
    LayoutInflater inflater;
    TextView slideText;
    ImageView slideImage;
    private Resources mResources;
    TextView header;
    LinearLayout background;

    private int[] imageIds = new int[]{

    };

    private String[] texts;
    private String[] heading;

    private int[] images;
    private Drawable[] drawables;


    public SlideAdapter(Context context){
        this.context = context;
        texts = new String[]{
                context.getResources().getString(R.string.slide1Content), context.getResources().getString(R.string.slide2Content), context.getResources().getString(R.string.slide3Content)
        };
        heading = new String[]{
                context.getResources().getString(R.string.slide1heading), context.getResources().getString(R.string.slide2heading), context.getResources().getString(R.string.slide3heading)
        };
        images = new int[]{
                R.drawable.car, R.drawable.track, R.drawable.money
        };
        drawables = new Drawable[]{
                context.getResources().getDrawable(R.drawable.round_image_yellow), context.getResources().getDrawable(R.drawable.round_green), context.getResources().getDrawable(R.drawable.round_cyan)
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
        background = view.findViewById(R.id.layout_background);
        slideText.setText(texts[position]);
        slideImage.setImageResource(images[position]);
        header = view.findViewById(R.id.slideHeading);
        header.setText(heading[position]);
        background.setBackground(drawables[position]);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }






}
