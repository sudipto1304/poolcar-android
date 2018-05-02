package com.poolcar.component;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poolcar.R;

public class PhoneNumberField extends RelativeLayout{

    private Context context;
    private final String TAG = this.getClass().getName();
    public PhoneNumberField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initComponent(context);
    }


    public String getCountryZipCode(){
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
        return countryZipCode;
    }


    public String getCountryCode(){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getNetworkCountryIso().toUpperCase();

    }

    private String getFlagEmoji(String countryCode) {
        int firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6;
        int secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstLetter)) + new String(Character.toChars(secondLetter));
    }


    private void initComponent(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.pc_phone_number_field_layout, this);
        ((TextView)findViewById(R.id.countryFlag)).setText(getFlagEmoji(getCountryCode()));
    }



}
