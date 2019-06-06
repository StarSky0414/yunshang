package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.provider.DBInitTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SalesControllerTest extends DBInitTest {
    SalesController salesController = new SalesController();

    @Test
    public void showSalesAll() {
        List<Sales> salesList = salesController.showSalesAll();
        for(Sales sales : salesList){
            System.out.println(sales.toString());
        }
    }

    @Test
    public void makeSales() {
        GoodsInfo goodsInfo = new GoodsInfo("xxxx123", "xxxx123", "xxxx123", 0, 0,0);
        ArrayList<GoodsInfo> goodsInfos = new ArrayList<>();
        goodsInfos.add(goodsInfo);
        goodsInfos.add(goodsInfo);
        goodsInfos.add(goodsInfo);
        salesController.makeSales(goodsInfos,12,33,11,1);
    }
}