package com.example.administrator.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db",null, 3);


        Button button = findViewById(R.id.create_database);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.getWritableDatabase(); //存在打开，不存在则创建，若数据库不可写入，则抛出异常
            }
        });


        Button button_add = findViewById(R.id.add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase(); //存在打开，不存在则创建，若数据库不可写入，则抛出异常
                ContentValues values = new ContentValues();

                //组装第一条数据
                values.put("name", "sun");
                values.put("author", "jack");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);
                values.clear();

                //组装第二条数据
                values.put("name", "liu");
                values.put("author", "lucy");
                values.put("pages", 234);
                values.put("price", 21.66);
                db.insert("Book", null, values);
            }
        });


        Button button_delete = findViewById(R.id.delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase(); //存在打开，不存在则创建，若数据库不可写入，则抛出异常
                db.delete("Book", "pages > ?", new String[]{"200"}); //不需要values
            }
        });


        Button button_modify = findViewById(R.id.modify);
        button_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase(); //存在打开，不存在则创建，若数据库不可写入，则抛出异常
                ContentValues values = new ContentValues();

                //组装第一条数据
                values.put("price", 500);
                //第三个参数对应where部分,?是占位，可以通过第四个参数的字符串数组作为每个?号
                db.update("Book",values, "name = ?", new String[]{"liu"});
            }
        });


        Button button_query = findViewById(R.id.query);
        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase(); //存在打开，不存在则创建，若数据库不可写入，则抛出异常

                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) { //挪到第一行
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    }
                    while (cursor.moveToNext());
                }

                cursor.close(); //记得关闭
            }
        });




    }
}
