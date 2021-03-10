package com.wxd.firstlinecode.mutilmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wxd.firstlinecode.R;
import com.wxd.firstlinecode.datastorage.DataStorageActivity;

import java.io.File;

public class NotificationActivity extends AppCompatActivity {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, DataStorageActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this,0,intent,0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("test","测试",NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(channel);
                    notification = new NotificationCompat.Builder(NotificationActivity.this, "test")
                            .setContentTitle("this is content title")
                            //.setContentText("Learn how to build notifications,send and sync data," +
                            //       "and use voice actions,Get the official Android IDE and developer tools to build apps for Android")
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications,send and sync data," +
                                    "and use voice actions,Get the official Android IDE and developer tools to build apps for Android"))
                            //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.big_image)))
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .setSound(Uri.fromFile(new File("/sdcard/Music/Musiclrc/We_Are_The_Brave.mp3")))
                            .setVibrate(new long[]{0,1000,1000,1000})
                            .setLights(Color.GREEN,1000,1000)
                            //.setDefaults(NotificationCompat.DEFAULT_ALL)
                            .setContentIntent(pi)
                            .setAutoCancel(true)
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .build();
                }else {
                    notification = new NotificationCompat.Builder(NotificationActivity.this)
                            .setContentTitle("this is content title")
                            .setContentText("this is content text")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .build();
                }
                manager.notify(1, notification);
            }
        });
    }
}