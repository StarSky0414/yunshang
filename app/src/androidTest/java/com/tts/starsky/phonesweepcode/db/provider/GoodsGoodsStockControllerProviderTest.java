package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsGoodsStockControllerProviderTest extends DBInitTest {

    @Test
    public void goodsStockInsert() {

        for (int i = 0; i < 5; i++) {
            GoodsInfoProvider goodsInfoProvider = new GoodsInfoProvider();
//            GoodsInfo goodsInfo = goodsInfoProvider.goodsQueryByBrCode("6924743915770");
            GoodsInfo goodsInfo = goodsInfoProvider.goodsQueryByBrCode("6922507096369");

            GoodsStock goodsStock = new GoodsStock();
            goodsStock.setGoodsId(goodsInfo.getGoodsBarCode());
            goodsStock.setIntoStockNum(10);
            goodsStock.setIntoStockPrice(6);
            goodsStock.setResidueGoodsNum(goodsStock.getIntoStockNum());

            GoodsStockProvider goodsStockProvider = new GoodsStockProvider();
            goodsStockProvider.goodsStockInsert(goodsInfo, goodsStock);
        }
    }
}