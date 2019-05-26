package com.tts.starsky.phonesweepcode.db;

import android.content.Context;

import com.tts.starsky.phonesweepcode.oss.UpFile;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;

/**
 * 数据库备份使用
 */
public class BackUp {

    private final Context context;

    public BackUp(Context context){
        this.context = context;
    }

    public void appInitBackUpCheck() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        // 1558860734
        Long backupSqlDate = sharedPreferencesUtil.getBackupSqlDate();
        long timeMillis = System.currentTimeMillis();
        long backupSqlDay = timeMilliToDay(backupSqlDate);
        long timeMillisDay = timeMilliToDay(timeMillis);

        if (backupSqlDay ==0 || backupSqlDay < timeMillisDay) {
            String absolutePath = context.getApplicationContext().getDatabasePath("my.db").getAbsolutePath();
            UpFile upFile = new UpFile();
            upFile.upfile(absolutePath,"yunshang/backup/"+timeMillisDay);
            System.out.println("==========="+absolutePath);
            sharedPreferencesUtil.setBackupSqlDate();
        }
    }

    /**
     * 将时间戳转换为日期时间
     */
    private static long timeMilliToDay(long timeMillis) {
        long dayNum = timeMillis / 3600 / 24;
        return dayNum;
    }
}
