package com.example.administrator.brodcasttest_8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReiver networkChangeReiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        // 当网络发生改变时，系统发出的就是这个广播
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        networkChangeReiver = new NetworkChangeReiver();
        registerReceiver(networkChangeReiver, intentFilter); //注册广播，即将某个广播和某个处理机制绑定


        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //广播是由intent传播的,所以构建Intent对象
                Intent intent = new Intent("com.example.administrator.brodcasttest_8.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //广播是由intent传播的,所以构建Intent对象
                Intent intent = new Intent("com.example.administrator.brodcasttest_8.MY_BROADCAST");

                //参数2为与权限有关的字符串
                sendOrderedBroadcast(intent, null);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReiver); //动态注册的广播接收器一定要取消注册才行
    }

    class NetworkChangeReiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "网络改变", Toast.LENGTH_SHORT).show();

            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); //获取管理网络的系统服务类

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo(); //通过ConnectivityManager获取网络实例
            if (networkInfo != null && networkInfo.isAvailable())
            {
                Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
