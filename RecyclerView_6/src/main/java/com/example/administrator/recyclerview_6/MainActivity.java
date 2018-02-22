package com.example.administrator.recyclerview_6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //layoutManager用于指定RecyclerView的布局方式
        //LinearLayoutManager表示线性布局，可以实现和ListView一样的效果
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); //默认纵向，改成横向
        recyclerView.setLayoutManager(layoutManager);

        //瀑布布局
        //参数1是布局列数，参数2布局排列方向
        /*StaggeredGridLayoutManager layoutManager1 =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);*/

        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
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
