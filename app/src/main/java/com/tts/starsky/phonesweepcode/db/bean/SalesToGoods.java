package com.tts.starsky.phonesweepcode.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.tts.starsky.phonesweepcode.db.dao.DaoSession;
import com.tts.starsky.phonesweepcode.db.dao.GoodsInfoDao;
import com.tts.starsky.phonesweepcode.db.dao.SalesToGoodsDao;
import com.tts.starsky.phonesweepcode.db.dao.DiscountDao;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 商品销售表
 *
 * 虚键
 * 流水id
 * 商品id
 *
 * 进货价格
 * 原本售价
 * 实际售价
 * 折扣方式id
 * 盈利
 */

@Entity
public class SalesToGoods {

    @Id
    private Long _id;

    private String salesId;

    private String goodsId;
    @ToOne(joinProperty = "goodsId")
    GoodsInfo goodsInfo;

    private double intoStockPrice = 0;
    private double originalPrice = 0;
    private double realityPrice = 0;

    private long discountId;
    @ToOne(joinProperty = "discountId")
    private Discount discounts;

    private double profit;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 776379832)
    private transient SalesToGoodsDao myDao;

    @Generated(hash = 1349401009)
    public SalesToGoods(Long _id, String salesId, String goodsId,
            double intoStockPrice, double originalPrice, double realityPrice,
            long discountId, double profit) {
        this._id = _id;
        this.salesId = salesId;
        this.goodsId = goodsId;
        this.intoStockPrice = intoStockPrice;
        this.originalPrice = originalPrice;
        this.realityPrice = realityPrice;
        this.discountId = discountId;
        this.profit = profit;
    }

    @Generated(hash = 819063774)
    public SalesToGoods() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getSalesId() {
        return this.salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
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

    public double getOriginalPrice() {
        return this.originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getRealityPrice() {
        return this.realityPrice;
    }

    public void setRealityPrice(double realityPrice) {
        this.realityPrice = realityPrice;
    }

    public long getDiscountId() {
        return this.discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public double getProfit() {
        return this.profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Generated(hash = 1315573622)
    private transient String goodsInfo__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1725143264)
    public GoodsInfo getGoodsInfo() {
        String __key = this.goodsId;
        if (goodsInfo__resolvedKey == null || goodsInfo__resolvedKey != __key) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GoodsInfoDao targetDao = daoSession.getGoodsInfoDao();
            GoodsInfo goodsInfoNew = targetDao.load(__key);
            synchronized (this) {
                goodsInfo = goodsInfoNew;
                goodsInfo__resolvedKey = __key;
            }
        }
        return goodsInfo;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1664241164)
    public void setGoodsInfo(GoodsInfo goodsInfo) {
        synchronized (this) {
            this.goodsInfo = goodsInfo;
            goodsId = goodsInfo == null ? null : goodsInfo.getGoodsId();
            goodsInfo__resolvedKey = goodsId;
        }
    }

    @Generated(hash = 618601867)
    private transient Long discounts__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1782482411)
    public Discount getDiscounts() {
        long __key = this.discountId;
        if (discounts__resolvedKey == null
                || !discounts__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DiscountDao targetDao = daoSession.getDiscountDao();
            Discount discountsNew = targetDao.load(__key);
            synchronized (this) {
                discounts = discountsNew;
                discounts__resolvedKey = __key;
            }
        }
        return discounts;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1544424846)
    public void setDiscounts(@NotNull Discount discounts) {
        if (discounts == null) {
            throw new DaoException(
                    "To-one property 'discountId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.discounts = discounts;
            discountId = discounts.getDiscountId();
            discounts__resolvedKey = discountId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 176207275)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSalesToGoodsDao() : null;
    }



}