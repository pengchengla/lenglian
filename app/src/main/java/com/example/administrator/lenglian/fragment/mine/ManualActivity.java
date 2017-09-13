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

public class ManualActivity extends BaseActivity {
    private TextView tv_back;
    private TextView dinggou;
    private RelativeLayout about_ding;
    private RelativeLayout shservice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_guideline);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        dinggou = (TextView) findViewById(R.id.dinggou);
        about_ding = (RelativeLayout) findViewById(R.id.about_ding);
        shservice = (RelativeLayout) findViewById(R.id.shservice);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        about_ding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //订购
                Intent intent=new Intent(ManualActivity.this,DinggouActivity.class);
                startActivity(intent);
            }
        });
    }

}
