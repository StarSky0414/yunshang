package com.tts.starsky.phonesweepcode.utile;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


public class SharedPreferencesUtil {

    private static Context context;

    public static void init(Context context) {
        SharedPreferencesUtil.context = context;
    }

    //声明SharedPreferences，用来读取xml;
    private static SharedPreferences sp;
    //声明SharedPreferences.Editor，用来修改xml里面的值。
    private static SharedPreferences.Editor ed;

    private static String BACKUP_SQL = "BACKUP_SQL";


    private static String getBase(String key) {
        sp = context.getSharedPreferences("setting", MODE_PRIVATE);
        return sp.getString(key,"");
    }


    private static void setBase(String key, String inputValue) {
        SharedPreferences setting = context.getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor edit = setting.edit();
        edit.putString(key, inputValue);
        edit.commit();
    }

    /**
     *  获取本地自动备份使用的时间戳
     * @return  本地自动备份使用的时间戳
     */
    public Long getBackupSqlDate(){
        String base = getBase(BACKUP_SQL);
        Long aLong = Long.valueOf(base);
        return aLong;
    }

    /**
     *  设置本地自动备份使用的时间戳
     */
    public void setBackupSqlDate(){
        long currentTimeMillis = System.currentTimeMillis();
        setBase(BACKUP_SQL,String.valueOf(currentTimeMillis));
    }




}
