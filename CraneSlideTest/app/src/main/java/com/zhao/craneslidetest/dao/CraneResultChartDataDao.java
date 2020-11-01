package com.zhao.craneslidetest.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.zhao.craneslidetest.beans.CraneResultChartData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CRANE_RESULT_CHART_DATA".
*/
public class CraneResultChartDataDao extends AbstractDao<CraneResultChartData, Long> {

    public static final String TABLENAME = "CRANE_RESULT_CHART_DATA";

    /**
     * Properties of entity CraneResultChartData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property TimeStamp = new Property(1, long.class, "timeStamp", false, "TIME_STAMP");
        public final static Property CraneType = new Property(2, String.class, "craneType", false, "CRANE_TYPE");
        public final static Property CraneNum = new Property(3, String.class, "craneNum", false, "CRANE_NUM");
        public final static Property ReadSpeedLeft = new Property(4, String.class, "readSpeedLeft", false, "READ_SPEED_LEFT");
        public final static Property SlipDistanceRight = new Property(5, String.class, "slipDistanceRight", false, "SLIP_DISTANCE_RIGHT");
    }


    public CraneResultChartDataDao(DaoConfig config) {
        super(config);
    }
    
    public CraneResultChartDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CRANE_RESULT_CHART_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"TIME_STAMP\" INTEGER NOT NULL ," + // 1: timeStamp
                "\"CRANE_TYPE\" TEXT," + // 2: craneType
                "\"CRANE_NUM\" TEXT," + // 3: craneNum
                "\"READ_SPEED_LEFT\" TEXT," + // 4: readSpeedLeft
                "\"SLIP_DISTANCE_RIGHT\" TEXT);"); // 5: slipDistanceRight
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CRANE_RESULT_CHART_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CraneResultChartData entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getTimeStamp());
 
        String craneType = entity.getCraneType();
        if (craneType != null) {
            stmt.bindString(3, craneType);
        }
 
        String craneNum = entity.getCraneNum();
        if (craneNum != null) {
            stmt.bindString(4, craneNum);
        }
 
        String readSpeedLeft = entity.getReadSpeedLeft();
        if (readSpeedLeft != null) {
            stmt.bindString(5, readSpeedLeft);
        }
 
        String slipDistanceRight = entity.getSlipDistanceRight();
        if (slipDistanceRight != null) {
            stmt.bindString(6, slipDistanceRight);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CraneResultChartData entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getTimeStamp());
 
        String craneType = entity.getCraneType();
        if (craneType != null) {
            stmt.bindString(3, craneType);
        }
 
        String craneNum = entity.getCraneNum();
        if (craneNum != null) {
            stmt.bindString(4, craneNum);
        }
 
        String readSpeedLeft = entity.getReadSpeedLeft();
        if (readSpeedLeft != null) {
            stmt.bindString(5, readSpeedLeft);
        }
 
        String slipDistanceRight = entity.getSlipDistanceRight();
        if (slipDistanceRight != null) {
            stmt.bindString(6, slipDistanceRight);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public CraneResultChartData readEntity(Cursor cursor, int offset) {
        CraneResultChartData entity = new CraneResultChartData( //
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // timeStamp
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // craneType
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // craneNum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // readSpeedLeft
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // slipDistanceRight
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CraneResultChartData entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setTimeStamp(cursor.getLong(offset + 1));
        entity.setCraneType(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCraneNum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setReadSpeedLeft(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSlipDistanceRight(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CraneResultChartData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CraneResultChartData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CraneResultChartData entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
