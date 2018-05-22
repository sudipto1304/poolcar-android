package com.poolcar.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.poolcar.R;
import com.poolcar.callbacks.KeyboardCallBack;

public class Keyboard extends TableLayout{

    private final String TAG = this.getClass().getName();
    private KeyboardCallBack callback;
    boolean isFullScreen=true;
    boolean isDotDisable=false;


    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Keyboard(Context context, boolean isDotDisable, boolean isFullScreen, KeyboardCallBack callback) {
        super(context);
        this.callback = callback;
        this.isDotDisable = isDotDisable;
        this.isFullScreen = isFullScreen;
        init(context);
    }

    public Keyboard(Context context, boolean isFullScreen, KeyboardCallBack callback) {
        super(context);
        this.callback = callback;
        this.isFullScreen = isFullScreen;
        init(context);
    }



    private void init(Context context){
        LayoutParams lpView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lpView.gravity = Gravity.CENTER;
        lpView.setMargins(30, 0, 30, 30);
        this.setLayoutParams(lpView);
        this.setPadding(15,15,15,15);
        this.setBackground(getResources().getDrawable(R.drawable.action_sheet_shadow));
        int width = (((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()).getWidth();
        if(!isFullScreen){
            TableRow row = new TableRow(context);
            TextView closeLink = new TextView(context);
            closeLink.setPadding(10,10,30,10);
            closeLink.setText(context.getResources().getString(R.string.done));
            closeLink.setLayoutDirection(LAYOUT_DIRECTION_RTL);
            closeLink.setGravity(Gravity.RIGHT);
            closeLink.setId(R.id.keyboard_done);
            closeLink.setTextColor(context.getResources().getColor(R.color.linkColor));
            row.addView(closeLink);
            TableRow.LayoutParams the_param = (TableRow.LayoutParams)closeLink.getLayoutParams();
            the_param.span = 3;
            closeLink.setLayoutParams(the_param);
            this.addView(row);
            closeLink.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.cancelKeyboard();
                }
            });
        }
        Log.d(TAG, "Width::"+width);
        TableRow row = null;
        for(int i=0; i<12; i++){
            final Button button = new Button(context);
            button.setTextSize(25);
            String text=null;
            if(i<9){
                text = String.valueOf(i+1);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.onType(button.getText().toString());
                    }
                });
                int resID = context.getResources().getIdentifier("key_"+String.valueOf(i+1), "id", context.getPackageName());
                button.setId(resID);
            }else if (i==9){
                text = "\u00B7";
                button.setId(R.id.key_dot);
                if(isDotDisable)
                    button.setEnabled(false);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.onTypeDot();
                    }
                });
            }else if(i==10){
                text = "0";
                button.setId(R.id.key_0);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.onType("0");
                    }
                });
            }else if(i==11){
                text = "\u232B";
                Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/arial_unicode_ms.ttf");
                button.setTypeface(face);
                button.setId(R.id.key_backspace);
                button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.onBackSpace();
                    }
                });

            }
            button.setText(text);
            int buttonWidth = (int) (width/3.3);
            button.setPadding(40,10,40,10);
            button.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setWidth(buttonWidth);
            if(i==0 || i%3==0){
                if(row!=null){
                    this.addView(row);
                }
                row = new TableRow(context);
            }
            row.addView(button);
            row.setGravity(Gravity.CENTER);
        }
        if(row!=null){
            this.addView(row);
        }





    }



    



}
