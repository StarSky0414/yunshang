package com.tts.starsky.phonesweepcode.bean;

public class Data {

    private String goodsName;
    private String goodsBarCode;
    private int goodsNum = 1;
    private double goodsPrice;
    private double goodsDisconutPrice = -1;
    private int discountSpinnerListNum = -1;
    // 根据库存计算出最大数
    private int maxNum;
    private double goodsAllPrice;

    public Data() {
    }


    public Data(String goodsName, int goodsNum, double goodsPrice, int discountSpinnerListNum, int maxNum) {
        this.goodsName = goodsName;
        this.goodsNum = goodsNum;
        this.goodsPrice = goodsPrice;
        this.discountSpinnerListNum = discountSpinnerListNum;
        this.maxNum = maxNum;
    }

    public String getGoodsBarCode() {
        return goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    public double getGoodsDisconutPrice() {
        return goodsDisconutPrice;
    }

    public void setGoodsDisconutPrice(double goodsDisconutPrice) {
        this.goodsDisconutPrice = goodsDisconutPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getDiscountSpinnerListNum() {
        return discountSpinnerListNum;
    }

    public void setDiscountSpinnerListNum(int discountSpinnerListNum) {
        this.discountSpinnerListNum = discountSpinnerListNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public double getGoodsAllPrice() {
        return goodsAllPrice;
    }

    public void setGoodsAllPrice(double goodsAllPrice) {
        this.goodsAllPrice = goodsAllPrice;
    }

    @Override
    public String toString() {
        return "Data{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", goodsPrice=" + goodsPrice +
                ", goodsDisconutPrice=" + goodsDisconutPrice +
                ", discountSpinnerListNum=" + discountSpinnerListNum +
                '}';
    }

    public int addNum() {
        goodsNum++;
        if (goodsNum > maxNum) {
            goodsNum = maxNum;
        }
        return goodsNum;
    }

    public int cutNum() {
        goodsNum--;
        // 杜绝负数
        if (goodsNum <= 0) {
            goodsNum = 0;
        }
        return goodsNum;
    }

    public double addPrice() {
        goodsAllPrice += goodsPrice;
        return goodsAllPrice;
    }

    public double cutPrice() {
        goodsAllPrice -= goodsPrice;
        return goodsAllPrice;
    }
}
