package com.atguigu.l08_app_endcallsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * 用于接收短信的广播接收器
 */
public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //短信数据保存在intent中
        Bundle extras = intent.getExtras();
        Object[] pdus = (Object[]) extras.get("pdus");//获取短信所有数据
        byte[] pdu = (byte[]) pdus[0];//当前短信的联系人和内容封装在pdu中

        SmsMessage smsMessage = SmsMessage.createFromPdu(pdu);
        String number = smsMessage.getOriginatingAddress();
        String message = smsMessage.getMessageBody();
        Log.e("TAG", "number = " + number + ",message = " + message);

        //拦截短信
        if ("10001".equals(number)) {

            abortBroadcast();
            Log.e("TAG", "拦截成功");
        }
    }
}
