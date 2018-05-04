package com.poolcar.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.poolcar.R;

public class AppUtils {
    private final static String TAG = AppUtils.class.getName();


    public static String getCountryCode(Context context){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getNetworkCountryIso().toUpperCase();

    }


    public static String getCountryPhoneCode(Context context){
        String countryID;
        String countryZipCode="";
        countryID= getCountryCode(context);
        String[] rl=context.getResources().getStringArray(R.array.CountryCodes);
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

}
