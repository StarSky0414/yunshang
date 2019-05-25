package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;
import com.tts.starsky.phonesweepcode.db.dao.SalesToGoodsDao;

import java.util.List;

public class SalesToGoodsProvider extends DBProviderBase{

    SalesToGoodsDao salesToGoodsDao =dbSession.getSalesToGoodsDao();

    public void salesToGoodsDaoInsertList(List<SalesToGoods> salesToGoodsList){
        salesToGoodsDao.insertInTx(salesToGoodsList);
    }
}
