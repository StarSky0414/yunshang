package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
//import com.tts.starsky.phonesweepcode.db.dao.SonUserInfoDao;
import com.tts.starsky.phonesweepcode.db.dao.UserInfoDao;

import java.util.List;

/**
 * 用户的数据请求层
 */

public class UserProvider extends DBProviderBase {

    /**
     *  插入或修改用户信息
     */
    public void insertOrChangeUser(UserInfo userInfo){
        dbSession.getUserInfoDao().insertOrReplace(userInfo);
        dbSession.clear();
    }

    /**
     *  查询所有用户信息
     */
    public List<UserInfo> queryAllUserInfo(){
        List<UserInfo> list = dbSession.getUserInfoDao().queryBuilder().list();
        dbSession.clear();
        return list;
    }

    /**
     *  根据用户Id查询用户信息
     */
    public UserInfo queryUserInfoById(Long userId){
        UserInfo unique = dbSession.getUserInfoDao().queryBuilder().where(UserInfoDao.Properties._id.eq(userId)).unique();
        dbSession.clear();
        return unique;
    }

    /**
     *  根据用户userName查询用户信息
     */
    public UserInfo queryUserInfoByName(String userName){
        UserInfo unique = dbSession.getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.UserName.eq(userName)).unique();
        dbSession.clear();
        return unique;
    }


    /**
     *  根据用户Id查询子用户信息
     */
    public UserInfo querySonUserInfoById(Long sonUserId,Long fatherUserId){
        UserInfo userInfo = dbSession.getUserInfoDao().queryBuilder().
                where(UserInfoDao.Properties.Account.eq(fatherUserId)).
                where(UserInfoDao.Properties.Account.eq(sonUserId)).
                unique();
        dbSession.clear();
        return userInfo;
    }



    /**
     * 创建子用户
     * @return
     */
    public long insert(UserInfo sonUserInfo) {
        UserInfoDao sonUserInfoDao = dbSession.getUserInfoDao();
        long l = sonUserInfoDao.insertOrReplace(sonUserInfo);
        dbSession.clear();
        return l;
    }

    /**
     * 查询所有用户子信息
     *
     * @return
     */
    public List<UserInfo> queryAllSonUserInfo(long fatherUserId) {
        UserInfoDao sonUserInfoDao = dbSession.getUserInfoDao();
        List<UserInfo> list = sonUserInfoDao.queryBuilder()
                .where(UserInfoDao.Properties.Account.eq(String.valueOf(fatherUserId))).list();
        dbSession.clear();
        return list;
    }

    /**
     * 查询单个用户子信息
     *
     * @return
     */
    public UserInfo queryOneSonUserInfo(long sonUserId,long fatherUserId) {
        UserInfoDao sonUserInfoDao = dbSession.getUserInfoDao();
        UserInfo unique = sonUserInfoDao.queryBuilder()
                .where(UserInfoDao.Properties._id.eq(sonUserId))
                .where(UserInfoDao.Properties.Account.eq(fatherUserId))
                .unique();
        dbSession.clear();
        return unique;
    }

    /**
     * 更新子用户
     * @return
     */
    public long update(UserInfo sonUserInfo) {
        long insert = insert(sonUserInfo);
        return insert;
    }

    public void deleUser(long sonUserId){
        UserInfoDao sonUserInfoDao = dbSession.getUserInfoDao();
        UserInfo sonUserInfo = sonUserInfoDao.queryBuilder().where(UserInfoDao.Properties._id.eq(sonUserId)).unique();
//        UserInfo sonUserInfo = queryOneSonUserInfo(sonUserId);
        deleUser(sonUserInfo);
    }

    public void deleUser(UserInfo sonUserInfo){
        UserInfoDao sonUserInfoDao = dbSession.getUserInfoDao();
        sonUserInfoDao.delete(sonUserInfo);
        dbSession.clear();
    }


}
