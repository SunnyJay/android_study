package com.example.administrator.recyclerview_6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruitList;


    public FruitAdapter(List<Fruit> fruitList)
    {
        this.fruitList = fruitList;
    }

    /**
     * 创建ViewHolder实例，缓存布局
     * @param parent 什么意思？
     * @param viewType 什么意思？
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //拿到item的布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * 对item进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Fruit fruit = fruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitText.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }


    //ViewHoler 缓存item的实例
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fruitImage;
        TextView fruitText;

        public ViewHolder(View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitText = itemView.findViewById(R.id.fruit_name);
        }

    }
}
