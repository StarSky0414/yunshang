//package com.tts.starsky.phonesweepcode.controller;
//
//import com.tts.starsky.phonesweepcode.db.bean.SonUserInfo;
//import com.tts.starsky.phonesweepcode.db.provider.SonUserProvider;
//
//import java.util.List;
//
///**
// * 子用户控制层
// */
//public class SonUserInfoController {
//
//    private SonUserProvider sonUserProvider = new SonUserProvider();
//
//    // 启用子用户
//    public static final String SON_SIGN_OPEN = "1";
//
//    // 关闭子用户
//    public static final String SON_SIGN_OFF = "0";
//
//    /**
//     * 修改子用户信息状态
//     * 1-》0
//     * 0-》1
//     * @return  返回 1 更新成功
//     */
//    public long changeSonUserSign(long sonUserId) {
//        SonUserInfo sonUserInfo = sonUserProvider.queryOneSonUserInfo(sonUserId);
//        String sign = sonUserInfo.getSign();
//        sign = String.valueOf(Integer.parseInt(sign) == 0 ? 1 : 0);
//        sonUserInfo.setSign(sign);
//        long update = sonUserProvider.update(sonUserInfo);
//        return update;
//    }
//
//    /**
//     * 创建子用户
//     * @return 为 0 创建失败 ，为1 创建成功
//     */
//    public long makeOrUpdateSonUser(SonUserInfo sonUserInfo){
//        SonUserInfo querySonUserInfo = sonUserProvider.queryOneSonUserInfo(sonUserInfo.get_id());
//        if (querySonUserInfo != null || querySonUserInfo.get_id() >0){
//            return 0;
//        }
//        long insert = sonUserProvider.insert(sonUserInfo);
//        return insert;
//    }
//
//    /**
//     *  查询所有子用户信息
//     * @return
//     */
//    public List<SonUserInfo> queryAllSonUserInfo(long fatherUserId){
//        List<SonUserInfo> sonUserInfos = sonUserProvider.queryAllSonUserInfo(fatherUserId);
//        return sonUserInfos;
//    }
//
//}
