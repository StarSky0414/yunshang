package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsInfoProvider;

import java.util.List;

/**
 *  折扣控制层
 */
public class GoodsInfoLIstController {

    private final GoodsInfoProvider goodsInfoProvider;

    public GoodsInfoLIstController() {
        goodsInfoProvider = new GoodsInfoProvider();
    }

    // 获取所有折扣信息
    public List<GoodsInfo> getAllGoodsInfo(){
        List<GoodsInfo> goodsInfos = goodsInfoProvider.showAllGoodsInfoList();
        return goodsInfos;
    }

    public void addGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfoProvider.goodsInfoInsert(goodsInfo);
    }
    // 删除折扣信息
    public void deleGoodsInfo(String goodsInfoId) {
        goodsInfoProvider.removeGoodsInfoById(goodsInfoId);
    }
    // 修改折扣信息
    public void changeGoodsInfo(GoodsInfo goodsInfo){
        goodsInfoProvider.goodsInfoChange(goodsInfo);
    }
}
