package com.example.administrator.lenglian.fragment.mine.bean;

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

public class AlterdetailActivity extends BaseActivity {
    private TextView tv_back;
    private ImageView alterdetail_tupian;
    private TextView alterdetail_count;
    private TextView alterdetail_price;
    private TextView alterdetail_zhuantai;
    private TextView alter_name;
    private TextView alter_phone;
    private TextView alter_address;
    private TextView alter_dingdan;
    private TextView dingdan;
    private TextView alter_time;
    private TextView alter_timeok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alteration_xiangq);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        alterdetail_tupian = (ImageView) findViewById(R.id.alterdetail_tupian);
        alterdetail_count = (TextView) findViewById(R.id.alterdetail_count);
        alterdetail_price = (TextView) findViewById(R.id.alterdetail_price);
        alterdetail_zhuantai = (TextView) findViewById(R.id.alterdetail_zhuantai);
        alter_name = (TextView) findViewById(R.id.alter_name);
        alter_phone = (TextView) findViewById(R.id.alter_phone);
        alter_address = (TextView) findViewById(R.id.alter_address);
        alter_dingdan = (TextView) findViewById(R.id.alter_dingdan);
        dingdan = (TextView) findViewById(R.id.dingdan);
        alter_time = (TextView) findViewById(R.id.alter_time);
        alter_timeok = (TextView) findViewById(R.id.alter_timeok);
         tv_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }
}
