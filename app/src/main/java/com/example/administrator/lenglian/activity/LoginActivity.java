package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText edt_phone, edt_mima, edt_code;
    private Button btn_login;
    private TextView tv_forgetpsw, tv_zhuce;
    private ImageView iv_weixin, iv_qq, iv_weibo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_mima = (EditText) findViewById(R.id.edt_mima);
        edt_code = (EditText) findViewById(R.id.edt_code);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_forgetpsw = (TextView) findViewById(R.id.tv_forgetpsw);
        tv_forgetpsw.setOnClickListener(this);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        tv_zhuce.setOnClickListener(this);
        iv_weixin = (ImageView) findViewById(R.id.iv_weixin);
        iv_weixin.setOnClickListener(this);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_weibo = (ImageView) findViewById(R.id.iv_weibo);
        iv_weibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_zhuce:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_forgetpsw:
                Intent intent3 = new Intent(this, ForgetpswActivity.class);
                startActivity(intent3);
                break;
            case R.id.iv_weibo:
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_qq:
                break;
        }
    }
}
