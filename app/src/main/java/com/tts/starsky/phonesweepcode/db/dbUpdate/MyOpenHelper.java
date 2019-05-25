package com.tts.starsky.phonesweepcode.db.dbUpdate;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tts.starsky.phonesweepcode.db.dao.DaoMaster;
import com.tts.starsky.phonesweepcode.db.dao.DiscountDao;
import com.tts.starsky.phonesweepcode.db.dao.GoodsInfoDao;
import com.tts.starsky.phonesweepcode.db.dao.GoodsStockDao;
import com.tts.starsky.phonesweepcode.db.dao.SalesDao;
import com.tts.starsky.phonesweepcode.db.dao.SalesToGoodsDao;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * 数据库更新时候保留原数据使用
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    Class<? extends AbstractDao<?, ?>>[] classes = new Class[]{
            DiscountDao.class,
            GoodsInfoDao.class,
            GoodsStockDao.class,
            SalesDao.class,
            SalesToGoodsDao.class,
    };


    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        //把需要管理的数据库表DAO作为最后一个参数传入到方法中
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, classes);
    }

}