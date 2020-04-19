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
    private static String USER_NAME = "USER_NAME";
    private static String USER_ID = "USER_ID";
    private static String IMG = "IMG";
    private static String IS_ADMIN = "IS_ADMIN";  // 0不是 、 1是
    private static String FATHER_ID = "FATHER_ID";


    private static String getBase(String key) {
        sp = context.getSharedPreferences("setting", MODE_PRIVATE);
        return sp.getString(key, "0");
    }


    private static void setBase(String key, String inputValue) {
        SharedPreferences setting = context.getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor edit = setting.edit();
        edit.putString(key, inputValue);
        edit.commit();
    }

    /**
     * 获取本地自动备份使用的时间戳
     *
     * @return 本地自动备份使用的时间戳
     */
    public Long getBackupSqlDate() {
        String base = getBase(BACKUP_SQL);
        Long aLong = Long.valueOf(base);
        return aLong;
    }

    /**
     * 设置本地自动备份使用的时间戳
     */
    public void setBackupSqlDate() {
        long currentTimeMillis = System.currentTimeMillis();
        setBase(BACKUP_SQL, String.valueOf(currentTimeMillis));
    }

    /**
     * 获取用户名字
     */
    public String getUserName() {
        String userName = getBase(USER_NAME);
        return userName;
    }

    /**
     * 设定名字
     * 条件 【登录成功】
     */
    public void setUserName(String userName) {
        setBase(USER_NAME, String.valueOf(userName));
    }

    /**
     * 读取用户ID
     */
    public String getUserId() {
        String userId = getBase(USER_ID);
        return userId;
    }

    /**
     * 设定用户Id
     */
    public void setUserId(String userId) {
        setBase(USER_ID, String.valueOf(userId));
    }

    /**
     * 是否是管理员
     */
    public String getAdminSign() {
        String adminSign = getBase(IS_ADMIN);
        return adminSign;
    }

    /**
     * 设定用户Id
     */
    public void setAdminSign(String userId) {
        setBase(IS_ADMIN, String.valueOf(userId));
    }

    /**
     * 是否是管理员
     */
    public String getFatherId() {
        String adminSign = getBase(FATHER_ID);
        return adminSign;
    }

    /**
     * 设定用户Id
     */
    public void setFatherId(String userId) {
        setBase(FATHER_ID, String.valueOf(userId));
    }

    public static void setImage(String uri) {
        setBase(IMG, uri);
    }

    public static String getImage() {
        String base = getBase(IMG);
        return base;
    }


}
