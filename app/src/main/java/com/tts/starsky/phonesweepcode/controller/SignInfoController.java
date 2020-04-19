package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.SignInfo;
import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
import com.tts.starsky.phonesweepcode.db.provider.SignInfoProvider;
import com.tts.starsky.phonesweepcode.db.provider.UserProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import com.tts.starsky.phonesweepcode.db.provider.SonUserProvider;

public class SignInfoController {

    private final SignInfoProvider signInfoProvider;
    private final UserProvider userProvider;
//    private final SonUserProvider sonUserProvider;
    // 允许打卡标志
    public static  boolean SIGN_OK=false;

    public SignInfoController() {
        signInfoProvider = new SignInfoProvider();
        userProvider = new UserProvider();
//        sonUserProvider = new SonUserProvider();
    }

    public void setSignInfo() {
        Long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));

        UserInfo userInfo = UserController.getUserInfo();
        SignInfo signInfo = new SignInfo();
        signInfo.setUserName(userInfo.getUserName());
        signInfo.setStartTime(String.valueOf(sd));
        signInfo.setUserSonId(userInfo.get_id());
        signInfo.setUserFatherId(Long.valueOf(userInfo.getAccount()));

        signInfoProvider.setSignInfo(signInfo);

    }

    public List<SignInfo> querySignInfo() {
        UserInfo userInfo = UserController.getUserInfo();
        String userName = userInfo.getUserName();
        List<SignInfo> signInfos = signInfoProvider.signInfosList(userName);
//        Collections.verse(signInfos);

        return signInfos;
    }

    /**
     * 设定管理员的Wifi名称
     */
    public void setUserWifiName() {

    }

    /**
     * 校验用户连接的WiFi
     * 未开启  0
     * 开启，但不是当前WiFi不可以打卡   1
     * 开启，可以打卡   2
     *
     * @return
     */
    public int checkWifiConnectName(String thisWifiName) {
        String userWifiName = getUserWifiName();
        if (userWifiName.isEmpty()){
            return 0;
        }else if(userWifiName.equals(thisWifiName)) {
            return 2;
        }else {
            return 1;
        }
    }

    /**
     *  获取Wifi名称
     */
    private String getUserWifiName(){
        // 获取子账户的Id
        UserInfo userInfo1 = UserController.getUserInfo();
        UserInfo userInfo = userProvider.querySonUserInfoById(userInfo1.get_id(),Long.valueOf(userInfo1.getAccount()));
        String wiFiName = userInfo.getWiFiName();
        if (wiFiName==null){
            wiFiName = "";
        }
        return wiFiName;
    }

    public void SetUserWifiName(String WiFiName){
        Long userId = UserController.getUserId();
        UserInfo userInfo = userProvider.queryUserInfoById(userId);
        userInfo.setWiFiName(WiFiName);
        userProvider.insertOrChangeUser(userInfo);
    }
}
