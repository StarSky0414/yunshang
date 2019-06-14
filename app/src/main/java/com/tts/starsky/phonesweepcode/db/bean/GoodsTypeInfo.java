package com.tts.starsky.phonesweepcode.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

@Entity
public class GoodsTypeInfo implements Serializable {

    @Transient
    static final long serialVersionUID = 42L;

    // 当前分类id
    @Id(autoincrement = true)
    private Long type_concrete_id;
    // 当前分类父类id
    private Long type_father_id;
    // 当前分类名称
    private String type_concrete_name;
    // 当前分类图片
    private String type_concrete_image;
    // 分类等级【2，3】【1级为目录系统生成】
    private int rank = 0;
    @Generated(hash = 1752071764)
    public GoodsTypeInfo(Long type_concrete_id, Long type_father_id,
            String type_concrete_name, String type_concrete_image, int rank) {
        this.type_concrete_id = type_concrete_id;
        this.type_father_id = type_father_id;
        this.type_concrete_name = type_concrete_name;
        this.type_concrete_image = type_concrete_image;
        this.rank = rank;
    }
    @Generated(hash = 1654544899)
    public GoodsTypeInfo() {
    }
    public Long getType_concrete_id() {
        return this.type_concrete_id;
    }
    public void setType_concrete_id(Long type_concrete_id) {
        this.type_concrete_id = type_concrete_id;
    }
    public Long getType_father_id() {
        return this.type_father_id;
    }
    public void setType_father_id(Long type_father_id) {
        this.type_father_id = type_father_id;
    }
    public String getType_concrete_name() {
        return this.type_concrete_name;
    }
    public void setType_concrete_name(String type_concrete_name) {
        this.type_concrete_name = type_concrete_name;
    }
    public String getType_concrete_image() {
        return this.type_concrete_image;
    }
    public void setType_concrete_image(String type_concrete_image) {
        this.type_concrete_image = type_concrete_image;
    }
    public int getRank() {
        return this.rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    
}
