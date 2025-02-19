package com.tts.starsky.phonesweepcode.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tts.starsky.phonesweepcode.db.bean.UserInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFO".
*/
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property UserPhoto = new Property(1, String.class, "userPhoto", false, "USER_PHOTO");
        public final static Property Account = new Property(2, String.class, "account", false, "ACCOUNT");
        public final static Property Address = new Property(3, String.class, "address", false, "ADDRESS");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property UserName = new Property(5, String.class, "userName", false, "USER_NAME");
        public final static Property PassWord = new Property(6, String.class, "passWord", false, "PASS_WORD");
        public final static Property Descri = new Property(7, String.class, "descri", false, "DESCRI");
        public final static Property WiFiName = new Property(8, String.class, "WiFiName", false, "WI_FI_NAME");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }
    
    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"USER_PHOTO\" TEXT," + // 1: userPhoto
                "\"ACCOUNT\" TEXT," + // 2: account
                "\"ADDRESS\" TEXT," + // 3: address
                "\"PHONE\" TEXT," + // 4: phone
                "\"USER_NAME\" TEXT," + // 5: userName
                "\"PASS_WORD\" TEXT," + // 6: passWord
                "\"DESCRI\" TEXT," + // 7: descri
                "\"WI_FI_NAME\" TEXT);"); // 8: WiFiName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String userPhoto = entity.getUserPhoto();
        if (userPhoto != null) {
            stmt.bindString(2, userPhoto);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(3, account);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(6, userName);
        }
 
        String passWord = entity.getPassWord();
        if (passWord != null) {
            stmt.bindString(7, passWord);
        }
 
        String descri = entity.getDescri();
        if (descri != null) {
            stmt.bindString(8, descri);
        }
 
        String WiFiName = entity.getWiFiName();
        if (WiFiName != null) {
            stmt.bindString(9, WiFiName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String userPhoto = entity.getUserPhoto();
        if (userPhoto != null) {
            stmt.bindString(2, userPhoto);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(3, account);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(6, userName);
        }
 
        String passWord = entity.getPassWord();
        if (passWord != null) {
            stmt.bindString(7, passWord);
        }
 
        String descri = entity.getDescri();
        if (descri != null) {
            stmt.bindString(8, descri);
        }
 
        String WiFiName = entity.getWiFiName();
        if (WiFiName != null) {
            stmt.bindString(9, WiFiName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userPhoto
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // account
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // address
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // passWord
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // descri
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // WiFiName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserPhoto(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAccount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPassWord(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDescri(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setWiFiName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInfo entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
