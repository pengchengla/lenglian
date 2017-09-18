package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AppraiseActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fan;
    private RelativeLayout tv_back;
    private TextView details_number;
    private TextView details_person;
    private TextView details_num;
    private TextView details_address;
    private ImageView details_tupian;
    private TextView details_miaoshu;
    private TextView details_price;
    private TextView jie_yanjin;
    private TextView details_cost;
    private TextView details_zprice;
    private TextView details_invoice;
    private TextView details_data;
    private TextView details_songdadata;
    private TextView distribution_paymentmethod;
    private TextView distribution_sum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        initView();
    }

    private void initView() {
        fan = (ImageView) findViewById(R.id.fan);
        tv_back = (RelativeLayout) findViewById(R.id.tv_back);
        details_number = (TextView) findViewById(R.id.details_number);
        details_person = (TextView) findViewById(R.id.details_person);
        details_num = (TextView) findViewById(R.id.details_num);
        details_address = (TextView) findViewById(R.id.details_address);
        details_tupian = (ImageView) findViewById(R.id.details_tupian);
        details_miaoshu = (TextView) findViewById(R.id.details_miaoshu);
        details_price = (TextView) findViewById(R.id.details_price);
        jie_yanjin = (TextView) findViewById(R.id.jie_yanjin);
        details_cost = (TextView) findViewById(R.id.details_cost);
        details_zprice = (TextView) findViewById(R.id.details_zprice);
        details_invoice = (TextView) findViewById(R.id.details_invoice);
        details_data = (TextView) findViewById(R.id.details_data);
        details_songdadata = (TextView) findViewById(R.id.details_songdadata);
        distribution_paymentmethod = (TextView) findViewById(R.id.distribution_paymentmethod);
        distribution_sum = (TextView) findViewById(R.id.distribution_sum);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }

    }
}
