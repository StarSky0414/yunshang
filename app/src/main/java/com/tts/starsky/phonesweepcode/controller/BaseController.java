package com.tts.starsky.phonesweepcode.controller;

import com.tts.starsky.phonesweepcode.db.provider.GoodsInfoProvider;
import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;
import com.tts.starsky.phonesweepcode.db.provider.SalesProvider;
import com.tts.starsky.phonesweepcode.db.provider.SalesToGoodsProvider;

public class BaseController {

    GoodsInfoProvider goodsInfoProvider;
    GoodsStockProvider goodsStockProvider;
    SalesToGoodsProvider salesToGoodsProvider;
    SalesProvider salesProvider;

    public BaseController() {
        goodsInfoProvider = new GoodsInfoProvider();
        goodsStockProvider = new GoodsStockProvider();
        salesToGoodsProvider = new SalesToGoodsProvider();
        salesProvider = new SalesProvider();
    }
}


