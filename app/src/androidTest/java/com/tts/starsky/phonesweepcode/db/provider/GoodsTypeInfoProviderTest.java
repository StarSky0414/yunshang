package com.tts.starsky.phonesweepcode.db.provider;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTypeInfoProviderTest extends DBInitTest{

    private final GoodsTypeInfoProvider goodsTypeInfoProvider = new GoodsTypeInfoProvider();

    @Test
    public void goodsTypeInsertOne() {
        goodsTypeInfoProvider.goodsTypeInsertOne("洗化",1);
        goodsTypeInfoProvider.goodsTypeInsertOne("护肤",2);
        goodsTypeInfoProvider.goodsTypeInsertOne("彩妆",3);
    }

    @Test
    public void goodsTypeInsertTwo() {
        // 1
        goodsTypeInfoProvider.goodsTypeInsertTwo("奇强1",1);
        goodsTypeInfoProvider.goodsTypeInsertTwo("船牌1",1);
        goodsTypeInfoProvider.goodsTypeInsertTwo("设定1",1);
        goodsTypeInfoProvider.goodsTypeInsertTwo("存储1",1);
        // 2
        goodsTypeInfoProvider.goodsTypeInsertTwo("奇强2",2);
        goodsTypeInfoProvider.goodsTypeInsertTwo("船牌2",2);
        goodsTypeInfoProvider.goodsTypeInsertTwo("设定2",2);
        goodsTypeInfoProvider.goodsTypeInsertTwo("存储2",2);
        // 3
        goodsTypeInfoProvider.goodsTypeInsertTwo("奇强3",3);
        goodsTypeInfoProvider.goodsTypeInsertTwo("船牌3",3);
        goodsTypeInfoProvider.goodsTypeInsertTwo("设定3",3);
        goodsTypeInfoProvider.goodsTypeInsertTwo("存储3",3);
    }

    @Test
    public void goodsTypeInsertThree() {
        goodsTypeInfoProvider.goodsTypeInsertThree("水",7);
    }

    @Test
    public void queryGoodsTypeListByFatherId() {
    }

    @Test
    public void deleGoodsTypeById() {
    }

    @Test
    public void clear(){
        dbSession.getGoodsTypeInfoDao().deleteAll();
    }

    @Before
    public void befer(){
//        clear();
    }
}