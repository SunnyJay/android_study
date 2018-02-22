package com.example.administrator.litepaltest_12;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase(); //最简单数据库操作，会创建数据库（Connector是litepal的类）
            }
        });

        //添加
        Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("san guo yan yi");
                book.setAuthor("ha");
                book.setPages(453);
                book.setPrice(23.53);
                book.save(); //继承的方法
            }
        });

        //更新方式一
        Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("xi you ji");
                book.setAuthor("sd");
                book.setPages(67);
                book.setPrice(35);
                book.save(); //继承的方法
                book.setPrice(53);
                book.save(); //更新
            }
        });

        //更新方式二
        Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("hong lou meng");
                book.setAuthor("ss");
                book.setToDefault("pages"); //设置pages字段为默认值
                book.updateAll(); //不指定条件则更新所有数据
                book.updateAll("name = ? and author = ?", "The Lost Symbal", "Dan Brown");
            }
        });

        //删除数据
        Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });

        //查询数据
        Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = DataSupport.findAll(Book.class);
                Log.d("MainActivity", "findAll");
                for (Book book : books) {
                    Log.d("MainActivity", "book name is" + book.getName());
                    Log.d("MainActivity", "book price is" + book.getPrice());
                }


                books = DataSupport
                        .select("name", "author", "pages")
                        .where("pages > ?", "400")
                        .order("pages")
                        .limit(10)
                        .offset(10)
                        .find(Book.class);

                Log.d("MainActivity", "select");
                for (Book book : books) {
                    Log.d("MainActivity", "book name is" + book.getName());
                    Log.d("MainActivity", "book price is" + book.getPrice());
                }


                Cursor cursor = DataSupport.findBySQL("select * from Book where pages > ? and price < ?",
                        "400", "20");

                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                    }
                    while (cursor.moveToNext());
                }
            }
        });

    }
}
