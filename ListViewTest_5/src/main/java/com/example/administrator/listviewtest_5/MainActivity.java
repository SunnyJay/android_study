package com.example.administrator.listviewtest_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[]  data= {"1", "2", "3", "4", "5","ss","ff","gg",
            "12", "24", "53", "46", "75","3ss","ff4","5gg"};
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //先提供数据，适配各种数据来源
        //数据是字符串所以是String;
        //构造参数：上下文、布局的id、数据
        //simple_list_item_1是内置的布局文件
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);

        ListView listView = findViewById(R.id.list_view_1);
        listView.setAdapter(adapter); //设置数据来源适配器 也可以自己实现一个ArrayAdapter*/

        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item, fruitList);
        ListView listView = findViewById(R.id.list_view_1);
        listView.setAdapter(fruitAdapter);


        //添加点击事件（注册监听器）
        //点击任何一个item就会调用
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruitList.get(i); //i表示position，可以判断出点的哪一个项目
                Toast.makeText(MainActivity.this, fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initFruits() {
        for(int i = 0; i < 2; i++)
        {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit apple1 = new Fruit("Apple", R.drawable.img2);
            fruitList.add(apple1);
            Fruit apple2 = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple2);
            Fruit apple3 = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple3);
            Fruit apple4 = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple4);
            Fruit apple5 = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple5);
            Fruit apple6 = new Fruit("Apple", R.drawable.img2);
            fruitList.add(apple6);
        }
    }
}
