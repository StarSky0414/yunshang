package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.dao.DiscountDao;

import java.util.List;

/**
 *  折扣管理的数据提供成
 *  用于与数据库交互
 */
public class DiscountProvider extends DBProviderBase{
    // 获取DB对应的实体
    DiscountDao discountDao = dbSession.getDiscountDao();

    // 保存实体，也就是数据库的插入
    public void discountInsert(Discount discount){
        discountDao.save(discount);
    }

    // 数据库信息的查询 【折扣ID】
    public Discount discountQuery(String discountId){
        Discount discount = discountDao.queryBuilder().where(DiscountDao.Properties.DiscountId.eq(discountId)).unique();
        return discount;
    }

    // 数据库修改
    public void discountChange(Discount discount){
        discountDao.save(discount);
    }

    // 查询所有数据信息
    public List<Discount> discountQueryAll(){
        List<Discount> discountList = discountDao.queryBuilder().list();
        return discountList;
    }

    // 删除其中一个 【折扣ID】
    public void removeDiscountById(Long discountId) {
        discountDao.deleteByKey(discountId);
    }

}
