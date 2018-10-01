package com.atguigu.l10_app_animation1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class WelcomeActivity extends Activity {

    private LinearLayout ll_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ll_welcome = (LinearLayout) findViewById(R.id.ll_welcome);

        //给当前的LinearLayout添加动画
        startAnimation();
    }

    /**
     * 给LinearLayout添加：
     * 1.缩放动画  3s
     * 2.透明度动画 3s
     * 3.旋转动画 3s
     */
    private void startAnimation() {

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);

        //将三个单一动画添加到AnimationSet中
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);

        //设置动画的监听
//        animationSet.setAnimationListener();//通常不是给animationSet设置监听
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this, Guide1Activity.class));
                finish();//在开启一个新的Activity的同时，销毁当前的Activity
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //启动动画
        ll_welcome.startAnimation(animationSet);

    }
}
