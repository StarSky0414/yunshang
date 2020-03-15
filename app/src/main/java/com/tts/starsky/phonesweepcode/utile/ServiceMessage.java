package com.tts.starsky.phonesweepcode.utile;


import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 *  用于获取系统消息
 */
public class ServiceMessage {

    private Context context;

    public ServiceMessage(Context context) {
        this.context = context;
    }

    /**
     *  获取扫描到的WIFI信息
     */

    public void getWifiInfo(){
        /**
         * 扫描附近wifi
         */
        String wserviceName = Context.WIFI_SERVICE;
        WifiManager mWifiManager = (WifiManager) context.getSystemService(wserviceName);
//        mWifiManager.disconnect();
//            mWifiManager.setWifiEnabled(true);
//            mWifiManager.startScan();
//        List<ScanResult> mWifiList = mWifiManager.getScanResults();
        mWifiManager.startScan();
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        EventBus.getDefault().postSticky(wifiInfo);
    }


}
