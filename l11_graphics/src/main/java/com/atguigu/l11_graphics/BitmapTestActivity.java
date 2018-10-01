package com.atguigu.l11_graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

/*
 Bitmap: 加载一张图片数据到内存中, 都可以封装成一个Bitmap对象
	 需求1: 加载资源文件中的图片资源并显示
	 需求2: 加载存储空间中的图片资源并显示
	 需求3: 将一个bitmap对象保存到存储空间中

	 使用上：①存储--->内存  ②内存--->存储
 */
public class BitmapTestActivity extends Activity {

    private ImageView iv_bitmap1;
    private ImageView iv_bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_test);

        iv_bitmap1 = (ImageView)findViewById(R.id.iv_bitmap1);
        iv_bitmap2 = (ImageView)findViewById(R.id.iv_bitmap2);

        //需求1.加载资源文件中的图片资源并显示
        iv_bitmap1.setImageResource(R.mipmap.ic_launcher);
        //需求2.加载存储空间中的图片资源并显示
        String filePath = Environment.getExternalStorageDirectory() + "/" + "abc.png";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        iv_bitmap2.setImageBitmap(bitmap);
    }
    
    public void saveImage(View v) {

        //保存图片：内存-->存储
        String filePath = Environment.getExternalStorageDirectory()+ "/" + "abc.png";
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        //将bitmap对象保存到：/data/data/应用包名/files/
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG,100,openFileOutput("abc123.png", Context.MODE_PRIVATE));
            Toast.makeText(BitmapTestActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
