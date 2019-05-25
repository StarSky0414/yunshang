package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;
import com.tts.starsky.phonesweepcode.db.dao.SalesDao;
import com.tts.starsky.phonesweepcode.utile.SQL;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public List<Sales> salesQueryAll(){
        List<Sales> salesList = salesDao.queryBuilder().orderDesc(SalesDao.Properties.CreateTime).list();
        return salesList;
    }

}
