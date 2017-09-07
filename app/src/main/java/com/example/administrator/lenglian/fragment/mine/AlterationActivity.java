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
import com.example.administrator.lenglian.fragment.mine.adapter.Alterationadapter;
import com.example.administrator.lenglian.fragment.mine.bean.AlterdetailActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AlterationActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ListView list_alteration;
    private List<Indexbean> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_alteration);
        initView();
        inindata();

    }

    private void inindata() {
        Indexbean index=new Indexbean();
        for (int i = 0; i < 5; i++) {
               index.setCount("豪华的冰柜，你值得拥有。爱不释手无与伦比哈哈哈"+i);
            list.add(index);
        }

            list_alteration.setAdapter(new Alterationadapter(this,list));

        list_alteration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(AlterationActivity.this,AlterdetailActivity.class);
                startActivity(it);
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        list_alteration = (ListView) findViewById(R.id.list_alteration);
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
