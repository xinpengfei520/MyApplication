package com.atguigu.l10_app_animation2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ImageView iv_main_scanner;
    private TextView tv_main_message;
    private ProgressBar pb_main_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_main_scanner = (ImageView) findViewById(R.id.iv_main_scanner);
        tv_main_message = (TextView) findViewById(R.id.tv_main_message);
        pb_main_loading = (ProgressBar) findViewById(R.id.pb_main_loading);

        //给ImageView设置动画
        setAnimation(iv_main_scanner);

        //使用异步任务实现具体扫描的功能
        scan();
    }

    private void scan() {

        new AsyncTask<Void, Void, Void>() {

            //第1步：主线程：显示提示视图
            @Override
            protected void onPreExecute() {
                tv_main_message.setText("手机杀毒引擎开始扫描...");

            }

            //第2步：分线程：扫描
            @Override
            protected Void doInBackground(Void... params) {

                final int appCount = 100;


/*                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });*/
                        pb_main_loading.setMax(appCount);

                        //通过循环的方式显示进度
                        for (int i = 0; i < appCount; i++) {
                            pb_main_loading.incrementProgressBy(1);
                            SystemClock.sleep(100);
                        }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //1.修改textView显示内容
                tv_main_message.setText("扫描完成，未发现病毒应用，请放心使用");
                //2.设置ProgressBar的消失
                pb_main_loading.setVisibility(View.GONE);
                //3.停止动画
                iv_main_scanner.clearAnimation();
            }
        }.execute();

    }

    /**
     * 给view设置旋转动画
     */
    private void setAnimation(View view) {

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(Animation.INFINITE);//无限循环
        rotateAnimation.setInterpolator(new LinearInterpolator());//匀速旋转

        view.startAnimation(rotateAnimation);
    }
}
