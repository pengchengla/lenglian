package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private RelativeLayout set_zhanhao;
    private RelativeLayout set_update;
    private RelativeLayout set_clean;
    private RelativeLayout set_about;
    private Button btn_exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        set_zhanhao = (RelativeLayout) findViewById(R.id.set_zhanhao);
        set_update = (RelativeLayout) findViewById(R.id.set_update);
        set_clean = (RelativeLayout) findViewById(R.id.set_clean);
        set_about = (RelativeLayout) findViewById(R.id.set_about);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(this);
        set_zhanhao.setOnClickListener(this);
        set_clean.setOnClickListener(this);
        set_about.setOnClickListener(this);
        set_update.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:

                break;
            case R.id.set_zhanhao:
                Intent intent=new Intent(this,AccountActivity.class);
                startActivity(intent);

                break;
            case R.id.set_update:

                break;
            case R.id.set_clean:

                break;
            case R.id.set_about:
                  Intent intent1=new Intent(this,AboutweActivity.class);
                startActivity(intent1);

                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
