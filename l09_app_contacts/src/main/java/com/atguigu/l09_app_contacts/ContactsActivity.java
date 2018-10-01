package com.atguigu.l09_app_contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView lv_contacts;
    private ContactAdapter adapter;
    private List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        adapter = new ContactAdapter();//不要忘了！
        lv_contacts = (ListView) findViewById(R.id.lv_contacts);

        initData();//初始化集合数据

        lv_contacts.setAdapter(adapter);
        //给ListView设置item的点击事件的监听
        lv_contacts.setOnItemClickListener(this);
    }

    /**
     * 初始化集合（联系人）数据
     * 通过使用ContentResolver调用联系人应用的ContentProvider，获取手机中的所有的联系人
     */
    private List<Map<String, String>> initData() {

        data = new ArrayList<>();
        //获取ContentResolver的对象
        ContentResolver resolver = this.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String name = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String number = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Cursor cursor = resolver.query(uri, new String[]{name, number}, null, null, null);

        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();//注意此处要在while循环里面声明
            String nameValue = cursor.getString(cursor.getColumnIndex(name));
            String numberValue = cursor.getString(cursor.getColumnIndex(number));
            map.put("name", nameValue);
            map.put("number", numberValue);

            data.add(map);
        }

        cursor.close();

        return data;
    }

    //点击ListView的item时的回调方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String number = data.get(position).get("number");

        //携带数据返回
        Intent intent = getIntent();
        intent.putExtra("number", number);
        setResult(RESULT_OK, intent);

        finish();//销毁当前页面，不要忘了！
    }

    /**
     * 用于装数据的适配器
     */
    class ContactAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? null : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = View.inflate(ContactsActivity.this, R.layout.item_contacts, null);
                viewHolder = new ViewHolder();
                convertView.setTag(viewHolder);

                viewHolder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
                viewHolder.tv_item_number = (TextView) convertView.findViewById(R.id.tv_item_number);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //装配集合数据
            Map<String, String> map = data.get(position);
            viewHolder.tv_item_name.setText(map.get("name"));
            viewHolder.tv_item_number.setText(map.get("number"));

            return convertView;
        }

        /**
         * getView的第二层优化
         */
        class ViewHolder {
            TextView tv_item_name;
            TextView tv_item_number;
        }
    }

}
