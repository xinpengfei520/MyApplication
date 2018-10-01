package com.atguigu.l09_app_contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static final int REQUESTCODE = 1;
    private EditText et_main_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main_number = (EditText) findViewById(R.id.et_main_number);
    }

    /**
     * 选择联系人的回调方法
     *
     * @param v
     */
    public void selectContacts(View v) {

        Intent intent = new Intent(this, ContactsActivity.class);
        //带回调的启动
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            String number = data.getStringExtra("number");
            et_main_number.setText(number);
        }
    }
}
