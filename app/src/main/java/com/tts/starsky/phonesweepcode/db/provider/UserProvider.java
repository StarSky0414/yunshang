package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
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


}
