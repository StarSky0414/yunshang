package com.tts.starsky.phonesweepcode.db;

import android.content.Context;

import com.tts.starsky.phonesweepcode.db.dao.DaoMaster;
import com.tts.starsky.phonesweepcode.db.dao.DaoSession;
import com.tts.starsky.phonesweepcode.db.dbUpdate.MyOpenHelper;

import org.greenrobot.greendao.database.Database;

public class DBBase {

    private static Context context;
    //数据库名
    private String DBNAME="my.db";
    //DBBase对象
    private static DBBase dbBase;

    private DBBase() {
    }

    /**
     * 初始化数据库
     * @param context 获取context
     */
    public static void dbBaseinit(Context context){
        DBBase.context = context;
//        QueryBuilder.LOG_SQL = true;
//        QueryBuilder.LOG_VALUES = true;
        do {
            dbBase = new DBBase();
        }while (dbBase ==null);
//        MigrationHelper.DEBUG = true;//如果查看数据库更新的Log，请设置为true

    }

    /**
     * 获取DBBase对象
     * @return DBBase对象
     */
    public static DBBase getDBBase() throws RuntimeException {
        if (dbBase==null){
            throw new RuntimeException("数据库未初始化");
        }
        return dbBase;
    }

    /**
     * 获取数据库session
     * @return 数据库session
     */
    public DaoSession getDBSession(){
        //获取DevOpenHelper   它继承 SQLiteOpenHelper
        MyOpenHelper myOpenHelper = new MyOpenHelper(context, DBNAME);
        Database db = myOpenHelper.getWritableDb();
//        SQLiteDatabase writableDatabase = myOpenHelper.getWritableDatabase()
//        Database db = (Database) writableDatabase;

        //获取  数据库session
        DaoSession daoSession = new DaoMaster(db).newSession();
        return daoSession;
    }
}
