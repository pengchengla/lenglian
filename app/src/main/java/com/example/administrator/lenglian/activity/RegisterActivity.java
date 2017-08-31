package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/24.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back, tv_getcode, tv_xieyi, tv_denglu;
    private EditText edt_phone, edt_code, edt_mima, edt_mima2;
    private Button btn_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_code = (EditText) findViewById(R.id.edt_code);
        edt_mima = (EditText) findViewById(R.id.edt_mima);
        edt_mima2 = (EditText) findViewById(R.id.edt_mima2);
        tv_getcode = (TextView) findViewById(R.id.tv_getcode);
        tv_getcode.setOnClickListener(this);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(this);
        tv_xieyi = (TextView) findViewById(R.id.tv_xieyi);
        tv_xieyi.setOnClickListener(this);
        tv_denglu = (TextView) findViewById(R.id.tv_denglu);
        tv_denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                break;
            case R.id.tv_getcode:
                break;
            case R.id.btn_yes:
                Intent intent1=new Intent(this,ZiLiaoActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_xieyi:
                Intent intent=new Intent(this,XieYiActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_denglu:
                break;
        }
    }
}
