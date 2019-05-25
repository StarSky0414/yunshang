package com.tts.starsky.phonesweepcode.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * 商品id
 * 条形码编号
 * 商品名称
 * 当前售价
 * 当前库存
 */
@Entity
public class GoodsInfo {

    @Id
    private String goodsId;
    private String goodsBarCode;
    private String goodsName;
    private double nowPrice=0;
    private int newStockNum=0;
    @Generated(hash = 607047788)
    public GoodsInfo(String goodsId, String goodsBarCode, String goodsName,
            double nowPrice, int newStockNum) {
        this.goodsId = goodsId;
        this.goodsBarCode = goodsBarCode;
        this.goodsName = goodsName;
        this.nowPrice = nowPrice;
        this.newStockNum = newStockNum;
    }
    @Generated(hash = 1227172248)
    public GoodsInfo() {
    }
    public String getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsBarCode() {
        return this.goodsBarCode;
    }
    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }
    public String getGoodsName() {
        return this.goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public double getNowPrice() {
        return this.nowPrice;
    }
    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }
    public int getNewStockNum() {
        return this.newStockNum;
    }
    public void setNewStockNum(int newStockNum) {
        this.newStockNum = newStockNum;
    }

}
