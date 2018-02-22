package com.example.administrator.broadcastbestpractice_9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button forceOffline = findViewById(R.id.force_offline);

        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.administrator.broadcastbestpractice_9.FORCE_OFFLINE");
                sendBroadcast(intent); //下线的逻辑不卸载mainactivity里，而是在base的广播接收器里
            }
        });
    }
}
