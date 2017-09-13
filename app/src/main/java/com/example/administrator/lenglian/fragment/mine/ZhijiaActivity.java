package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ZhijiaActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView tijiao;
    private EditText edt_pin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evalute_addto);
        initView();

    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tijiao = (TextView) findViewById(R.id.tijiao);
        edt_pin = (EditText) findViewById(R.id.edt_pin);
        tv_back.setOnClickListener(this);
        tijiao.setOnClickListener(this);
        edt_pin.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String pin = edt_pin.getText().toString().trim();
        if (TextUtils.isEmpty(pin)) {
            Toast.makeText(this, "你的评论~", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tijiao:
                 //提交
                break;
            case R.id.edt_pin:
                 edt_pin.setCursorVisible(true);
                break;
        }
    }
}