package com.wxd.firstlinecode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了自定义广播", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onReceive: 收到了自定义广播");
        //abortBroadcast();
    }
}
