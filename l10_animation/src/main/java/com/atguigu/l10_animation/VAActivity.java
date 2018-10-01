package com.atguigu.l10_animation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * 编码实现View Animation
 * 	1. Code方式
 *  2. Xml方式
 */
public class VAActivity extends Activity {

    private ImageView iv_animation;
    private TextView tv_animation_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_va);

        iv_animation = (ImageView)findViewById(R.id.iv_animation);
        tv_animation_msg = (TextView)findViewById(R.id.tv_animation_msg);
    }

    /**
     * 1.1  编码实现: 缩放动画
     * @param v
     */
    public void startCodeScale(View v) {

        tv_animation_msg.setText("Code缩放动画：宽度从0.5到1.5, 高度从0.0到1.0, 缩放的圆心为顶部中心点,延迟1s开始,持续2s,最终还原");
        //1.创建对象
        ScaleAnimation animation = new ScaleAnimation(0.5f,1.5f,0,1, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0);

        //2.参数设置
        animation.setDuration(2000);//持续2s
        animation.setStartOffset(1000);//延迟1s
        animation.setFillAfter(false);//最终还原

        //3.启动动画
        iv_animation.startAnimation(animation);
    }

    /**
     * 1.2使用xml实现缩放动画
     * @param v
     */
    public void startXmlScale(View v) {
        tv_animation_msg.setText("Xml缩放动画: Xml缩放动画: 宽度从0.0到1.5, 高度从0.0到1.0, 延迟1s开始,持续3s,圆心为左上角, 最终固定");

        //1.创建一个相应动画的配置文件
        //2.加载配置文件，生成对应的动画对象
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        //3.启动动画
        iv_animation.startAnimation(animation);

    }

    /**
     * 2.1使用代码实现旋转动画
     * @param v
     */
    public void startCodeRotate(View v) {
        tv_animation_msg.setText("Code旋转动画: 以图片中心点为中心, 从负90度到正90度, 持续5s");
        //1.创建RotateAnimation对象
        RotateAnimation rotateAnimation = new RotateAnimation(-90, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //2.设置参数
        rotateAnimation.setDuration(5000);//持续的时间
        rotateAnimation.setFillAfter(true);//停留在最终的位置

        //3.启动动画
        iv_animation.startAnimation(rotateAnimation);
    }

    /**
     * 2.2使用xml实现旋转动画
     * @param v
     */
    public void startXmlRotate(View v) {
        tv_animation_msg.setText("Xml旋转动画: 以左顶点为坐标, 从正90度到负90度, 持续5s");
        //1.创建一个相应动画的配置文件
        //2.加载配置文件，生成对应的动画对象
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        //3.启动动画
        iv_animation.startAnimation(animation);
    }

    /**
     * 3.1使用代码实现透明度动画
     * @param v
     */
    public void startCodeAlpha(View v) {
        tv_animation_msg.setText("Code透明度动画: 从完全透明到完全不透明, 持续2s");
        //1.创建AlphaAnimation对象
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1.0f);//完全透明：0 完全不透明：1.0

        //2.设置参数
        alphaAnimation.setDuration(2000);
        //3.启动动画
        iv_animation.startAnimation(alphaAnimation);
    }

    /**
     * 3.2使用xml实现透明度动画
     * @param v
     */
    public void startXmlAlpha(View v) {
        tv_animation_msg.setText("Xml透明度动画: 从完全不透明到完全透明, 持续1s");
        //1.创建一个相应动画的配置文件
        //2.加载配置文件，生成对应的动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        //3.启动动画
        iv_animation.startAnimation(animation);
    }

    /**
     * 4.1 编码实现: 平移动画
     * @param v
     */
    public void startCodeTranslate(View v) {
        tv_animation_msg.setText("Code移动动画: 向右移动一个自己的宽度, 向下移动一个自己的高度, 持续2s");
        //1.创建TranslateAnimation对象
//        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, iv_animation.getWidth(), Animation.ABSOLUTE, 0, Animation.ABSOLUTE, iv_animation.getHeight());

        //2.设置参数
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);

        //3.启动动画
        iv_animation.startAnimation(translateAnimation);
    }

    /**
     *  4.2 xml实现: 平移动画
     * @param v
     */
    public void startXmlTranslate(View v) {
        tv_animation_msg.setText("xml移动动画: 从屏幕的右边逐渐回到原来的位置, 持续2s"); //***此效果用于界面切换的动画效果
        //1.创建一个相应动画的配置文件
        //2.加载配置文件，生成对应的动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        //3.启动动画
        iv_animation.startAnimation(animation);
    }

    /**
     * 5.1 编码实现: 复合动画
     * @param v
     */
    public void startCodeAnimationSet(View v) {

        tv_animation_msg.setText("Code复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续1s");
        //1.创建AnimationSet对象
        AnimationSet animationSet = new AnimationSet(false);
        //2.创建具体的单一动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);

        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setStartOffset(2000);//设置延迟时间
        //3.将各个单一动画添加到AnimationSet中
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);

        //4.启动动画
        iv_animation.startAnimation(animationSet);
    }

    /**
     * 5.2 xml实现: 复合动画
     * @param v
     */
    public void startXmlAnimationSet(View v) {
        tv_animation_msg.setText("Xml复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续2s");
        //1.创建一个相应动画的配置文件
        //2.加载配置文件，生成对应的动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
        //3.启动动画
        iv_animation.startAnimation(animation);
    }

    /**
     * 6. 测试动画监听
     * @param v
     */
    public void testAnimationListener(View v) {

        tv_animation_msg.setText("测试动画监听");

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);//持续时间
        rotateAnimation.setFillAfter(true);//停留在最终的位置
//        rotateAnimation.setRepeatCount(3);//重复执行3次
        rotateAnimation.setRepeatCount(Animation.INFINITE);//无限循环执行
        rotateAnimation.setInterpolator(new LinearInterpolator());//设置动画为匀速变化

        //设置动画的监听
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {//动画启动时
                Log.e("TAG", "启动动画");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("TAG", "结束动画");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e("TAG", "重复动画");
            }
        });
        iv_animation.startAnimation(rotateAnimation);
    }
}
