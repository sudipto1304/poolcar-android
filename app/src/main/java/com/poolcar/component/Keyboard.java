package com.poolcar.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Keyboard extends RelativeLayout{

    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Keyboard(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context){
        this.setMinimumHeight( ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setPadding(15,15,15,15);
    }







}
