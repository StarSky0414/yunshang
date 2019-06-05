package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.dao.DiscountDao;

import java.util.List;

public class DiscountProvider extends DBProviderBase{
    DiscountDao discountDao = dbSession.getDiscountDao();

    public void discountInsert(Discount discount){
        discountDao.save(discount);
    }

    public Discount discountQuery(String discountId){
        Discount discount = discountDao.queryBuilder().where(DiscountDao.Properties.DiscountId.eq(discountId)).unique();
        return discount;
    }

    public void discountChange(Discount discount){
        discountDao.save(discount);
    }

    public List<Discount> discountQueryAll(){
        List<Discount> discountList = discountDao.queryBuilder().list();
        return discountList;
    }

    public void removeDiscountById(Long discountId) {
        discountDao.deleteByKey(discountId);
    }

    public void changeDiscount(Discount discount){
        discountDao.save(discount);
    }
}
