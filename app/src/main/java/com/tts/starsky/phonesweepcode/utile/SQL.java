package com.tts.starsky.phonesweepcode.utile;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  数据库使用工具类
 */
public class SQL {

    /**
     *  当前时间
     * @return 当前时间字符串  格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getThisTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());
        return format;
    }

    /**
     *  当前时间戳+用户id为主键
     *  目前为单机版本 用户id设定为00
     */
    public static String getTimePram(){
        long timeMillis = System.currentTimeMillis();
        String s = String.valueOf(timeMillis) + "00";
        return s;
    }
}
