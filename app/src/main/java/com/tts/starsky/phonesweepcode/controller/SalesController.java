package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;
import com.tts.starsky.phonesweepcode.db.provider.SalesProvider;
import com.tts.starsky.phonesweepcode.db.provider.SalesToGoodsProvider;
import com.tts.starsky.phonesweepcode.utile.SQL;

import java.util.ArrayList;
import java.util.List;

/**
 * 流水账单控制层
 */
public class SalesController {

    private final SalesToGoodsProvider salesToGoodsProvider;
    private final SalesProvider salesProvider;

    public SalesController() {
        salesToGoodsProvider = new SalesToGoodsProvider();
        salesProvider = new SalesProvider();
    }

    public List<Sales> showSalesAll() {
        List<Sales> salesList = salesProvider.salesQueryAll();
        return salesList;
    }

    /**
     * 创建商品流水
     *
     * @param goodsInfos     商品信息集合
     * @param intoStockPrice 当前对应库存的进货价格
     * @param originalPrice  应售价格
     * @param realityPrice   实际售价格
     * @param discountId     折扣id
     */
    public void makeSales(List<GoodsInfo> goodsInfos, int intoStockPrice, int originalPrice, int realityPrice,
                          long discountId) {
        String timePram = SQL.getTimePram();

        // 创建一个账单实体类
        Sales sales = new Sales();
//        sales.setDiscountId(discountId);
//        sales.setIntoStockPrice(intoStockPrice);
        sales.setOriginalPrice(originalPrice);
        sales.setRealityPrice(realityPrice);
        sales.setSalesId(timePram);

        // 流水账单中多商品信息
        List<SalesToGoods> salesToGoodsList = new ArrayList<>();
        for (GoodsInfo goodsInfo : goodsInfos) {
            String goodsId = goodsInfo.getGoodsId();
//            SalesToGoods salesToGoods = new SalesToGoods(null, timePram, goodsId);
//            salesToGoodsList.add(salesToGoods);
        }

        // 存储数据库
        salesToGoodsProvider.salesToGoodsDaoInsertList(salesToGoodsList);
        salesProvider.salesInsert(sales);
    }

}
