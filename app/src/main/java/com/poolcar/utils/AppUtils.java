package com.poolcar.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.poolcar.R;

public class AppUtils {
    private final static String TAG = AppUtils.class.getName();


    public static String getCountryCode(Context context){
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getNetworkCountryIso().toUpperCase();

    }


    public static String getCountryPhoneCode(Context context){
        String countryID;
        String countryPhoneCode="";
        countryID= getCountryCode(context);
        String[] rl=context.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(countryID.trim())){
                countryPhoneCode=g[0];
                break;
            }
        }
        Log.d(TAG, "CountryZipCode:::"+countryPhoneCode);
        return countryPhoneCode;
    }

    public static String getCountryPhoneCodeByCountry(Context context, String countryCode){
        String countryPhoneCode="";
        String[] rl=context.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(countryCode.trim())){
                countryPhoneCode=g[0];
                break;
            }
        }
        Log.d(TAG, "CountryZipCode:::"+countryPhoneCode);
        return countryPhoneCode;
    }

    public static String formatNumber(String countryCode, String phNum) {
        String number;
        try {
            PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneNumber = instance.parse(phNum, countryCode);
            number = instance.formatInOriginalFormat(phoneNumber, countryCode);
        } catch (NumberParseException e) {
            Log.e(TAG, "Caught: " + e.getMessage(), e);
            number = phNum;
        }
        return number;
    }

    public static void vibrate(Context context){
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE));
        }else{
            //deprecated in API 26
            v.vibrate(200);
        }
    }
}
