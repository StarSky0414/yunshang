package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.SignInfo;
import com.tts.starsky.phonesweepcode.db.provider.SignInfoProvider;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SignInfoController {

    private final SignInfoProvider signInfoProvider;

    public SignInfoController() {
        signInfoProvider = new SignInfoProvider();
    }

    public void setSignInfo(String userName){

        Long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));

        SignInfo signInfo = new SignInfo();
        signInfo.setUserName(userName);
        signInfo.setStartTime(String.valueOf(sd));

        signInfoProvider.setSignInfo(signInfo);
    }

    public List<SignInfo> querySignInfo(String userName){
        List<SignInfo> signInfos = signInfoProvider.signInfosList(userName);
//        Collections.verse(signInfos);

        return signInfos;
    }
}
