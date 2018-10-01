package com.atguigu.l10_app_animation1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Guide1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);
    }

    /**
     * 下一页回调
     *
     * @param v
     */
    public void next(View v) {
        startActivity(new Intent(this, Guide2Activity.class));
        //在startActivity之后指明切换的动画
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
