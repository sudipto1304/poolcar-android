package com.poolcar.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.poolcar.R;
import com.poolcar.utils.AppUtils;

public class PhoneNumberEditText extends android.support.v7.widget.AppCompatEditText{
    private Context context;
    private final String TAG = this.getClass().getName();
    private int nonEditTextCount;
    private Rect mPrefixRect = new Rect();

    public PhoneNumberEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String countryCode = AppUtils.getCountryPhoneCode(context);
        nonEditTextCount = countryCode.length()+1;
        getPaint().getTextBounds("+"+ countryCode, 0,nonEditTextCount, mPrefixRect);
        mPrefixRect.right += getPaint().measureText("  "); // add some offset

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("+"+AppUtils.getCountryPhoneCode(context), super.getCompoundPaddingLeft(), getBaseline(), getPaint());
        this.setInputType(InputType.TYPE_CLASS_PHONE);
        this.setFocusable(false);
    }

    @Override
    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft() + mPrefixRect.width();
    }



}
