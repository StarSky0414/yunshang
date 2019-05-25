package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.bean.Data;

import org.junit.Test;

import java.util.ArrayList;

public class BillControllerTest {

    @Test
    public void makeBill() {
        ArrayList<Data> data = new ArrayList<>();

        Data data1 = new Data();
        data1.setGoodsBarCode("6924743915770");
        data1.setGoodsNum(13);
        data1.setDiscountSpinnerListNum(0);
        data1.setGoodsPrice(22);
        data1.setGoodsDisconutPrice(12);

        data.add(data1);

        BillController billController = new BillController(data);
        billController.makeBill();
    }
}