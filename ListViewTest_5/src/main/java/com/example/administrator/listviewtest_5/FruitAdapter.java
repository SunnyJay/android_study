package com.example.administrator.listviewtest_5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class FruitAdapter extends ArrayAdapter<Fruit>
{
    /**
     * 就是传进来的子项的布局id。对应fruit_item
     */
    private int resourceId;

    /**
     *
     * @param context 上下文
     * @param textViewResourceId 子布局id
     * @param objects 数据
     */
    public FruitAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Fruit> objects)
    {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * 当每个子项被滚动到屏幕内的时候，该方法会被调用；有10个子项就会调用10次。
     * @param position 当前项的位置
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        /*
        //一、基本
        //1.先获取某个位置的fruit对象，每个位置的fruit都是绑定好的，在构造传进来的时候就确定了，
        //所以根据位置是可以拿出来的。
        Fruit fruit = getItem(position);

        //2.获取一个子项的view对象
        //false表示只让我们在父布局中声明的layout属性生效，但不为这个view添加父布局。
        //标准写法 填充
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        //3.使用fruit再去设置子项文字和图片
        ImageView imageView = view.findViewById(R.id.fruit_image);
        TextView textView = view.findViewById(R.id.fruit_name);
        imageView.setImageResource(fruit.getImageId());
        textView.setText(fruit.getName());
        */


        //二、优化一 缓存布局
        /*
        //1.先获取某个位置的fruit对象，每个位置的fruit都是绑定好的，在构造传进来的时候就确定了，
        //所以根据位置是可以拿出来的。
        Fruit fruit = getItem(position);

        //2.使用convertView缓存某个位置的布局（之前已经加载过了）
        View view;
        if (convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        }
        else
        {
            view = convertView;
        }
        //3.使用fruit再去设置子项文字和图片
        ImageView imageView = view.findViewById(R.id.fruit_image);
        TextView textView = view.findViewById(R.id.fruit_name);
        imageView.setImageResource(fruit.getImageId());
        textView.setText(fruit.getName());
        */

        //三、优化二 将所有控件的实例缓存，不用每次都去获取findViewById
        Fruit fruit = getItem(position);
        ViewHolder viewHolder;
        View view;

        if (convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitText = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder); //将viewHolder存储在view中
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); //重新获取viewHolder
        }

        viewHolder.fruitText.setText(fruit.getName());
        viewHolder.fruitImage.setImageResource(fruit.getImageId());

        return view;
    }

    class ViewHolder
    {
        ImageView fruitImage;
        TextView fruitText;
    }
}
