package com.tts.starsky.phonesweepcode.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tts.starsky.phonesweepcode.db.bean.SignInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SIGN_INFO".
*/
public class SignInfoDao extends AbstractDao<SignInfo, Long> {

    public static final String TABLENAME = "SIGN_INFO";

    /**
     * Properties of entity SignInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property StartTime = new Property(1, String.class, "startTime", false, "START_TIME");
        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
    }


    public SignInfoDao(DaoConfig config) {
        super(config);
    }
    
    public SignInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SIGN_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"START_TIME\" TEXT," + // 1: startTime
                "\"USER_NAME\" TEXT);"); // 2: userName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SIGN_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SignInfo entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(2, startTime);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SignInfo entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(2, startTime);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(3, userName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public SignInfo readEntity(Cursor cursor, int offset) {
        SignInfo entity = new SignInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // startTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // userName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SignInfo entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStartTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(SignInfo entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(SignInfo entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SignInfo entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
