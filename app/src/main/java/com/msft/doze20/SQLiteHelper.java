package com.msft.doze20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;

public class SQLiteHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "test.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "activities";
    public static final String ID = "_id";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_TYPE = "activity_id";

    private static final String DATABASE_CREATE = "CREATE TABLE " +  TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY, " + COLUMN_DATA + " TEXT, " + COLUMN_TYPE + " INTEGER, " + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
