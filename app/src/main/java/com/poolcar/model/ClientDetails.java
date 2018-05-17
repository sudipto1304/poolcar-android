package com.poolcar.model;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.poolcar.PCApplication;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ClientDetails implements Serializable{

    private String deviceId;
    private String operatingSystem;
    private String operatingSystemVersion;
    private String requestTime;
    private String isp;
    private String ipAddress;

    public ClientDetails(){
        deviceId = AppData.getInstance().getClientId();
        operatingSystem = "ANDROID";
        operatingSystemVersion=String.valueOf(android.os.Build.VERSION.SDK_INT);
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss a zzz");
        Date date = new Date();
        requestTime=df.format(date);
        TelephonyManager telephonyManager = ((TelephonyManager) PCApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE));
        isp = telephonyManager.getNetworkOperatorName();
        WifiManager wifiMan = (WifiManager) PCApplication.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();
        int ip = wifiInf.getIpAddress();
        ipAddress = String.format("%d.%d.%d.%d", (ip & 0xff),(ip >> 8 & 0xff),(ip >> 16 & 0xff),(ip >> 24 & 0xff));
    }
}
