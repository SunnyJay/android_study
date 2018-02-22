package com.example.administrator.sharedpreferencestest_10;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.save_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //注意又是Builder模式
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom")
                        .putInt("age", 28)
                        .putBoolean("married", false)
                        .apply();
            }
        });

        Button button1 = findViewById(R.id.get_data);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //注意又是Builder模式
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                int age = pref.getInt("age", 0);
                String name = pref.getString("name", "");
                boolean married = pref.getBoolean("married", false);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("注意").setMessage("age" + age + "name" + name + "married" + married)
                        .setCancelable(true)
                        .show();
            }
        });
    }
}
