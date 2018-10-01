package com.atguigu.l10_app_animation1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Guide2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);
    }

    public void next(View v) {
        startActivity(new Intent(this,Guide3Activity.class));
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void pre(View v) {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
