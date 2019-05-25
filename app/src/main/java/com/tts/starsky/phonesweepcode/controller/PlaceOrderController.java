package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.provider.DiscountProvider;

import java.util.List;

public class PlaceOrderController {

    private final DiscountProvider discountProvider;

    public PlaceOrderController(){
        discountProvider = new DiscountProvider();
    }

    public List<Discount> getDiscountData(){
        List<Discount> discounts = discountProvider.discountQueryAll();
        return discounts;
    }
}
