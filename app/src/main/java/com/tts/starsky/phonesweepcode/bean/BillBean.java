package com.tts.starsky.phonesweepcode.bean;


/**
 * 承载 账单信息
 * <p>
 * 流水id
 * 时间
 * 原价
 * 让利
 * 应付
 * 盈利
 */
public class BillBean {

    private String id;
    private String createTime;
    private double price;
    private double transferOfProfits;
    private double effectivelyPrice;
    private double profit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTransferOfProfits() {
        return transferOfProfits;
    }

    public void setTransferOfProfits(double transferOfProfits) {
        this.transferOfProfits = transferOfProfits;
    }

    public double getEffectivelyPrice() {
        return effectivelyPrice;
    }

    public void setEffectivelyPrice(double effectivelyPrice) {
        this.effectivelyPrice = effectivelyPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public BillBean() {
    }

    public BillBean(String id, String createTime, double price, double transferOfProfits, double effectivelyPrice, double profit) {
        this.id = id;
        this.createTime = createTime;
        this.price = price;
        this.transferOfProfits = transferOfProfits;
        this.effectivelyPrice = effectivelyPrice;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "BillBean{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", price=" + price +
                ", transferOfProfits=" + transferOfProfits +
                ", effectivelyPrice=" + effectivelyPrice +
                ", profit=" + profit +
                '}';
    }
}


