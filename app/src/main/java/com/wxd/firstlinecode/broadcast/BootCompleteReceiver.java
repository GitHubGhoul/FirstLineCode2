package com.wxd.firstlinecode.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot complete", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onReceive: Boot complete");
    }
}
