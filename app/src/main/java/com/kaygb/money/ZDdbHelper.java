package com.kaygb.money;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ZDdbHelper extends SQLiteOpenHelper {
    private final static String NAME = "zd.db";
    private final static int VERSION = 1;
    public ZDdbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ZDdbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table zhangdan (zid integer primary key autoincrement,zdname varchar(255),zddate varchar(255),zdlx varchar(255),zdje REAL,zdbz varchar(255))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
