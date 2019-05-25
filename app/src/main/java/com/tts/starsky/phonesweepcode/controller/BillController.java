package com.tts.starsky.phonesweepcode.controller;


import com.tts.starsky.phonesweepcode.bean.Data;
import com.tts.starsky.phonesweepcode.Init;
import com.tts.starsky.phonesweepcode.bean.BillBean;
import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;
import com.tts.starsky.phonesweepcode.utile.SQL;

import java.util.ArrayList;
import java.util.List;

/**
 * 流水账单写入
 */
public class BillController extends BaseController {

    private final List<Data> dataList;

    public BillController(List<Data> dataList) {
        this.dataList = dataList;
    }

    /**
     * 创建流水账单
     */
    public BillBean makeBill() {

        BillBean billBean = new BillBean();

        String thisTime = SQL.getThisTime();
        String timePram = SQL.getTimePram();

        double profit = 0;
        double originalPrice = 0;
        double realityPrice = 0;

        for (Data data : dataList) {
            // 用于获取商品信息，以及商品进货表
            String goodsBarCode = data.getGoodsBarCode();
            // 原本售价
            double goodsPrice = data.getGoodsPrice();
            // 实际售价
            double goodsDisconutPrice = data.getGoodsDisconutPrice();
            int goodsNum = data.getGoodsNum();

            // 折扣实体类
            int discountSpinnerListNum = data.getDiscountSpinnerListNum();
            Discount discount = Init.discounts.get(discountSpinnerListNum);

            // 进货价格
            ArrayList<BillGoodsPrice> billGoodsPrices = new ArrayList<>();
            goodsStockCount(goodsBarCode, goodsNum, billGoodsPrices);

            // goodsinfo 中库存扣除
            goodsInfoProvider.decStockNum(goodsBarCode, goodsNum);

            // 商品销售List
            ArrayList<SalesToGoods> salesToGoodsArrayList = new ArrayList<>();
            for (BillGoodsPrice billGoodsPrice : billGoodsPrices) {
                for (int i = 0; i < billGoodsPrice.num; i++) {
                    SalesToGoods salesToGoods = new SalesToGoods();
                    salesToGoods.setDiscountId(discount.getDiscountId());
                    salesToGoods.setGoodsId(goodsBarCode);
                    salesToGoods.setIntoStockPrice(billGoodsPrice.intoStockPrice);
                    salesToGoods.setSalesId(timePram);
                    salesToGoods.setOriginalPrice(goodsPrice);
                    salesToGoods.setRealityPrice(goodsDisconutPrice);
                    // 盈亏计算
                    double mProfit = goodsDisconutPrice - billGoodsPrice.intoStockPrice;
                    salesToGoods.setProfit(mProfit);

                    salesToGoodsArrayList.add(salesToGoods);

                    profit += mProfit;
                    originalPrice += goodsPrice;
                    realityPrice += goodsDisconutPrice;

                }
            }
            salesToGoodsProvider.salesToGoodsDaoInsertList(salesToGoodsArrayList);

        }

        // 流水账单数据库写入
        Sales sales = new Sales();
        sales.setSalesId(timePram);
        sales.setCreateTime(thisTime);
        sales.setProfit(profit);
        sales.setOriginalPrice(originalPrice);
        sales.setRealityPrice(realityPrice);
        salesProvider.salesInsert(sales);

        // 创建要向下层传递的bean
        billBean.setCreateTime(thisTime);
        billBean.setId(timePram);
        billBean.setPrice(originalPrice);
        billBean.setTransferOfProfits(originalPrice-realityPrice);
        billBean.setEffectivelyPrice(realityPrice);
        billBean.setProfit(profit);

        return billBean;

    }

    private void goodsStockCount(String goodsBarCode, int goodsNum, ArrayList<BillGoodsPrice> billGoodsPrices) {
        GoodsStock goodsStockByGoodsBarCode = goodsStockProvider.getGoodsStockByGoodsBarCode(goodsBarCode);
        int tempNum = goodsStockByGoodsBarCode.getResidueGoodsNum() - goodsNum;
        BillGoodsPrice billGoodsPrice = new BillGoodsPrice();
        // 如果库存量足够
        if (tempNum >= 0) {
            goodsStockByGoodsBarCode.setResidueGoodsNum(tempNum);
            goodsStockProvider.updateGoodsStock(goodsStockByGoodsBarCode);
            billGoodsPrice.num = Math.abs(goodsNum);
        } else {
            billGoodsPrice.num = goodsStockByGoodsBarCode.getResidueGoodsNum();
            goodsStockByGoodsBarCode.setResidueGoodsNum(0);
            goodsStockProvider.updateGoodsStock(goodsStockByGoodsBarCode);
            int abs = Math.abs(tempNum);
            goodsStockCount(goodsBarCode, abs, billGoodsPrices);
        }
        billGoodsPrice.intoStockPrice = goodsStockByGoodsBarCode.getIntoStockPrice();
        billGoodsPrices.add(billGoodsPrice);

    }




    public class BillGoodsPrice {
        private int num;
        private double intoStockPrice;
    }

}
