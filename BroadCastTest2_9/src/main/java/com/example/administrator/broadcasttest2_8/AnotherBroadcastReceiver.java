package com.example.administrator.broadcasttest2_8;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "AnotherBroadcastReceiver收到", Toast.LENGTH_SHORT).show();

        abortBroadcast(); //阻断广播
    }
}
