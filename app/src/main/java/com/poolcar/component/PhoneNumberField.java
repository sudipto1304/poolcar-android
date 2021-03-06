package com.poolcar.component;

import android.content.Context;
import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.poolcar.R;
import com.poolcar.utils.AppUtils;

public class PhoneNumberField extends RelativeLayout{

    private Context context;
    private final String TAG = this.getClass().getName();
    private ImageButton imageView;
    private PhoneNumberEditText textBox;

    private int  MAX_LENGTH=16;



    public PhoneNumberField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initComponent(context);
    }

    public String getNumber(){
        return this.textBox.getText().toString();
    }

    public void setNumber(String val){
        this.textBox.setText(val);
        this.textBox.setSelection(this.textBox.getText().length());
    }

    public PhoneNumberEditText getTextBoxObj(){
        return textBox;
    }




    private void initComponent(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pc_phone_number_field_layout, this);
        imageView = findViewById(R.id.countryFlag);
        imageView.setImageResource(getResources().getIdentifier("flag_"+ AppUtils.getCountryCode(context).toLowerCase(),"drawable", context.getPackageName()));
        textBox = findViewById(R.id.phoneText);
        textBox.setSelection(textBox.getText().length());
        textBox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_LENGTH)});


    }






}
