package com.wxd.firstlinecode.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wxd.firstlinecode.R;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "ServiceActivity";

    private Button start;
    private Button stop;
    private Button bind;
    private Button unbind;
    private Button start_intent_servie;
    private Button start_download;
    private Button pause_download;
    private Button cancel_download;

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        bind = findViewById(R.id.bind);
        unbind = findViewById(R.id.unbind);
        start_intent_servie = findViewById(R.id.start_intent_servie);
        start_download = findViewById(R.id.start_download);
        pause_download = findViewById(R.id.pause_download);
        cancel_download = findViewById(R.id.cancel_download);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                stopService(intent);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
        start_intent_servie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + Thread.currentThread().getId());
                Intent intent = new Intent(ServiceActivity.this, MyIntentService.class);
                startService(intent);
            }
        });
        start_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadBinder == null) {
                    return;
                }
                //String url = "https://github.com/git-for-windows/git/releases/download/v2.30.2.windows.1/PortableGit-2.30.2-64-bit.7z.exe";
                String url = "https://dl.hdslb.com/mobile/latest/iBiliPlayer-bili.apk";
                downloadBinder.startDownload(url);
            }
        });
        pause_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadBinder == null) {
                    return;
                }
                downloadBinder.pauseDownload();
            }
        });
        cancel_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downloadBinder == null) {
                    return;
                }
                downloadBinder.cancelDownload();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "拒绝权限无法使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}