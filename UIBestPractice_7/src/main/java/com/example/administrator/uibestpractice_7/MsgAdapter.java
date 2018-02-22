package com.example.administrator.uibestpractice_7;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>
{
    private List<Msg> msgList;

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout leftLayout;
        LinearLayout rightLayout;

        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            leftMsg = itemView.findViewById(R.id.left_msg);
            rightMsg = itemView.findViewById(R.id.right_msg);
        }
    }

    /**
     * 负责为Item创建视图
     * @param parent 这里的parent应该指的是RecyclerView这个控件
     * @param viewType
     * @return
     */
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //为每一个item创建一个view,切记，这个view是某一个item的！
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.msg_item, parent, false); //为什么是parent.getContext()

        return new ViewHolder(view); //为每一个item返回一个ViewHolder，用来缓存item的视图以便重用
    }

    /**
     * 负责将数据绑定到Item的视图上。
     * @param holder 某一个item的视图的缓存
     * @param position 某一个item的位置
     */
    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
        Msg msg = msgList.get(position);

        if(msg.getType() == Msg.TYPE_RECIVED)
        {
            //收到的消息，则在左边的消息布局，将右边布局隐藏
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
       }
       else if(msg.getType() == Msg.TYPE_SENT)
        {
            //发到的消息，则在左边的消息布局，将右边布局隐藏
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
