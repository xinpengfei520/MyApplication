package com.atguigu.l08_app_endcallsms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackNumberDAO类进行数据库的CRUD操作 DAO:data(base) access object 数据(库)访问对象
 */
public class BlackNumberDAO {

    private static final String TABLE_NAME = "blacknumber";
    private DBHelper dbHelper;

    /**
     * 构造器用于创建一个黑名单表
     *
     * @param context
     */
    public BlackNumberDAO(Context context) {
        dbHelper = new DBHelper(context, 1);
    }

    /**
     * 添加号码到数据库
     *
     * @param blackNumber
     */
    public void add(BlackNumber blackNumber) {
        // 获取数据库的连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        values.put("name",blackNumber.getName());
        long newID = database.insert(BlackNumberDAO.TABLE_NAME, null, values);
        Log.e("TAG","新加入号码的id:"+newID );
        database.close();
    }

    /**
     * 通过id删除指定的号码
     *
     * @param id
     */
    public void delete(int id) {
        // 获取数据库的连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.delete(TABLE_NAME, "_id= ?", new String[] { id + "" });
        database.close();
    }

    /**
     * 修改指定的号码
     *
     * @param blackNumber
     */
    public void update(BlackNumber blackNumber) {
        // 获取数据库的连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", blackNumber.getNumber());
        database.update(TABLE_NAME, values, "_id = ?",
                new String[] { blackNumber.getId() + "" });
        database.close();
    }

    /**
     * 返回所有黑名单号码
     *
     * @return
     */
    public List<BlackNumber> getAll() {


        List<BlackNumber> list = new ArrayList<BlackNumber>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(TABLE_NAME, null, null, null, null,
                null, "_id desc");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            BlackNumber blackNumber = new BlackNumber(id, name,number);
            list.add(blackNumber);
        }
        cursor.close();
        database.close();

        return list;
    }
}
