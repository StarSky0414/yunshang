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

    // 获取所有折扣信息
    public List<Discount> getAllDiscount(){
        Long userId = UserController.getUserId();
        List<Discount> discounts = discountProvider.discountQueryAll(userId);
        return discounts;
    }

    public void addDiscount(Discount discount) {
        discountProvider.discountInsert(discount);
    }
    // 删除折扣信息
    public void deleDiscount(Long discountId) {
        discountProvider.removeDiscountById(discountId);
    }
    // 修改折扣信息
    public void changeDiscount(Discount discount){
        discountProvider.discountChange(discount);
    }
}
