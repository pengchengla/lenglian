package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class CardActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView card_remove;
    private RelativeLayout card_add;
    private ListView cardlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_card);
        initView();

    }

    private void initView() {
        cardlist = (ListView) findViewById(R.id.card_list);
        tv_back = (TextView) findViewById(R.id.tv_back);
        card_remove = (TextView) findViewById(R.id.card_remove);
        card_add = (RelativeLayout) findViewById(R.id.card_add);
        card_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.card_remove:
                break;
            case R.id.card_add:
                Intent intent=new Intent(this,AddcardActivity.class);
                startActivity(intent);
                break;
        }
    }
}
