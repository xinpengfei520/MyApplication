package com.atguigu.l09_provide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xinpengfei on 2016/9/13.
 */
public class DBHelper extends SQLiteOpenHelper {

    //构造器用于创建一个数据库的表
    public DBHelper(Context context, int version) {
        super(context, "atguigu.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement,name varchar)");
        db.execSQL("insert into person(name)values('张三')");
        db.execSQL("insert into person(name)values('李四')");
        db.execSQL("insert into person(name)values('王五')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
