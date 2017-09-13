package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AmendpwdActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private EditText set_initialpwd;
    private EditText set_loginpwd;
    private EditText set_replacepwd;
    private Button set_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_alterpwd);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        set_initialpwd = (EditText) findViewById(R.id.set_initialpwd);
        set_loginpwd = (EditText) findViewById(R.id.set_loginpwd);
        set_replacepwd = (EditText) findViewById(R.id.set_replacepwd);
        set_btn = (Button) findViewById(R.id.set_btn);
        set_btn.setOnClickListener(this);
        tv_back .setOnClickListener(this);
        set_initialpwd.setOnClickListener(this);
        set_replacepwd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_btn:
              //修改密码提交
                submit();
                break;
            case R.id.tv_back:
                finish();

                break;
        }
    }

    private void submit() {
        // validate
        String initialpwd = set_initialpwd.getText().toString().trim();
        if (TextUtils.isEmpty(initialpwd)) {
            Toast.makeText(this, "请输原始密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String loginpwd = set_loginpwd.getText().toString().trim();
        if (TextUtils.isEmpty(loginpwd)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
         else   if (set_loginpwd.getText().toString().length() < 5 || set_loginpwd.getText().toString().length() > 20) {
            Toast.makeText(this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String replacepwd = set_replacepwd.getText().toString().trim();
        if (TextUtils.isEmpty(replacepwd)) {
            Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }else  if (set_loginpwd.getText().toString().trim().equals(set_replacepwd.getText().toString().trim())){
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
              //返回数据

            return;
        }
        else   if (set_loginpwd.getText().toString().length() < 5 || set_loginpwd.getText().toString().length() > 20) {
            Toast.makeText(this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(this, "两次密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
        }


        // TODO validate success, do something


    }
}
