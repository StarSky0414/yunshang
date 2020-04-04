package com.tts.starsky.phonesweepcode.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *  用户信息
 */
@Entity
public class UserInfo {

    @Id(autoincrement = true)
    // 用户ID
    private Long _id;

    // 用户头像
    private String userPhoto;

    // 用户账户
    private String account;

    // 用户默认地址
    private String address;

    // 用户默认电话
    private String phone;

    // 用户账户
    private String userName;

    // 用户密码
    private String passWord;

    // 描述
    private String descri;

    @Generated(hash = 40649202)
    public UserInfo(Long _id, String userPhoto, String account, String address,
                    String phone, String userName, String passWord, String descri) {
        this._id = _id;
        this.userPhoto = userPhoto;
        this.account = account;
        this.address = address;
        this.phone = phone;
        this.userName = userName;
        this.passWord = passWord;
        this.descri = descri;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getUserPhoto() {
        return this.userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", userPhoto='" + userPhoto + '\'' +
                ", account='" + account + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getDescri() {
        return this.descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }
}
