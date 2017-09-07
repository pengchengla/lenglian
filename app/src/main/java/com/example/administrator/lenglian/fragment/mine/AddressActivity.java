package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Addressadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddressActivity extends BaseActivity implements View.OnClickListener{

    private TextView tv_back;
    private TextView adress_add;
    private ListView list_address;
    private List<Indexbean> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_address);
        initView();
        inindata();
    }

    private void inindata() {
        for (int i = 0; i < 5; i++) {
            Indexbean indexs=new Indexbean();
            indexs.setCount("张三");
            list.add(indexs);
        }
        Addressadapter addressadapter=new Addressadapter(this,list);
        list_address.setAdapter(addressadapter);

    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        adress_add = (TextView) findViewById(R.id.adress_add);
        list_address = (ListView) findViewById(R.id.list_address);
        tv_back.setOnClickListener(this);
        adress_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.adress_add:
                Intent it=new Intent(AddressActivity.this,AddaddressActivity.class);
                startActivity(it);
                break;
        }
    }


}
