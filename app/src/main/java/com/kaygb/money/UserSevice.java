package com.kaygb.money;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserSevice {
    private DbHelper dbHelper;

    public UserSevice(Context context) {
        dbHelper = new DbHelper(context);
    }


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public boolean Login(String username, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "select * from user where username = ? and password = ? ";
        Cursor rawQuery = sqLiteDatabase.rawQuery(sql, new String[] { username,
                password });
        if (rawQuery.moveToFirst() == true) {
            rawQuery.close();
            return true;
        }
        return false;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */

    public boolean Register(User user) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "insert into user (username,password) values (?,?)";
        Object obj[] = { user.getUsername(), user.getPassword() };
        sqLiteDatabase.execSQL(sql, obj);
        return true;
    }

}
