package com.tts.starsky.phonesweepcode.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 折扣id
 * 折扣方式名
 * 折扣数
 * 折扣方式
 */
@Entity
public class Discount {

    @Id(autoincrement = true)
    private Long discountId;

    private String discountName;
    private int discountNum=1;
    private int discountWay=0;
    private Long userId;
    @Generated(hash = 1071616738)
    public Discount(Long discountId, String discountName, int discountNum,
            int discountWay, Long userId) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountNum = discountNum;
        this.discountWay = discountWay;
        this.userId = userId;
    }
    @Generated(hash = 1777606421)
    public Discount() {
    }


    public Long getDiscountId() {
        return this.discountId;
    }
    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }
    public String getDiscountName() {
        return this.discountName;
    }
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }
    public int getDiscountNum() {
        return this.discountNum;
    }
    public void setDiscountNum(int discountNum) {
        this.discountNum = discountNum;
    }
    public int getDiscountWay() {
        return this.discountWay;
    }
    public void setDiscountWay(int discountWay) {
        this.discountWay = discountWay;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", discountName='" + discountName + '\'' +
                ", discountNum=" + discountNum +
                ", discountWay=" + discountWay +
                '}';
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
