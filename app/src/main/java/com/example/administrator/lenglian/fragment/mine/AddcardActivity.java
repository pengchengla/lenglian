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

public class AddcardActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private EditText ced_name;
    private EditText ced_phone;
    private EditText ced_cardnum;
    private EditText edt_code;
    private TextView tv_getcode;
    private Button btn_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_add);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        ced_name = (EditText) findViewById(R.id.ced_name);
        ced_phone = (EditText) findViewById(R.id.ced_phone);
        ced_cardnum = (EditText) findViewById(R.id.ced_cardnum);
        edt_code = (EditText) findViewById(R.id.edt_code);
        tv_getcode = (TextView) findViewById(R.id.tv_getcode);
        btn_yes = (Button) findViewById(R.id.btn_yes);

        btn_yes.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:

                break;
            case R.id.tv_back:
                  finish();
                break;
        }
    }

    private void submit() {
        // validate
        String name = ced_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = ced_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String cardnum = ced_cardnum.getText().toString().trim();
        if (TextUtils.isEmpty(cardnum)) {
            Toast.makeText(this, "请输入银行卡号", Toast.LENGTH_SHORT).show();
            return;
        }

        String code = edt_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }



    }
}
