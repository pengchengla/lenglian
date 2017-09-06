package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.good.GoodDetailActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class ChangXiaoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private RecyclerView recycler_changxiao;
    private ChangxiaoAdapter mChangxiaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_xiao);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        recycler_changxiao = (RecyclerView) findViewById(R.id.recycler_changxiao);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("畅销界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("畅销界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    private void initData() {
        recycler_changxiao.setLayoutManager(new LinearLayoutManager(this));
        List<String> list=new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mChangxiaoAdapter = new ChangxiaoAdapter(R.layout.changxiao_item,list);
        recycler_changxiao.setAdapter(mChangxiaoAdapter);
        mChangxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(ChangXiaoActivity.this, GoodDetailActivity.class));
            }
        });
    }

    class ChangxiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ChangxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
