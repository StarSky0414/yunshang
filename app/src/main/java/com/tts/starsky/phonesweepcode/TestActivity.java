package com.tts.starsky.phonesweepcode;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.tts.starsky.phonesweepcode.controller.SalesController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;
import com.tts.starsky.phonesweepcode.db.dao.DaoSession;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        SalesController salesController = new SalesController();
        List<Sales> salesList = salesController.showSalesAll();
        for(Sales sales : salesList){
            System.out.println(sales.toString());
//            System.out.println(sales.getDiscounts());
            List<SalesToGoods> salesToGoodsList = sales.getSalesToGoods();
            for (SalesToGoods salesToGoods: salesToGoodsList){
                System.out.println("==========="+salesToGoods.toString());
                GoodsInfo goodsInfo = salesToGoods.getGoodsInfo();

                System.out.println("=+++++++++++"+ goodsInfo.toString());
            }
            System.out.println("=================");
        }


    }
}
