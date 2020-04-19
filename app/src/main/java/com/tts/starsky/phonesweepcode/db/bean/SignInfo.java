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

    private Long userFatherId;

    private Long userSonId;

    @Generated(hash = 864625756)
    public SignInfo(Long _id, String startTime, String userName, Long userFatherId,
            Long userSonId) {
        this._id = _id;
        this.startTime = startTime;
        this.userName = userName;
        this.userFatherId = userFatherId;
        this.userSonId = userSonId;
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

    public Long getUserFatherId() {
        return this.userFatherId;
    }

    public void setUserFatherId(Long userFatherId) {
        this.userFatherId = userFatherId;
    }

    public Long getUserSonId() {
        return this.userSonId;
    }

    public void setUserSonId(Long userSonId) {
        this.userSonId = userSonId;
    }
}
