package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private RelativeLayout set_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_accoutq);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        set_pwd = (RelativeLayout) findViewById(R.id.set_pwd);
        set_pwd.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                 finish();
                break;
            case R.id.set_pwd:
                Intent intent=new Intent(this,AmendpwdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
