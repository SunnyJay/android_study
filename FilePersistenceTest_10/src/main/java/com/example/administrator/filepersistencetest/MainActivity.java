package com.example.administrator.filepersistencetest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit);

        String input = load();
        if(!TextUtils.isEmpty(input))
        {
            editText.setText(input);
            editText.setSelection(input.length());
            Toast.makeText(this, "恢复成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputString = editText.getText().toString();
        save(inputString);
    }

    public void save(String inputText)
    {
        FileOutputStream out;
        BufferedWriter writer = null;

        try {

            try {
                out = openFileOutput("data", Context.MODE_PRIVATE);
                writer = new BufferedWriter(new OutputStreamWriter(out));
                writer.write(inputText);
            }
            finally {
                writer.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String load()
    {
        FileInputStream in;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {

            try {
                in = openFileInput("data");
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
