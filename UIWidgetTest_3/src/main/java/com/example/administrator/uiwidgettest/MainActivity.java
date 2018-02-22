package com.example.administrator.uiwidgettest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_1 = findViewById(R.id.button_1);
        final EditText edit_text_1 = findViewById(R.id.edit_text_1);
        final ImageView imageView  = findViewById(R.id.image_view_1);
        final ProgressBar progressBar  = findViewById(R.id.progress_bar_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示文本框文字
                /*String inputText = edit_text_1.getText().toString();
                Toast.makeText(MainActivity.this, inputText,
                        Toast.LENGTH_SHORT).show();*/

                //动态改变图片
                //注意使用R.drawable引用
                /*imageView.setImageResource(R.drawable.img2);*/


                //隐藏显示滚动条
                switch (view.getId()){ //getId获得当前控件id
                    case R.id.button_1:
                        if (progressBar.getVisibility() == View.GONE)
                        {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            progressBar.setVisibility(View.GONE);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        Button button_2 = findViewById(R.id.button_2);
        final ProgressBar progressBar_2  = findViewById(R.id.progress_bar_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress = progressBar_2.getProgress();
                progress += 10;
                progressBar_2.setProgress(progress);
            }
        });


        //警告对话框
        Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("对话框");
                alertDialog.setMessage("非常重要");
                alertDialog.setCancelable(false);

                //Positive
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                //Negativ
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });


    }
}
