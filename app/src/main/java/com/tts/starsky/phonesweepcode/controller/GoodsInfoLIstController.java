package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsInfoProvider;

import java.util.List;

/**
 *  商品控制层
 */
public class GoodsInfoLIstController {

    private final GoodsInfoProvider goodsInfoProvider;

    public GoodsInfoLIstController() {
        goodsInfoProvider = new GoodsInfoProvider();
    }

    // 获取所有商品信息
    public List<GoodsInfo> getAllGoodsInfo(){
        List<GoodsInfo> goodsInfos = goodsInfoProvider.showAllGoodsInfoList();
        return goodsInfos;
    }

    public void addGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfoProvider.goodsInfoInsert(goodsInfo);
    }
    // 删除商品信息
    public void deleGoodsInfo(String goodsInfoId) {
        goodsInfoProvider.removeGoodsInfoById(goodsInfoId);
    }
    // 修改商品信息
    public void changeGoodsInfo(GoodsInfo goodsInfo){
        goodsInfoProvider.goodsInfoChange(goodsInfo);
    }
}
