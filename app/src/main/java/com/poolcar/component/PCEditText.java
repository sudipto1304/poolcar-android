package com.poolcar.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private Context context;

    public PCEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context  =context;
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


    public void setError(String error){
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
        textView.startAnimation(shake);
        Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_notify_error);
        drawable.setBounds(0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth());
        textView.setError(error, drawable);
    }

    private void initComponent(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pc_edit_text_layout, this);
        cross_image = findViewWithTag("cross_image");
        cross_image.setId(this.getResources().getIdentifier(cross_id, "id", context.getPackageName()));
        cross_image.setBackgroundResource(R.drawable.ic_action_close);
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

    public String getText(){
        return this.textView.getText().toString();
    }

    public void setDigit(final String digits){
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        this.textView.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(15) });
    }

}
