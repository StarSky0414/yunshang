package com.tts.starsky.phonesweepcode.controller;

import android.content.Context;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;

import java.util.ArrayList;
import java.util.List;

public class ContentManager {

    private Context context;

    public ContentManager(Context context) {
        this.context = context;
    }

    public List<GoodsInfo> getGoodsInfoList(Long type_concrete_id) {

        /*DatabaseOP databaseOP = new DatabaseOP(context);
        List<CommodityInfo> commodityInfoList = databaseOP.getGoodsInfoList(type_concrete_id);*/
        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        return goodsInfoList;
    }
}
