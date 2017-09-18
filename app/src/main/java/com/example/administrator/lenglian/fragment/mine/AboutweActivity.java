package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.MyApplication;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AboutweActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private EditText set_edwe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_aboutwe);
        initView();


    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        set_edwe = (EditText) findViewById(R.id.set_edwe);
        tv_back.setOnClickListener(this);
        set_edwe.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String edwe = set_edwe.getText().toString().trim();
        if (TextUtils.isEmpty(edwe)) {
            Toast.makeText(this, "关于我们的内容", Toast.LENGTH_SHORT).show();
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
            case R.id. set_edwe:
                set_edwe.setCursorVisible(true);
                break;
        }
    }
}
