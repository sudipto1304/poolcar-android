package com.poolcar.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poolcar.R;

public class PCEditText extends RelativeLayout {

    private String cross_id;
    private String field_id;
    private ImageView cross_image;
    private EditText textView;
    private String hintText;
    private boolean isPassword;

    public PCEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PCEditText);
        try {
            cross_id = ta.getString(R.styleable.PCEditText_clear_id);
            field_id = ta.getString(R.styleable.PCEditText_field_id);
            hintText = ta.getString(R.styleable.PCEditText_hintText);
            isPassword = ta.getBoolean(R.styleable.PCEditText_isPassword, false);
        } finally {
            ta.recycle();
        }
        initComponent(context);
    }


    private void initComponent(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pc_edit_text_layout, this);
        cross_image = findViewWithTag("cross_image");
        cross_image.setId(this.getResources().getIdentifier(cross_id, "id", context.getPackageName()));
        cross_image.setBackgroundResource(R.drawable.icons8_cance_10);
        textView = findViewWithTag("edit_text_field");
        textView.setId(this.getResources().getIdentifier(field_id, "id", context.getPackageName()));
        textView.setHint(hintText);
        textView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        if(isPassword)
            textView.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
        cross_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
            }
        });
    }


}
