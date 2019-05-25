package com.tts.starsky.phonesweepcode.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

import com.tts.starsky.phonesweepcode.db.dao.DaoSession;
import com.tts.starsky.phonesweepcode.db.dao.DiscountDao;
import com.tts.starsky.phonesweepcode.db.dao.SalesDao;

import org.greenrobot.greendao.annotation.NotNull;
import com.tts.starsky.phonesweepcode.db.dao.SalesToGoodsDao;

/**
 * 流水id
 * 时间
 * 盈利价格
 * 原本售价
 * 实际售价
 * 折扣方式id
 * 盈利
 */
@Entity
public class Sales {

    @Id
    private String salesId;
    @ToMany(referencedJoinProperty = "salesId")
    private List<SalesToGoods> salesToGoods;

    private String createTime;
    private double profit = 0;
    private double originalPrice = 0;
    private double realityPrice = 0;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 785387219)
    private transient SalesDao myDao;
    @Generated(hash = 86508896)
    public Sales(String salesId, String createTime, double profit,
            double originalPrice, double realityPrice) {
        this.salesId = salesId;
        this.createTime = createTime;
        this.profit = profit;
        this.originalPrice = originalPrice;
        this.realityPrice = realityPrice;
    }
    @Generated(hash = 1550837133)
    public Sales() {
    }
    public String getSalesId() {
        return this.salesId;
    }
    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public double getProfit() {
        return this.profit;
    }
    public void setProfit(double profit) {
        this.profit = profit;
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
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1480109691)
    public List<SalesToGoods> getSalesToGoods() {
        if (salesToGoods == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SalesToGoodsDao targetDao = daoSession.getSalesToGoodsDao();
            List<SalesToGoods> salesToGoodsNew = targetDao
                    ._querySales_SalesToGoods(salesId);
            synchronized (this) {
                if (salesToGoods == null) {
                    salesToGoods = salesToGoodsNew;
                }
            }
        }
        return salesToGoods;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1793687660)
    public synchronized void resetSalesToGoods() {
        salesToGoods = null;
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
    @Generated(hash = 599349852)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSalesDao() : null;
    }

}
