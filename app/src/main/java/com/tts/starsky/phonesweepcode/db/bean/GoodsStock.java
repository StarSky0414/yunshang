package com.tts.starsky.phonesweepcode.db.bean;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 进货id
 * 商品id
 * 进货价格
 * 进货数量
 * 进货剩余数量
 */
@Entity
public class GoodsStock {

    @Id(autoincrement = true)
    private Long stockId;
    private String goodsId;

    private double intoStockPrice=0;
    private int intoStockNum=0;
    private int residueGoodsNum=0;
    @Generated(hash = 1954877351)
    public GoodsStock(Long stockId, String goodsId, double intoStockPrice,
            int intoStockNum, int residueGoodsNum) {
        this.stockId = stockId;
        this.goodsId = goodsId;
        this.intoStockPrice = intoStockPrice;
        this.intoStockNum = intoStockNum;
        this.residueGoodsNum = residueGoodsNum;
    }
    @Generated(hash = 16825727)
    public GoodsStock() {
    }
    public Long getStockId() {
        return this.stockId;
    }
    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
    public String getGoodsId() {
        return this.goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public double getIntoStockPrice() {
        return this.intoStockPrice;
    }
    public void setIntoStockPrice(double intoStockPrice) {
        this.intoStockPrice = intoStockPrice;
    }
    public int getIntoStockNum() {
        return this.intoStockNum;
    }
    public void setIntoStockNum(int intoStockNum) {
        this.intoStockNum = intoStockNum;
    }
    public int getResidueGoodsNum() {
        return this.residueGoodsNum;
    }
    public void setResidueGoodsNum(int residueGoodsNum) {
        this.residueGoodsNum = residueGoodsNum;
    }

}
