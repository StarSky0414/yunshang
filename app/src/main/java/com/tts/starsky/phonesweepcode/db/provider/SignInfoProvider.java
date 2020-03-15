package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.SignInfo;
import com.tts.starsky.phonesweepcode.db.dao.SignInfoDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  签到信息数据库提供者
 */
public class SignInfoProvider extends DBProviderBase {

    /**
     *  签到信息
     * @param signInfo 要插入的商品对象
     */
    public void setSignInfo(SignInfo signInfo){
        dbSession.getSignInfoDao().insertOrReplace(signInfo);
    }

    /**
     *  查询当天时间签到时间
     * @param userName 商品编码号
     * @return
     */
    public List<SignInfo> signInfosList(String userName) {
        Long timeStamp = System.currentTimeMillis();

        // 获取当前日期
        Date date = new Date(timeStamp);
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy年MM月dd");
        String Date = dateFormat.format(date);


        List<SignInfo> unique = dbSession.getSignInfoDao().queryBuilder().where(
                SignInfoDao.Properties.UserName.eq(userName),
                SignInfoDao.Properties.StartTime.like(Date+"%")  // 模糊查询当前日期
                ).list();
        return unique;
    }

}
