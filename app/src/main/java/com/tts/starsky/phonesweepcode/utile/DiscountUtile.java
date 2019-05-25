package com.tts.starsky.phonesweepcode.utile;

import com.tts.starsky.phonesweepcode.Init;
import com.tts.starsky.phonesweepcode.db.bean.Discount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 折扣计算
 */
public class DiscountUtile {

    /**
     * 打折方式
     * 1.打几折
     * 2.满几赠几
     * 3.满减
     */
    public enum WAY {
        DISCOUNT,
        FUUL_SUB,
        FULL_SUBTRACTION
    }

    public static double getCountReslut(Double oldPrice, int discountListNum) {
        double countReslut = getCountReslut(oldPrice, 0, discountListNum);
        return countReslut;
    }

    public static double getCountReslut(Double oldPrice, int goodsNum, int discountListNum) {

        List<Discount> discounts = Init.discounts;
        Discount discount = discounts.get(discountListNum);
        WAY way = WAY.values()[discount.getDiscountWay()];
        double count = 0;
        switch (way) {
            case DISCOUNT:
                count = discountCount(oldPrice, discount);
                break;
            case FUUL_SUB:
                break;
            case FULL_SUBTRACTION:
                break;
        }
        return count;
    }

    /**
     *  打折计算
     * @param oldPrice 老价格
     * @param discount 打折实体类
     * @return  新价格
     */
    private static double discountCount(Double oldPrice, Discount discount) {
        int discountNum = discount.getDiscountNum();
        double newPrice = oldPrice * 0.01 * discountNum;

        // 四舍五入 保留一位
        BigDecimal b = new BigDecimal(newPrice);
        newPrice = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        return newPrice;
    }

}

