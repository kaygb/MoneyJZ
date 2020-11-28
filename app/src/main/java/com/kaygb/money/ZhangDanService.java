package com.kaygb.money;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ZhangDanService {
    private ZDdbHelper zDdbHelper;
    public ZhangDanService(Context context){
        zDdbHelper = new ZDdbHelper(context);
    }

    public boolean addZd(ZhangDan zd){
        SQLiteDatabase sqLiteDatabase = zDdbHelper.getReadableDatabase();
        String sql = "insert into zhangdan (zdname,zddate,zdlx,zdje,zdbz) values (?,?,?,?,?)";
        Object obj[] = {zd.getZDname(),zd.getZddate(),zd.getZdlx(),zd.getZdje(),zd.getZdbz() };
        sqLiteDatabase.execSQL(sql, obj);
        return true;
    }

}
