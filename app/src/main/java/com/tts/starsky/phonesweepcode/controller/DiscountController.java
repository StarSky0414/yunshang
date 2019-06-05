package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.provider.DiscountProvider;

import java.util.List;

/**
 *  折扣控制层
 */
public class DiscountController {

    private final DiscountProvider discountProvider;

    public DiscountController() {
        discountProvider = new DiscountProvider();
    }

    public List<Discount> getAllDiscount(){
        List<Discount> discounts = discountProvider.discountQueryAll();
        return discounts;
    }

    public void addDiscount(Discount discount) {
        discountProvider.discountInsert(discount);
    }

    public void deleDiscount(Long discountId) {
        discountProvider.removeDiscountById(discountId);
    }

    public void changeDiscount(Discount discount){
        discountProvider.discountChange(discount);
    }
}
