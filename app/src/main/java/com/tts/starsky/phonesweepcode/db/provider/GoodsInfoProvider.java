package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.dao.GoodsInfoDao;

/**
 *  DB 商品信息提供者
 */
public class GoodsInfoProvider extends DBProviderBase {

    /**
     *  商品插入或是修改
     * @param goodsInfo 要插入的商品对象
     */
    public void goodsInfoInsert(GoodsInfo goodsInfo){
        dbSession.getGoodsInfoDao().insertOrReplace(goodsInfo);
    }


    /**
     *  查询单个商品信息
     * @param theresult 商品编码号
     * @return 商品信息实体类
     */
    public GoodsInfo goodsQueryByBrCode(String theresult) {
        GoodsInfo unique = dbSession.getGoodsInfoDao().queryBuilder().where(GoodsInfoDao.Properties.GoodsId.eq(theresult)).unique();
        return unique;
    }

    /**
     *  减少库存
     * @param theresult 商品编码号
     * @param num 减少数量
     */
    public void decStockNum(String theresult,int num){
        GoodsInfo goodsInfo = goodsQueryByBrCode(theresult);
        int newStockNum = goodsInfo.getNewStockNum();
        goodsInfo.setNewStockNum(newStockNum - num);
        goodsInfoInsert(goodsInfo);
    }
}
