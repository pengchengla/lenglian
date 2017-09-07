package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class WarrantydetailActivity extends BaseActivity {

    private TextView tv_back;
    private ImageView waranty_tupian;
    private TextView waranty_count;
    private TextView waranty_name;
    private TextView waranty_phone;
    private TextView waranty_address;
    private TextView waranty_evolve;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waranty_details);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        waranty_tupian = (ImageView) findViewById(R.id.waranty_tupian);
        waranty_count = (TextView) findViewById(R.id.waranty_count);
        waranty_name = (TextView) findViewById(R.id.waranty_name);
        waranty_phone = (TextView) findViewById(R.id.waranty_phone);
        waranty_address = (TextView) findViewById(R.id.waranty_address);
        waranty_evolve = (TextView) findViewById(R.id.waranty_evolve);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
