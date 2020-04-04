package com.tts.starsky.phonesweepcode.controller;


import com.tts.starsky.phonesweepcode.db.bean.UserInfo;
import com.tts.starsky.phonesweepcode.db.provider.UserService;
import com.tts.starsky.phonesweepcode.utile.SharedPreferencesUtil;

/**
 * 用户控制层
 */
public class UserController {

    private final UserService userService;
    private final static String adminName = "admin";
    private final static String adminPwd = "admin";

    public UserController() {
        userService = new UserService();
    }

    /**
     * 判断用户登录
     * 成功返回 true
     * 失败返回 false
     */
    public boolean checkUserAccess(String userName, String passWord) {
        UserInfo userInfo = userService.queryUserInfoByName(userName);
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
        userService.insertOrChangeUser(userInfo);
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
        sharedPreferencesUtil.setUserName(userName);
    }

    /**
     * 修改电话和地址
     */
    public void setUserPhoneAndAdress(String userName, String phone, String address) {
        UserInfo userInfo = userService.queryUserInfoByName(userName);
        userInfo.setAddress(address);
        userInfo.setPhone(phone);
        userService.insertOrChangeUser(userInfo);
    }

    /**
     * 设定用户头像
     */
    public void setUserPhoto(String userName, String userPhoto) {
        UserInfo userInfo = userService.queryUserInfoByName(userName);
        userInfo.setUserPhoto(userPhoto);
        userService.insertOrChangeUser(userInfo);
    }

    /**
     *  获取用户信息
     */
    public static UserInfo getUserInfo(){
        Long userId = getUserId();
        UserService userService = new UserService();
        UserInfo userInfo = userService.queryUserInfoById(userId);
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
