package com.tts.starsky.phonesweepcode.controller;


import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
import com.tts.starsky.phonesweepcode.db.provider.UserProvider;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;

import java.util.List;

/**
 * 用户控制层
 */
public class UserController {

    private final UserProvider userProvider;
    private final static String adminName = "admin";
    private final static String adminPwd = "admin";

    public UserController() {
        userProvider = new UserProvider();
    }

    /**
     * 判断用户登录
     * 成功返回 true
     * 失败返回 false
     */
    public boolean checkUserAccess(String userName, String passWord) {
        UserInfo userInfo = userProvider.queryUserInfoByName(userName);
        if (userInfo == null || !passWord.equals(userInfo.getPassWord())) {
            return false;
        } else {
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
            sharedPreferencesUtil.setUserName(userName);
            String userId = String.valueOf(userInfo.get_id());
            sharedPreferencesUtil.setUserId(userId);
            return true;
        }
    }

    /**
     * 自动登录校验 普通用户
     * 成功返回 true
     * 失败返回 false
     */
    public boolean autoCheckUserAccess() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        String userName = sharedPreferencesUtil.getUserName();
        System.out.println("===========自动登录校验 userName " + userName);
        return userName != null && !userName.isEmpty();
    }

    /**
     * 自动登录校验  管理员
     * 成功返回 true
     * 失败返回 false
     */
    public boolean autoCheckUserAccessAdmin() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        String userName = sharedPreferencesUtil.getUserName();
        System.out.println("===========自动登录校验 userName " + userName);
        return userName != null && !userName.isEmpty() && userName.equals(adminName);
    }

    /**
     * 注册账户
     * 修改密码
     * @return
     */
    public UserInfo setUserNameAndPassWord(String userName, String passWord) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassWord(passWord);
        userProvider.insertOrChangeUser(userInfo);
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setUserName(userName);
        sharedPreferencesUtil.setUserId(String.valueOf(userInfo.get_id()));
        userInfo.setAccount(String.valueOf(userInfo.get_id()));
        userProvider.insertOrChangeUser(userInfo);
        return userInfo;
    }

    /**
     * 修改电话和地址
     */
    public void setUserPhoneAndAdress(String userName, String phone, String address) {
        UserInfo userInfo = userProvider.queryUserInfoByName(userName);
        userInfo.setAddress(address);
        userInfo.setPhone(phone);
        userProvider.insertOrChangeUser(userInfo);
    }

    /**
     * 设定用户头像
     */
    public void setUserPhoto(String userName, String userPhoto) {
        UserInfo userInfo = userProvider.queryUserInfoByName(userName);
        userInfo.setUserPhoto(userPhoto);
        userProvider.insertOrChangeUser(userInfo);
    }

    /**
     * 获取用户信息
     */
    public static UserInfo getUserInfo() {
        Long userId = getUserId();
        UserProvider userProvider = new UserProvider();
        UserInfo userInfo = userProvider.queryUserInfoById(userId);
        return userInfo;
    }

    /**
     * 获取用户Id
     */
    public static Long getUserId() {
        String userId;
        try {
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
            userId = sharedPreferencesUtil.getUserId();
        } catch (Exception e) {
            userId = "0";
        }
        Long aLong = Long.valueOf(userId);
        return aLong;
    }

    /**
     * 是否是管理员
     */
    public static boolean isAdmin(String userName, String passWord) {
        if (adminName.equals(userName) && adminPwd.equals(passWord)) {
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
            sharedPreferencesUtil.setUserName(userName);
            sharedPreferencesUtil.setUserId(userName);
            return true;
        }
        return false;
    }

    /**
     * 是否是管理员
     */
    public static boolean isAdmin() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        String adminSign = sharedPreferencesUtil.getAdminSign();
//        if (adminSign.equals("0")){
//            userProvider.queryUserInfoByName()
//        }
        return !adminSign.equals("-1");
    }
/**
     * 是否是管理员
     */
    public static boolean isAdmin(UserInfo userInfo) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();

        String account = userInfo.getAccount();
        boolean equals = account.equals(String.valueOf(userInfo.get_id()));
        if (equals) {
            setAdminSign();
        }else {
            clearAdminSign();
        }
        return equals;
    }

    /**
     * 是否是管理员
     */
    public static void setAdminSign() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setAdminSign("1");
    }


    /**
     * 是否是管理员
     */
    public static void clearAdminSign() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setAdminSign("-1");
    }


    /**
     * 是否是管理员
     */
    public static void setFatherUserId(String fatherUserId) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setFatherId(fatherUserId);
    }


    /**
     * 是否是管理员
     * @return
     */
    public static String getFatherUserId() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        String fatherId = sharedPreferencesUtil.getFatherId();
        return fatherId;
    }



    /**
     * 清除用户信息
     */
    public void clearUserId() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setUserId("");
        sharedPreferencesUtil.setUserName("");
    }

    // 启用子用户
    public static final String SON_SIGN_OPEN = "1";

    // 关闭子用户
    public static final String SON_SIGN_OFF = "0";

    /**
     * 修改子用户信息状态
     * 1-》0
     * 0-》1
     * @return 返回 1 更新成功
     */
//    public long changeSonUserSign(long sonUserId) {
//        SonUserInfo sonUserInfo = sonUserProvider.queryOneSonUserInfo(sonUserId);
//        String sign = sonUserInfo.getSign();
//        sign = String.valueOf(Integer.parseInt(sign) == 0 ? 1 : 0);
//        sonUserInfo.setSign(sign);
//        long update = sonUserProvider.update(sonUserInfo);
//        return update;
//    }

    /**
     * 创建子用户
     *
     * @return 为 0 创建失败 ，为1 创建成功
     */
    public long makeOrUpdateSonUser(UserInfo sonUserInfo) {
//        UserInfo querySonUserInfo = userProvider.queryOneSonUserInfo(sonUserInfo.get_id());
//        if (querySonUserInfo != null || querySonUserInfo.get_id() >0){
//            return 0;
//        }
        long insert = userProvider.insert(sonUserInfo);
        return insert;
    }

    /**
     * 查询所有子用户信息
     *
     * @return
     */
    public List<UserInfo> queryAllSonUserInfo(long fatherUserId) {
        List<UserInfo> sonUserInfos = userProvider.queryAllSonUserInfo(fatherUserId);
        return sonUserInfos;
    }

    public void delUser(UserInfo userInfo) {
        userProvider.deleUser(userInfo);
    }

    public void change(UserInfo userInfo) {
        userProvider.insertOrChangeUser(userInfo);
    }

    public void setWifiInfo(String wifiName) {
        UserInfo userInfo = getUserInfo();
        userInfo.setWiFiName(wifiName);
        userProvider.insertOrChangeUser(userInfo);
    }

    public UserInfo queryUserInfo(String fatherUserInfo){
        UserInfo userInfo = userProvider.queryUserInfoById(Long.parseLong(fatherUserInfo));
        return userInfo;
    }
}
