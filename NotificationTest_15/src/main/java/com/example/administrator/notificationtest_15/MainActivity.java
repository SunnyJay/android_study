package com.example.administrator.notificationtest_15;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                Intent intent = new Intent(this, NotificationActivity.class);
                //requestCode一般为0  flags一般为0
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("标题")
                        .setContentText("内容")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true) //设置自动取消
                        .setSound(Uri.fromFile(new File("/Ringtones/443.mp3"))) //播放音频
                        .setVibrate(new long[]{0, 1000, 1000, 1000}) //震动
                        .setLights(Color.RED, 1000, 1000) //前置LED
                        .setDefaults(NotificationCompat.DEFAULT_ALL) //默认
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(
                                "与快充、双摄、全面屏、指纹解锁等相比，NFC功能显得略微低调，" +
                                "小编今天就来盘点一下目前市面上具备NFC功能的千元机。"))
                        //.setStyle(new NotificationCompat.BigPictureStyle()
                        //        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background)))
                        .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知重要程度 不起作用？
                        .build();
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }

}
