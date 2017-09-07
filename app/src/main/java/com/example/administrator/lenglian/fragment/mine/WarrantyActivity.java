package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Warrantyadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class WarrantyActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ListView warranty_list;
      private List<Indexbean> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_warranty);
        initView();
        initdata();
    }

    private void initdata() {
          Indexbean indexbean=new Indexbean();
        for (int i = 0; i < 5; i++) {
            indexbean.setCount("阿拉阿发发发 阿达阿达啊大大啊大大啊啊啊阿达"+i);
            list.add(indexbean);
        }

        Warrantyadapter warrantyadapter = new Warrantyadapter(this,list);
        warranty_list.setAdapter(warrantyadapter);
        //listview点击
        warranty_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(WarrantyActivity.this,WarrantydetailActivity.class);
                startActivity(it);
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        warranty_list = (ListView) findViewById(R.id.warranty_list);
        tv_back.setOnClickListener(this);
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
