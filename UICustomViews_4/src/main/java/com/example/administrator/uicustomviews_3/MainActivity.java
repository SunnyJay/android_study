package com.example.administrator.uicustomviews_3;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐藏自带标题栏
        ActionBar actionBar = getActionBar();
        if (actionBar != null)
        {
            actionBar.hide();
        }

        Button button_1 = findViewById(R.id.title_back);
        Button button_2 = findViewById(R.id.title_edit);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你点击了编辑", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
