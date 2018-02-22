package com.example.administrator.uibestpractice_7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private Button send;
    private EditText editText;
    private RecyclerView recyclerView;
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();

        editText= findViewById(R.id.input_text);
        send= findViewById(R.id.send);
        recyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        msgAdapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getText().toString();
                if(!"".equals(content))
                {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    // 当有新消息时刷新ListView中的显示
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    //将ListView定位到最后一行
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    //清空输入框
                    editText.setText("");


                }
            }
        });



    }

    private void initMsgs() {

        Msg msg1 = new Msg("你好", Msg.TYPE_RECIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("你好,你是？", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("我是Jack，很高兴认识你", Msg.TYPE_RECIVED);
        msgList.add(msg3);
    }
}
