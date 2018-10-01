package com.atguigu.l09_resolver;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 测试使用ContentResolver调用ContentProvider的主界面
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通过ContentResolver调用ContentProvider插入一条记录
     *
     * @param v
     */
    public void insert(View v) {

        ContentResolver resolver = this.getContentResolver();

        Uri uri = Uri.parse("content://com.atguigu.l09_provide.personprovider/person/");

        ContentValues values = new ContentValues();
        values.put("name", "小王");
        Uri newUri = resolver.insert(uri, values);

        Log.e("TAG", newUri.toString());
    }

    /**
     * 通过ContentResolver调用ContentProvider删除一条记录
     *
     * @param v
     */
    public void delete(View v) {
        //1.获取ContentResolver的对象
        ContentResolver resolver = this.getContentResolver();

        Uri uri = Uri.parse("content://com.atguigu.l09_provide.personprovider/person/1");

        int deleteCount = resolver.delete(uri, null, null);
        Log.e("TAG", "删除了" + deleteCount + "条数据");
    }

    /**
     * 通过ContentResolver调用ContentProvider修改一条记录
     *
     * @param v
     */
    public void update(View v) {
        //1.获取ContentResolver的对象
        ContentResolver resolver = this.getContentResolver();
        Uri uri = Uri.parse("content://com.atguigu.l09_provide.personprovider/person/2");

        ContentValues values = new ContentValues();
        values.put("name", "小花");

        int updateCount = resolver.update(uri, values, null, null);
        Log.e("TAG", "修改了" + updateCount + "条数据");
    }

    /**
     * 通过ContentResolver调用ContentProvider查询所有记录
     *
     * @param v
     */
    public void query(View v) {

        //1.获取ContentResolver的对象
        ContentResolver resolver = this.getContentResolver();
        //2.提供查询需要的参数
        Uri uri = Uri.parse("content://com.atguigu.l09_provide.personprovider/person");

        //3.进行查询:调用ContentResolver的query(),实际上就会触发对ContentProvider的query()方法的调用
        //ContentProvider的query()方法的返回值即为如下的ContentResolver的query()的返回值
        Cursor cursor = resolver.query(uri, null, null, null, null);
        //4.从Cursor中读取数据
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Log.e("TAG", "id = " + id + ",name = " + name);
        }

        //5.关闭资源
        cursor.close();
    }
}
