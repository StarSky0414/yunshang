package com.tts.starsky.phonesweepcode.db.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.bean.GoodsTypeInfo;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;

import com.tts.starsky.phonesweepcode.db.dao.DiscountDao;
import com.tts.starsky.phonesweepcode.db.dao.GoodsInfoDao;
import com.tts.starsky.phonesweepcode.db.dao.GoodsStockDao;
import com.tts.starsky.phonesweepcode.db.dao.GoodsTypeInfoDao;
import com.tts.starsky.phonesweepcode.db.dao.SalesDao;
import com.tts.starsky.phonesweepcode.db.dao.SalesToGoodsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig discountDaoConfig;
    private final DaoConfig goodsInfoDaoConfig;
    private final DaoConfig goodsStockDaoConfig;
    private final DaoConfig goodsTypeInfoDaoConfig;
    private final DaoConfig salesDaoConfig;
    private final DaoConfig salesToGoodsDaoConfig;

    private final DiscountDao discountDao;
    private final GoodsInfoDao goodsInfoDao;
    private final GoodsStockDao goodsStockDao;
    private final GoodsTypeInfoDao goodsTypeInfoDao;
    private final SalesDao salesDao;
    private final SalesToGoodsDao salesToGoodsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        discountDaoConfig = daoConfigMap.get(DiscountDao.class).clone();
        discountDaoConfig.initIdentityScope(type);

        goodsInfoDaoConfig = daoConfigMap.get(GoodsInfoDao.class).clone();
        goodsInfoDaoConfig.initIdentityScope(type);

        goodsStockDaoConfig = daoConfigMap.get(GoodsStockDao.class).clone();
        goodsStockDaoConfig.initIdentityScope(type);

        goodsTypeInfoDaoConfig = daoConfigMap.get(GoodsTypeInfoDao.class).clone();
        goodsTypeInfoDaoConfig.initIdentityScope(type);

        salesDaoConfig = daoConfigMap.get(SalesDao.class).clone();
        salesDaoConfig.initIdentityScope(type);

        salesToGoodsDaoConfig = daoConfigMap.get(SalesToGoodsDao.class).clone();
        salesToGoodsDaoConfig.initIdentityScope(type);

        discountDao = new DiscountDao(discountDaoConfig, this);
        goodsInfoDao = new GoodsInfoDao(goodsInfoDaoConfig, this);
        goodsStockDao = new GoodsStockDao(goodsStockDaoConfig, this);
        goodsTypeInfoDao = new GoodsTypeInfoDao(goodsTypeInfoDaoConfig, this);
        salesDao = new SalesDao(salesDaoConfig, this);
        salesToGoodsDao = new SalesToGoodsDao(salesToGoodsDaoConfig, this);

        registerDao(Discount.class, discountDao);
        registerDao(GoodsInfo.class, goodsInfoDao);
        registerDao(GoodsStock.class, goodsStockDao);
        registerDao(GoodsTypeInfo.class, goodsTypeInfoDao);
        registerDao(Sales.class, salesDao);
        registerDao(SalesToGoods.class, salesToGoodsDao);
    }
    
    public void clear() {
        discountDaoConfig.clearIdentityScope();
        goodsInfoDaoConfig.clearIdentityScope();
        goodsStockDaoConfig.clearIdentityScope();
        goodsTypeInfoDaoConfig.clearIdentityScope();
        salesDaoConfig.clearIdentityScope();
        salesToGoodsDaoConfig.clearIdentityScope();
    }

    public DiscountDao getDiscountDao() {
        return discountDao;
    }

    public GoodsInfoDao getGoodsInfoDao() {
        return goodsInfoDao;
    }

    public GoodsStockDao getGoodsStockDao() {
        return goodsStockDao;
    }

    public GoodsTypeInfoDao getGoodsTypeInfoDao() {
        return goodsTypeInfoDao;
    }

    public SalesDao getSalesDao() {
        return salesDao;
    }

    public SalesToGoodsDao getSalesToGoodsDao() {
        return salesToGoodsDao;
    }

}
