package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.DBBase;
import com.tts.starsky.phonesweepcode.db.dao.DaoSession;

/**
 *  DBProvider 抽象类 用于创建DBSession
 */
public class DBProviderBase {

    protected final DaoSession dbSession;

    DBProviderBase(){
        DBBase dbBase = DBBase.getDBBase();
        dbSession = dbBase.getDBSession();
    }
}
