package com.example.administrator.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button addData = findViewById(R.id.add);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //要访问的地址
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "aaa");
                values.put("author", "bbb");
                values.put("pages", 2323);
                values.put("price", 343.6);

                //getContentResolver用于访问内容提供器中共享的数据
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button query = findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //要访问的地址
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/book");
                //query返回cursor
                Cursor cursor = getContentResolver().query(uri, null, null, null,
                        null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d("MainActivity", "Book name is" + name);
                    }
                }
                cursor.close(); // 记得要关闭
            }
        });

        Button update = findViewById(R.id.modify);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //要访问的地址
                Uri uri = Uri.parse("content://com.example.administrator.databasetest.provider/book"
                + newId);
                ContentValues values = new ContentValues();
                values.put("name", "ttt");
                values.put("author", "yyy");
                getContentResolver().update(uri, values, null, null);
            }
        });
    }
}
