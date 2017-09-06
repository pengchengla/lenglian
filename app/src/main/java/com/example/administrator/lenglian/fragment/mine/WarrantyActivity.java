package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Warrantyadapter;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class WarrantyActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ListView warranty_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_warranty);
        initView();
        initdata();
    }

    private void initdata() {
        Warrantyadapter warrantyadapter = new Warrantyadapter(this);
        warranty_list.setAdapter(warrantyadapter);
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        warranty_list = (ListView) findViewById(R.id.warranty_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
