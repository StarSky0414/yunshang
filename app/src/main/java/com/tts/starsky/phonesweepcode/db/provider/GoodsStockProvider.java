package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.dao.GoodsStockDao;

/**
 *  商品数据库提供者
 */
public class GoodsStockProvider extends DBProviderBase {

    private final GoodsInfoProvider goodsInfoProvider;

    public GoodsStockProvider() {
        goodsInfoProvider = new GoodsInfoProvider();
    }

    /**
     * 进货数据插入
     */
    public long goodsStockInsert(GoodsInfo goodsInfo, GoodsStock goodsStock) {

        int newStockNum = goodsInfo.getNewStockNum();
        goodsInfo.setNewStockNum(newStockNum + goodsStock.getIntoStockNum());  // 增加库存信息
        goodsInfo.setUserId(Long.parseLong(UserController.getFatherUserId()));
        goodsInfoProvider.goodsInfoInsert(goodsInfo);  //保存商品信息
        long insert = dbSession.getGoodsStockDao().insert(goodsStock); // 保存库存信息
        return insert;
    }

    /**
     *  通过商品编码号获取商品入库信息
     * @param goodsBarCode 商品编码号
     * @return 最早商品入库信息
     */
    public GoodsStock getGoodsStockByGoodsBarCode(String goodsBarCode,long userId) {
        GoodsStock unique = dbSession.getGoodsStockDao().queryBuilder()
                .where(GoodsStockDao.Properties.GoodsId.eq(goodsBarCode))
                .where(GoodsStockDao.Properties.ResidueGoodsNum.notEq(0))
                .where(GoodsStockDao.Properties.UserId.eq(userId))
                .orderAsc(GoodsStockDao.Properties.StockId)
                .limit(1).unique();
        return unique;
    }

    /**
     *  保存商品入库信息
     * @param goodsStockByGoodsBarCode 商品编码号
     */
    public void updateGoodsStock(GoodsStock goodsStockByGoodsBarCode) {
        dbSession.getGoodsStockDao().update(goodsStockByGoodsBarCode); // 保存库存信息
    }
}
