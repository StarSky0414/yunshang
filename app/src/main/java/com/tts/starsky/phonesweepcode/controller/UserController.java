package com.tts.starsky.phonesweepcode.controller;


import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
import com.tts.starsky.phonesweepcode.db.provider.UserProvider;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;

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
        System.out.println("===========自动登录校验 userName "+userName);
        return userName!=null&&!userName.isEmpty();
    }

    /**
     * 自动登录校验  管理员
     * 成功返回 true
     * 失败返回 false
     */
    public boolean autoCheckUserAccessAdmin() {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        String userName = sharedPreferencesUtil.getUserName();
        System.out.println("===========自动登录校验 userName "+userName);
        return userName!=null&&!userName.isEmpty()&&userName.equals(adminName);
    }

    /**
     * 注册账户
     * 修改密码
     */
    public void setUserNameAndPassWord(String userName, String passWord) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassWord(passWord);
        userProvider.insertOrChangeUser(userInfo);
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setUserName(userName);
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
     *  获取用户信息
     */
    public static UserInfo getUserInfo(){
        Long userId = getUserId();
        UserProvider userProvider = new UserProvider();
        UserInfo userInfo = userProvider.queryUserInfoById(userId);
        return userInfo;
    }

    /**
     *  获取用户Id
     */
    public static Long getUserId(){
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        String userId = sharedPreferencesUtil.getUserId();
        Long aLong = Long.valueOf(userId);
        return aLong;
    }

    /**
     *  是否是管理员
     */
    public static boolean isAdmin(String userName, String passWord){
        if(adminName.equals(userName) && adminPwd.equals(passWord)) {
            SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
            sharedPreferencesUtil.setUserName(userName);
            sharedPreferencesUtil.setUserId(userName);
            return true;
        }
        return false;
    }

    /**
     *  清除用户信息
     */
    public void clearUserId(){
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setUserId("");
        sharedPreferencesUtil.setUserName("");
    }
}
