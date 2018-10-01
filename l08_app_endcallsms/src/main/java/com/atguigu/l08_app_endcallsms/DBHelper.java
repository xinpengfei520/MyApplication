package com.atguigu.l08_app_endcallsms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DBHelper继承于SQLiteOpenHelper，使用SQLite数据库存储 重写2个方法
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, int version) {
        super(context, "blacknumber.db", null, version);
    }

    /**
     * 创建数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建一个表,双引号里面写的是sql语句
        db.execSQL("create table blacknumber(_id integer primary key autoincrement,name varchar,number varchar)");
//		db.execSQL("insert into blacknumber(number)values(110)");
//		db.execSQL("insert into blacknumber(number)values(120)");
    }

    /**
     * 如果有版本升级就调用此方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
