package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.DBBase;
import com.tts.starsky.phonesweepcode.db.bean.SalesToGoods;
import com.tts.starsky.phonesweepcode.utile.SQL;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SalesToGoodsProviderTest extends DBInitTest{


    @Test
    public void salesToGoodsDaoInsertList(){
        SalesToGoodsProvider salesToGoodsProvider = new SalesToGoodsProvider();
        ArrayList<SalesToGoods> salesToGoods = new ArrayList<>();
        for (int i = 0; i<=5 ; i++) {
//            SalesToGoods xxxx123 = new SalesToGoods(null, SQL.getTimePram(), "xxxx123");
//            SalesToGoods xxxx123 = new SalesToGoods(null, "111", "xxxx123");
//            salesToGoods.add(xxxx123);
        }
        salesToGoodsProvider.salesToGoodsDaoInsertList(salesToGoods);
    }


    @Test
    public void clear(){
        DBBase.getDBBase().getDBSession().getSalesToGoodsDao().deleteAll();
        DBBase.getDBBase().getDBSession().getSalesDao().deleteAll();
        DBBase.getDBBase().getDBSession().getGoodsStockDao().deleteAll();
    }
}