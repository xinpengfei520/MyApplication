package com.atguigu.l09_provide;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by xinpengfei on 2016/9/13.
 */
public class PersonProvider extends ContentProvider {

    //格式1：content://com.atguigu.l09_provide.personprovider/person/
    //格式2：content://com.atguigu.l09_provide.personprovider/person/4
    //提供一个“盛装”uri的容器
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        //参数1：指明authority  参数2：指明访问的路径  参数3：给当前的uri赋一个唯一的id
        matcher.addURI("com.atguigu.l09_provide.personprovider", "person/", 1);
        matcher.addURI("com.atguigu.l09_provide.personprovider", "person/#", 2);
    }

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext(), 1);
        return true;//表示正确的加载了
    }

    /**
     * 对外提供查询的操作
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //根据ContentResolver发送过来的uri，获取其id,判断是容器中的哪个uri
        int code = matcher.match(uri);
        //获取一个数据库的连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            if (code == 1) {

                cursor = database.query("person", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            } else if (code == 2) {
                //解析uri,得到其中的Id
                long id = ContentUris.parseId(uri);
                cursor = database.query("person", projection, "_id = ?", new String[]{id + ""}, null, null, sortOrder);
                return cursor;
            } else {
                throw new RuntimeException("输入的uri不合法");
            }
        } finally {

            //关闭操作
//            cursor.close();
//            database.close();
        }


    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * 对外提供插入的操作
     *
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        try {
            int code = matcher.match(uri);
            if (code == 1) {
                //返回添加的数据的id
                long newID = database.insert("person", null, values);

                //将发送过来的uri与添加成功的数据的id，匹配在一起返回
                Uri newUri = ContentUris.withAppendedId(uri, newID);
                return newUri;

            } else {
                throw new RuntimeException("输入的uri不合法");
            }
        } finally {

            //此处的判断也可以不加，加上更保险
//            if(database != null) {
            database.close();
//            }
        }


    }

    /**
     * 对外提供删除的操作
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        try {
            int code = matcher.match(uri);
            if (code == 1) {
                //返回删除的条数
                int deleteCount = database.delete("person", selection, selectionArgs);
                return deleteCount;
            } else if (code == 2) {
                long id = ContentUris.parseId(uri);
                int deleteCount = database.delete("person", "_id = ?", new String[]{id + ""});
                return deleteCount;
            } else {
                throw new RuntimeException("输入的uri不合法");
            }
        } finally {

            database.close();
        }


    }

    /**
     * 对外提供修改的操作
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        try {
            int code = matcher.match(uri);
            if (code == 1) {
                int updateCount = database.update("person", values, selection, selectionArgs);
                return updateCount;

            } else if (code == 2) {//修改指定id的数据
                long id = ContentUris.parseId(uri);
                int updateCount = database.update("person", values, "_id = ?", new String[]{id + ""});
                return updateCount;

            } else {
                throw new RuntimeException("输入的uri不合法");
            }
        } finally {

            database.close();
        }

    }
}
