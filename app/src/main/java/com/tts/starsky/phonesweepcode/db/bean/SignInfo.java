package com.tts.starsky.phonesweepcode.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  店员签到信息
 */

@Entity
public class SignInfo {

    @Id
    private Long _id;

    // 签到时间
    private String startTime ;

    // 签到人员
    private String userName;

    @Generated(hash = 849071653)
    public SignInfo(Long _id, String startTime, String userName) {
        this._id = _id;
        this.startTime = startTime;
        this.userName = userName;
    }

    @Generated(hash = 1879720682)
    public SignInfo() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "SignInfo{" +
                "_id=" + _id +
                ", startTime='" + startTime + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
