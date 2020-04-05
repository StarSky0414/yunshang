package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.SonUserInfo;
import com.tts.starsky.phonesweepcode.db.dao.SonUserInfoDao;

import java.util.List;

/**
 * 子用户服务
 */
public class SonUserProvider extends DBProviderBase {

    /**
     * 创建子用户
     * @return
     */
    public long insert(SonUserInfo sonUserInfo) {
        SonUserInfoDao sonUserInfoDao = dbSession.getSonUserInfoDao();
        long l = sonUserInfoDao.insertOrReplace(sonUserInfo);
        dbSession.clear();
        return l;
    }

    /**
     * 查询所有用户子信息
     *
     * @return
     */
    public List<SonUserInfo> queryAllSonUserInfo(long fatherUserId) {
        SonUserInfoDao sonUserInfoDao = dbSession.getSonUserInfoDao();
        List<SonUserInfo> list = sonUserInfoDao.queryBuilder().where(SonUserInfoDao.Properties.Account.eq(String.valueOf(fatherUserId))).list();
        dbSession.clear();
        return list;
    }

    /**
     * 查询单个用户子信息
     *
     * @return
     */
    public SonUserInfo queryOneSonUserInfo(long sonUserId) {
        SonUserInfoDao sonUserInfoDao = dbSession.getSonUserInfoDao();
        SonUserInfo unique = sonUserInfoDao.queryBuilder().where(SonUserInfoDao.Properties._id.eq(sonUserId)).unique();
        dbSession.clear();
        return unique;
    }

    /**
     * 更新子用户
     * @return
     */
    public long update(SonUserInfo sonUserInfo) {
        long insert = insert(sonUserInfo);
        return insert;
    }

    public void deleUser(long sonUserId){
        SonUserInfo sonUserInfo = queryOneSonUserInfo(sonUserId);
        deleUser(sonUserInfo);
    }

    public void deleUser(SonUserInfo sonUserInfo){
        SonUserInfoDao sonUserInfoDao = dbSession.getSonUserInfoDao();
        sonUserInfoDao.delete(sonUserInfo);
        dbSession.clear();
    }

}
