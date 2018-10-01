package com.atguigu.l11_graphics;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Matrix ，中文里叫矩阵，高等数学里有介绍，在图像处理方面，主要是用于平面的缩放、平移、旋转等操作
 */
public class MatrixTestActivity extends AppCompatActivity {

    private EditText et_matrix_scale;
    private EditText et_matrix_rotate;
    private EditText et_matrix_translateX;
    private EditText et_matrix_translateY;

    private ImageView iv_matrix_icon;

    private Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test);

        et_matrix_scale = (EditText)findViewById(R.id.et_matrix_scale);
        et_matrix_rotate = (EditText)findViewById(R.id.et_matrix_rotate);
        et_matrix_translateX = (EditText)findViewById(R.id.et_matrix_translateX);
        et_matrix_translateY = (EditText)findViewById(R.id.et_matrix_translateY);

        iv_matrix_icon = (ImageView)findViewById(R.id.iv_matrix_icon);

        //1.实例化Matrix
        matrix = new Matrix();
    }

    /**
     * 缩放的测试
     * @param v
     */
    public void scaleBitmap(View v) {
        float scaleF = Float.parseFloat(et_matrix_scale.getText().toString());
        //2.处理Matrix
        matrix.postScale(scaleF,scaleF);

        //3. 给ImageView设置Matrix
        iv_matrix_icon.setImageMatrix(matrix);

    }

    /**
     * 旋转的测试
     * @param v
     */
    public void rotateBitmap(View v) {
        float rotateF = Float.parseFloat(et_matrix_rotate.getText().toString());
        matrix.postRotate(rotateF);

        iv_matrix_icon.setImageMatrix(matrix);
    }

    /**
     * 平移的测试
     * @param v
     */
    public void translateBitmap(View v) {
        //获取x轴移动比例
        float translateX = Float.parseFloat(et_matrix_translateX.getText().toString());
        //获取y轴移动比例
        float translateY = Float.parseFloat(et_matrix_translateY.getText().toString());
        matrix.postTranslate(translateX,translateY);

        iv_matrix_icon.setImageMatrix(matrix);
    }

    /**
     * 回到初始位置
     * @param v
     */
    public void clearMatrix(View v) {
        matrix.reset();
        iv_matrix_icon.setImageMatrix(matrix);
    }

}
