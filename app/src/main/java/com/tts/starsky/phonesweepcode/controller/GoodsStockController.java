//package com.tts.starsky.phonesweepcode.controller;
//
//import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
//import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
//import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;
//
///**
// * 商品进货控制层
// */
//public class GoodsStockController {
//
//    private final GoodsStockProvider goodsStockProvider;
//
//    GoodsStockController() {
//        goodsStockProvider = new GoodsStockProvider();
//    }
//
//    /**
//     * 进货
//     *
//     * @param goodsInfo  商品信息
//     * @param goodsStock 进货信息
//     * @return 数据库插入 成功 true 、 失败 false
//     */
//    public boolean goodsStock(GoodsInfo goodsInfo, GoodsStock goodsStock) {
//        long l = goodsStockProvider.goodsStockInsert(goodsInfo, goodsStock);
//        return l != 0;
//    }
//}
