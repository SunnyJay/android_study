package com.example.administrator.broadcastbestpractice_9;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/11/16.
 */

public class BaseActivity  extends AppCompatActivity{

    private ForceOfflineReceiver forceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 在onResume中取消广播处理器注册。
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (forceOfflineReceiver != null) {
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null; //全局变量置为null
        }
    }

    /**
     * 在onResume中注册广播处理器。
     * 因为只需要保证只有处理栈顶的活动才能强制收到这条下线广播，非栈顶的不应该也没有必要去接受这条广播
     */
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.broadcastbestpractice_9.FORCE_OFFLINE");
        forceOfflineReceiver = new ForceOfflineReceiver();
        registerReceiver(forceOfflineReceiver, intentFilter);
    }

    class ForceOfflineReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(final Context context, Intent intent) {

            //对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告").setMessage("强制下线，请重新登录").setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCollector.finishAll();
                            Intent intent1 = new Intent(context, LoginActivity.class);
                            context.startActivity(intent1); //跳转到登录
                        }
                    });
            builder.show();
        }
    }
}
