package com.tts.starsky.phonesweepcode.db.provider;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.tts.starsky.phonesweepcode.db.DBBase;

public class DBInitTest {

    public DBInitTest(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        DBBase.dbBaseinit(appContext);
    }
}
