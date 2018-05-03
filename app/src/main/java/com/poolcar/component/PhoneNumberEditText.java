package com.poolcar.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;

import com.poolcar.R;

public class PhoneNumberEditText extends android.support.v7.widget.AppCompatEditText{
    private Context context;
    private final String TAG = this.getClass().getName();
    private int nonEditTextCount;
    private Rect mPrefixRect = new Rect();

    public PhoneNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    public String getCountryCode(){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getNetworkCountryIso().toUpperCase();

    }

    public String getCountryPhoneCode(){
        String countryID;
        String countryZipCode="";
        countryID= getCountryCode();
        String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(countryID.trim())){
                countryZipCode=g[0];
                break;
            }
        }
        Log.d(TAG, "CountryZipCode:::"+countryZipCode);
        nonEditTextCount = countryZipCode.length()+1;
        return countryZipCode;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        getPaint().getTextBounds("+"+getCountryPhoneCode(), 0,nonEditTextCount, mPrefixRect);
        mPrefixRect.right += getPaint().measureText("  "); // add some offset

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("+"+getCountryPhoneCode(), super.getCompoundPaddingLeft(), getBaseline(), getPaint());
        this.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    @Override
    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft() + mPrefixRect.width();
    }



}
