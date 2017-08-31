package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back;
    private RecyclerView recycler_msg;
    private MsgAdapter mMsgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        recycler_msg = (RecyclerView) findViewById(R.id.recycler_msg);
        recycler_msg.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mMsgAdapter = new MsgAdapter(R.layout.item_msg, list);
        recycler_msg.setAdapter(mMsgAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    class MsgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MsgAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
