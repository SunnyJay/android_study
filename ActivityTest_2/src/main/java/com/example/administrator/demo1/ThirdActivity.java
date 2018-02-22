package com.example.administrator.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d("ThirdActivity", "Task id is " + getTaskId()); //返回当前栈的id
        Log.d("ThirdActivity", "name is " + getClass().getSimpleName());

        Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityController.finishAll(); //销毁所有活动
                android.os.Process.killProcess(android.os.Process.myPid()); //杀死当前进程，退出
            }
        });
    }
}
