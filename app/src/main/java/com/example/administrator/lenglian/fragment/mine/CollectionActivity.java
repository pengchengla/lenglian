package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class CollectionActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView collect_bianji;
    private TextView collect_ok;
    private RelativeLayout collect_title;
    private ListView list_collect;
    private RelativeLayout select_delte;
    private CheckBox collect_check;
    private Button collect_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_collect);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        collect_bianji = (TextView) findViewById(R.id.collect_bianji);
        collect_ok = (TextView) findViewById(R.id.collect_ok);
        collect_title = (RelativeLayout) findViewById(R.id.collect_title);
        list_collect = (ListView) findViewById(R.id.list_collect);
        select_delte = (RelativeLayout) findViewById(R.id.select_delte);
        collect_check = (CheckBox) findViewById(R.id.collect_check);
        collect_btn = (Button) findViewById(R.id.collect_btn);
        tv_back.setOnClickListener(this);
        collect_bianji.setOnClickListener(this);
        collect_ok.setOnClickListener(this);
        collect_btn.setOnClickListener(this);
        collect_check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.collect_bianji:
                collect_bianji.setVisibility(View.GONE);
                collect_ok.setVisibility(View.VISIBLE);
                select_delte.setVisibility(View.VISIBLE);
                break;
            case R.id.collect_ok:
                collect_bianji.setVisibility(View.VISIBLE);
                collect_ok.setVisibility(View.GONE);
                select_delte.setVisibility(View.GONE);
                break;
            case R.id.collect_btn:
                break;
            case R.id.collect_check:
                break;
        }
    }
}
