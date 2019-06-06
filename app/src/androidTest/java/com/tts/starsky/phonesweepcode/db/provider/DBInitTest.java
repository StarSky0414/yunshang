package com.tts.starsky.phonesweepcode.db.provider;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.tts.starsky.phonesweepcode.db.DBBase;
import com.tts.starsky.phonesweepcode.db.dao.DaoSession;

public class DBInitTest {

    public final DaoSession dbSession;

    public DBInitTest(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBBase.dbBaseinit(appContext);
        DBBase dbBase = DBBase.getDBBase();
        dbSession = dbBase.getDBSession();
    }
}
