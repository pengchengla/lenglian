package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ReceiptActivity extends BaseActivity implements View.OnClickListener {
    private ImageView fan;
    private RelativeLayout tv_back;
    private TextView peisong_number;
    private TextView distribution_state;
    private TextView consignee_person;
    private TextView consign_num;
    private TextView consignee_address;
    private ImageView distribution_tupian;
    private TextView distribution_miaoshu;
    private TextView distribution_price;
    private TextView jie_yanjin;
    private TextView distribution_cost;
    private TextView distribution_zprice;
    private TextView ususally_invoice;
    private TextView distribution_data;
    private TextView distribution_songdadata;
    private TextView distribution_paymentmethod;
    private TextView distribution_sum;
    private Button btn_receiving;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_distribution);
        initView();
    }

    private void initView() {
        fan = (ImageView) findViewById(R.id.fan);
        tv_back = (RelativeLayout) findViewById(R.id.tv_back);
        peisong_number = (TextView) findViewById(R.id.peisong_number);
        distribution_state = (TextView) findViewById(R.id.distribution_state);
        consignee_person = (TextView) findViewById(R.id.consignee_person);
        consign_num = (TextView) findViewById(R.id.consign_num);
        consignee_address = (TextView) findViewById(R.id.consignee_address);
        distribution_tupian = (ImageView) findViewById(R.id.distribution_tupian);
        distribution_miaoshu = (TextView) findViewById(R.id.distribution_miaoshu);
        distribution_price = (TextView) findViewById(R.id.distribution_price);
        jie_yanjin = (TextView) findViewById(R.id.jie_yanjin);
        distribution_cost = (TextView) findViewById(R.id.distribution_cost);
        distribution_zprice = (TextView) findViewById(R.id.distribution_zprice);
        ususally_invoice = (TextView) findViewById(R.id.ususally_invoice);
        distribution_data = (TextView) findViewById(R.id.distribution_data);
        distribution_songdadata = (TextView) findViewById(R.id.distribution_songdadata);
        distribution_paymentmethod = (TextView) findViewById(R.id.distribution_paymentmethod);
        distribution_sum = (TextView) findViewById(R.id.distribution_sum);
        btn_receiving = (Button) findViewById(R.id.btn_receiving);

        btn_receiving.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_receiving:

                break;
            case R.id. tv_back:
                finish();
                break;
        }
    }
}
