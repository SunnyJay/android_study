package com.example.administrator.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //接受前一个活动的数据
       /* Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d("SecondActivity", data); //不知道为何日志查不到

        Button button_2 = findViewById(R.id.button_2);
        button_2.setText(data); //通过button显示出数据*/

        //接受前一个活动的数据并返回
        /*Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("data_return","返回值");
                setResult(RESULT_OK, intent);
                finish(); //销毁自身(当前活动)
            }
        });*/

        //singleInstance模式
        Log.d("SecondActivity", "Task id is " + getTaskId()); //返回当前栈的id
        Log.d("SecondActivity", "name is " + getClass().getSimpleName());
        Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 若不是通过按钮而是通过back键返回，则重写该方法解决
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "我是通过back返回的！");
        setResult(RESULT_OK, intent);
        finish();
    }
}
