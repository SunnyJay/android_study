package com.example.administrator.servicetest;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    //继承Binder 提供开始下载和查看下载进度方法
    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d("Myservice", "StartDownload executed");
        }

        public int getProgress() {
            Log.d("Myservice", "getProgress executed");
            return 0;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myservice", "onCreate executed");

        Intent intent = new Intent(this, MainActivity.class);
        //PendingIntent是延迟支持的Intent
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentText("这是内容")
                .setContentTitle("这是标题")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();

        //第一个参数是通知的id，类似notify的第一个参数
        startForeground(1, notification); //变成前台服务，并在状态栏显示, 但实验貌似不起作用
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Myservice", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Myservice", "onDestroy executed");
    }
}
