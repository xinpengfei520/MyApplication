package com.atguigu.l10_app_animation1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Guide3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide3);
    }

    public void pre(View v) {
        finish();
        //在finish之后指明切换的动画
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    public void complete(View v) {

        startActivity(new Intent(this,MainActivity.class));
        //在startActivity之后指明切换的动画
        overridePendingTransition(R.anim.bottom_in, R.anim.alpha_out);
    }
}
