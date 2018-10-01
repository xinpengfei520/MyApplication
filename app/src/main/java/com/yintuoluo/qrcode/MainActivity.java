package com.yintuoluo.qrcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * 根据文字生成二维码
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_qr_content;
    private Button btn_qr;
    private ImageView img_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        et_qr_content=(EditText) findViewById(R.id.et_qr_content);
        btn_qr=(Button)findViewById(R.id.btn_qr_code);
        img_qr=(ImageView)findViewById(R.id.img_qr);

        btn_qr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_qr_code://点击生成二维码
                Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap bitmap= EncodingUtils.createQRCode(et_qr_content.getText().toString().trim(), 500, 500, logoBitmap);
                img_qr.setImageBitmap(bitmap);
                break;
        }
    }
}
