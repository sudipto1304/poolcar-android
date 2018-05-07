package com.poolcar.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Keyboard extends TableLayout{

    private final String TAG = this.getClass().getName();


    public Keyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Keyboard(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context){
        LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lpView.gravity = Gravity.CENTER;
        //this.setMinimumHeight( ViewGroup.LayoutParams.WRAP_CONTENT);
        //this.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(lpView);
        this.setPadding(15,15,15,15);

        int width = (((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()).getWidth();

        Log.d(TAG, "Width::"+width);
        TableRow row = null;
        for(int i=0; i<12; i++){
            Button button = new Button(context);
            button.setTextSize(25);
            String text=null;
            if(i<9){
                text = String.valueOf(i+1);
            }else if (i==9){
                text = "\u00B7";
            }else if(i==10){
                text = "0";
            }else if(i==11){
                text = "\u232B";
                Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/arial_unicode_ms.ttf");
                button.setTypeface(face);

            }
            button.setText(text);
            int buttonWidth = (width/3);
            button.setPadding(10,10,10,10);
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
