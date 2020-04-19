package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.dao.GoodsInfoDao;

import java.util.List;

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
    public GoodsInfo goodsQueryByBrCode(String theresult,long userId) {
        GoodsInfo unique = dbSession.getGoodsInfoDao().queryBuilder().where(GoodsInfoDao.Properties.GoodsId.eq(theresult)
        ).where(GoodsInfoDao.Properties.UserId.eq(userId)).unique();
        return unique;
    }

    /**
     *  减少库存
     * @param theresult 商品编码号
     * @param num 减少数量
     */
    public void decStockNum(String theresult,int num,long userId){
        GoodsInfo goodsInfo = goodsQueryByBrCode(theresult,userId);
        int newStockNum = goodsInfo.getNewStockNum();
        goodsInfo.setNewStockNum(newStockNum - num);
        goodsInfo.setUserId(Long.parseLong(UserController.getFatherUserId()));
        goodsInfoInsert(goodsInfo);
    }
    /**
     * 显示所有商品列表
     */
    public List<GoodsInfo> showAllGoodsInfoList(long userId) {
        List<GoodsInfo> list = dbSession.getGoodsInfoDao().queryBuilder().where(GoodsInfoDao.Properties.UserId.eq(userId)).list();
        return list;
    }

    /**
     * 删除单条商品信息
     */
    public void removeGoodsInfoById(String goodsInfoId) {
        dbSession.getGoodsInfoDao().deleteByKey(goodsInfoId);
    }

    /**
     *  修改商品信息
     */
    public void goodsInfoChange(GoodsInfo goodsInfo) {
        goodsInfo.setUserId(Long.parseLong(UserController.getFatherUserId()));
        dbSession.getGoodsInfoDao().insertOrReplace(goodsInfo);
    }
}
