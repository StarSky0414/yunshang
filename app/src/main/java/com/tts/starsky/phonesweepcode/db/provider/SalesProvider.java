package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.dao.SalesDao;
import com.tts.starsky.phonesweepcode.utile.SQL;

import java.util.List;

/**
 * DB 账单流水
 */
public class SalesProvider extends DBProviderBase {

    SalesDao salesDao = dbSession.getSalesDao();

    public void salesInsert(Sales sales){
        sales.setCreateTime(SQL.getThisTime());
        salesDao.insert(sales);
    }

    public Sales salesQuery(Long id){
        Sales sales = salesDao.queryBuilder().where(SalesDao.Properties.SalesId.eq(id)).unique();
        return sales;
    }

    public List<Sales> salesQueryAllByFatherUserId(long fatherId){
        List<Sales> salesList = salesDao.queryBuilder()
                .where(SalesDao.Properties.UserFatherId.eq(fatherId))
                .orderDesc(SalesDao.Properties.CreateTime).list();
        return salesList;
    }
    public List<Sales> salesQueryAllBySonUserId(long sonId){
        List<Sales> salesList = salesDao.queryBuilder()
                .where(SalesDao.Properties.UserSonId.eq(sonId))
                .orderDesc(SalesDao.Properties.CreateTime).list();
        return salesList;
    }

}
