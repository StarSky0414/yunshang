package com.tts.starsky.phonesweepcode.view;

import android.app.Application;

import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.DBBase;
import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.provider.DiscountProvider;
import com.tts.starsky.phonesweepcode.oss.InitOssClient;
import com.tts.starsky.phonesweepcode.oss.OSSConfig;
import com.tts.starsky.phonesweepcode.utile.DiscountUtile;
import com.tts.starsky.phonesweepcode.utile.OkHttpUtil;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;

import java.util.List;

public class Init extends Application {

    public static List<Discount> discounts;

    @Override
    public void onCreate() {
        super.onCreate();
        DBBase.dbBaseinit(this);
        OkHttpUtil.initOkHttp();

        InitOssClient.initOssClient(this,OSSConfig.stsServer, OSSConfig.endPoint);
        SharedPreferencesUtil.init(this);
//        new BackUp(this).appInitBackUpCheck();
    }

    /**
     *  初始化折扣信息
     */
    public static   void discountInfoInit(){
        // 设置一个默认
        String userId = UserController.getFatherUserId();
        Discount discount = new Discount(0L, "无折扣", 100, DiscountUtile.WAY.valueOf(DiscountUtile.WAY.DISCOUNT.toString()).ordinal(),Long.parseLong(userId));
        DiscountProvider discountProvider = new DiscountProvider();
        discounts = discountProvider.discountQueryAll(Long.parseLong(userId));
        discounts.add(0,discount);
    }


}
