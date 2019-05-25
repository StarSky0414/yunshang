package com.tts.starsky.phonesweepcode.http;

public class Goods {

    private String goodsName;
    private String barcode;
    private String brand;
    private String supplier;
    private String standard;
    private String price;
    private String outPrice;

    public Goods(String goodsName, String barcode, String brand, String supplier, String standard, String price, String outPrice) {
        this.goodsName = goodsName;
        this.barcode = barcode;
        this.brand = brand;
        this.supplier = supplier;
        this.standard = standard;
        this.price = price;
        this.outPrice = outPrice;
    }

    public Goods() {
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(String outPrice) {
        this.outPrice = outPrice;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsName='" + goodsName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", brand='" + brand + '\'' +
                ", supplier='" + supplier + '\'' +
                ", standard='" + standard + '\'' +
                ", price='" + price + '\'' +
                ", outPrice='" + outPrice + '\'' +
                '}';
    }
}
