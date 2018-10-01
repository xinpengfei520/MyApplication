package com.atguigu.l10_app_animation1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText et_main_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main_name = (EditText)findViewById(R.id.et_main_name);
    }

    public void register(View v) {

        String name = et_main_name.getText().toString();
//        if(name == null)
        if("".equals(name)) {

            //1.加载动画的配置文件
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
            et_main_name.startAnimation(animation);

            Toast.makeText(MainActivity.this, "输入的内容为空", Toast.LENGTH_SHORT).show();
        }
    }
}
