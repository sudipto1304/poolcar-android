package com.poolcar.component;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poolcar.R;

public class PhoneNumberField extends RelativeLayout{

    private Context context;
    private final String TAG = this.getClass().getName();
    private ImageButton imageView;
    private PhoneNumberEditText textBox;



    public PhoneNumberField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initComponent(context);
    }





    public String getCountryCode(){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getNetworkCountryIso().toUpperCase();

    }



    private void initComponent(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pc_phone_number_field_layout, this);
        imageView = findViewById(R.id.countryFlag);
        imageView.setImageResource(getResources().getIdentifier("flag_"+getCountryCode().toLowerCase(),"drawable", context.getPackageName()));
        textBox = findViewById(R.id.phoneText);
        textBox.setSelection(textBox.getText().length());
    }




}
