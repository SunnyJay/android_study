package com.example.administrator.androidthreadtest_16;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;

    public static final int UPDATE_TEXT = 1;

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    text.setText("很高兴遇见你");
                    break;
                default:
                    break;
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        Button changeText = findViewById(R.id.change_text);
        changeText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //text.setText("很高兴遇见你"); //不能在子线程中直接更新UI
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message); //将Message对象发送出去
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
