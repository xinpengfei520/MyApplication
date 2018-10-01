package com.atguigu.l10_animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/*
 * 测试: Drawable Animation
 */
public class DAActivity extends Activity implements OnClickListener {

    private ImageView iv_da_mm;
    private Button btn_da_start;
    private Button btn_da_stop;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da);

        iv_da_mm = (ImageView) findViewById(R.id.iv_da_mm);
        btn_da_start = (Button) findViewById(R.id.btn_da_start);
        btn_da_stop = (Button) findViewById(R.id.btn_da_stop);

        btn_da_start.setOnClickListener(this);
        btn_da_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btn_da_start) {
            if(animationDrawable == null) {
                //得到背景动画图片对象
                animationDrawable = (AnimationDrawable) iv_da_mm.getBackground();
                //启动动画
                animationDrawable.start();
            }
        }else if(v == btn_da_stop) {
            if(animationDrawable != null) {
                //停止动画
                animationDrawable.stop();
                animationDrawable = null;//不要忘了
            }

        }
    }
}
