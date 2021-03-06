package com.wxd.firstlinecode.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wxd.firstlinecode.R;
import com.wxd.firstlinecode.activity.BaseActivity;

public class BroadcastMainActivity extends BaseActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private Button btnSend;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.wxd.firstlinecode.FORCE_OFFLINE");
                //intent.setPackage(getPackageName());
                sendBroadcast(intent);
                //localBroadcastManager.sendBroadcast(intent);
                //sendOrderedBroadcast(intent,null);
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context, "networks is available", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "networks is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}