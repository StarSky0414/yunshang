package com.tts.starsky.phonesweepcode.db;

import android.content.Context;
import com.tts.starsky.phonesweepcode.oss.UpFile;
import com.tts.starsky.phonesweepcode.utile.OkHttpUtil;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;
import java.text.SimpleDateFormat;

/**
 * 数据库备份使用
 */
public class BackUp {

    private final Context context;

    public BackUp(Context context) {
        this.context = context;
    }

    public void appInitBackUpCheck() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        // 1558860734
        Long backupSqlDate = sharedPreferencesUtil.getBackupSqlDate();
        long timeMillis = System.currentTimeMillis();
        long backupSqlDay = timeMilliToDay(backupSqlDate);
        long timeMillisDay = timeMilliToDay(timeMillis);

        if (backupSqlDay == 0 || backupSqlDay < timeMillisDay) {
            String absolutePath = context.getApplicationContext().getDatabasePath("my.db").getAbsolutePath();
            UpFile upFile = new UpFile();
            upFile.upfile(absolutePath, "yunshang/backup/" + timeMillisDay);
            System.out.println("===========" + absolutePath);
            sharedPreferencesUtil.setBackupSqlDate();
        }
    }

    /**
     * 将时间戳转换为日期时间
     */
    private static long timeMilliToDay(long timeMillis) {
        String date = timeStamp2Date(timeMillis);
        long dayNum = Long.valueOf(date);
        return dayNum;
    }

    private static String timeStamp2Date(long timeMillis) {
        String format = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(timeMillis);
    }

    /**
     * 数据恢复暂未启用
     *
     * @param childUrl 文件名
     */
    public void recoverDB(String childUrl) {
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        String absolutePath = context.getApplicationContext().getDatabasePath("my.db").getAbsolutePath();
        String urlRoot = "https://thethreestooges.oss-cn-shenzhen.aliyuncs.com/yunshang/backup/";
        okHttpUtil.downFile(urlRoot + childUrl, absolutePath);
    }

}
