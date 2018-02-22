package com.example.administrator.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder;

    //创建一个匿名类，即服务和活动之间的连接，其描述了怎么绑定，绑定或解绑的时候会调用服务的什么接口
    private ServiceConnection connection = new ServiceConnection() {

        //绑定时调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        //解绑时调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startService = findViewById(R.id.start_service);
        Button stopService = findViewById(R.id.stop_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);

        Button bindService = findViewById(R.id.bind_service);
        Button unbindService = findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        Button start_itent_service = findViewById(R.id.start_itent_service);
        start_itent_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent); //启动服务 注意intent是参数
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent); //停止服务
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);

                //BIND_AUTO_CREATE表示在活动和服务进行绑定后自动创建服务，会使得Myservice的OnCreate执行
                bindService(bindIntent, connection, BIND_AUTO_CREATE); //绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection); //解绑服务
                break;
            case R.id.start_itent_service:
                Intent start_itent_service = new Intent(this, MyService.class);
                startService(start_itent_service);
                break;
            default:
                break;
        }
    }
}
