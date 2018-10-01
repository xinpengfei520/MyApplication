package com.atguigu.l08_app_endcallsms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.text.InputType.TYPE_CLASS_PHONE;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final int REQUESTCODE = 1;
    private ListView lv_main;
    private MyAdapter adapter;
    private List<BlackNumber> list;
    private EditText et_main_number;
    private BlackNumberDAO dao;
    private int position;

    //声明PopupWindow
    private PopupWindow popupWindow;
    //声明PopupWindow对应的视图
    private View popupView;

    //声明缩放动画
    private ScaleAnimation scaleAnimation;
    private AlphaAnimation alphaAnimation;
    private AnimationSet animationSet;
    private String newName = "未知";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main_number = (EditText) findViewById(R.id.et_main_number);
        lv_main = (ListView) findViewById(R.id.lv_main);

        dao = new BlackNumberDAO(this);
        list = dao.getAll();
        adapter = new MyAdapter();
        lv_main.setAdapter(adapter);

        lv_main.setOnItemClickListener(this);

        lv_main.setOnScrollListener(new AbsListView.OnScrollListener() {

            /*SCROLL_STATE_IDLE:空闲状态
            SCROLL_STATE_TOUCH_SCROLL:滑动的状态
			SCROLL_STATE_FLING:快速滑动的状态*/
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当开始滑动时，设置popupwindow消失
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        this.position = position;

        if (popupWindow == null) {
            popupView = View.inflate(MainActivity.this, R.layout.pw_linearlayout, null);
            //参数2,3：指明popupwindow的宽度和高度
            popupWindow = new PopupWindow(popupView, view.getWidth() - 140, view.getHeight());

            //设置背景图片， 必须设置，不然动画没作用
            popupWindow.setBackgroundDrawable(new BitmapDrawable());

            //创建缩放动画(默认从左上角开始)
            scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
            scaleAnimation.setDuration(500);
            alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setDuration(500);

            animationSet = new AnimationSet(true);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setFillAfter(true);

            //通过popupView获取其内部的2个Linearlayout,并分别设置监听
            popupView.findViewById(R.id.ll_pw_update).setOnClickListener(this);
            popupView.findViewById(R.id.ll_pw_delete).setOnClickListener(this);

        }

        //在重新显示之前，设置popupwindow的销毁
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }

        //显示
        popupWindow.showAsDropDown(view, 140, -view.getHeight());

        //设置动画
        popupView.startAnimation(animationSet);
    }

    @Override
    public void onClick(View v) {

        final BlackNumber blackNumber = list.get(position);

        switch (v.getId()) {
            case R.id.ll_pw_update:

                popupWindow.dismiss();
                final EditText et_new = new EditText(this);
                et_new.setHint("请输入新的号码");
                et_new.setInputType(TYPE_CLASS_PHONE);//TYPE_CLASS_PHONE输入类型为手机号
                new AlertDialog.Builder(this)
                        .setTitle("修改号码" + blackNumber.getNumber())
                        .setView(et_new)
                        .setPositiveButton("修改", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newNumber = et_new.getText().toString().trim();
                                blackNumber.setNumber(newNumber);
                                //1.从数据库中修改
                                dao.update(blackNumber);
                                //2.从集合中修改,可省略，因为两个引用指向同一个对象
//					list.set(position, blackNumber);
                                //3.界面上显示
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "修改完成", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();

                break;
            case R.id.ll_pw_delete:

                popupWindow.dismiss();
                //1.从数据库中删除
                dao.delete(blackNumber.getId());
                //2.从集合中删除
                list.remove(position);
                //3.界面上显示
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "移除成功", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    /**
     * 装数据的适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list == null ? null : list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item, null);

            }

            TextView tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            TextView tv_item_number = (TextView) convertView.findViewById(R.id.tv_item_number);

            /*String getName = list.get(position).getName();
            Log.e("TAG", getName+"");*/
            tv_item_name.setText(list.get(position).getName());
            tv_item_number.setText(list.get(position).getNumber());

            return convertView;
        }
    }

    /**
     * 加入黑名单的回调
     *
     * @param v
     */
    public void selectNumber(View v) {

        Intent intent = new Intent(this, ContactsActivity.class);
        //带回调的启动
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            String number = data.getStringExtra("number");
            newName = data.getStringExtra("name");
            et_main_number.setText(number);
            Log.e("TAG", newName);
        }
    }

    /**
     * 从手机联系人选择的回调
     *
     * @paam v
     */
    public void addNumber(View v) {

        et_main_number = (EditText) findViewById(R.id.et_main_number);
        String number = et_main_number.getText().toString().trim();
       /* boolean flag = nameAndNumber.contains(" "+number);
        if(flag) {//判断nameAndNumber是否包含" "+number
            number = nameAndNumber;
        }*/

        if (number.length() == 0) {

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
            et_main_number.startAnimation(animation);
            Toast.makeText(this, "亲，您还没有输入号码！", Toast.LENGTH_SHORT).show();

        } else {
            for (BlackNumber b : list) {
                if (number.equals(b.getNumber())) {
                    Toast.makeText(this, "此号码已经在黑名单中！", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            BlackNumber blackNumber = new BlackNumber(-1,newName,number);
            newName = "未知";
            Log.e("TAG", "newName=" + newName);
            //1.加入数据库
            dao.add(blackNumber);
            //2.加入集合
//			list.add(blackNumber);
//			方式二：从数据库再全部获取一次，就相当添加入了集合
            list = dao.getAll();
            //3.更新界面
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "拉黑成功", Toast.LENGTH_SHORT).show();
            et_main_number.setText("");//添加完成把里边清空
        }
    }

}
