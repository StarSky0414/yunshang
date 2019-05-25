package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsInfoProviderTest extends DBInitTest{

    GoodsInfoProvider goodsInfoProvider = new GoodsInfoProvider();

    @Test
    public void testGoodsInfoInsert() {
        GoodsInfo goodsInfo = new GoodsInfo("6924743915770", "6924743915770", "薯片", 20, 20);
        goodsInfoProvider.goodsInfoInsert(goodsInfo);
        goodsInfo = new GoodsInfo("6922507096369", "6922507096369", "冰红茶", 10, 20);
        goodsInfoProvider.goodsInfoInsert(goodsInfo);
    }
}