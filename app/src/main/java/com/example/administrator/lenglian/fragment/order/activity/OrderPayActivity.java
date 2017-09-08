package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class OrderPayActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView detail_number;
    private TextView detail_name;
    private TextView detail_phone;
    private TextView detail_addres;
    private ImageView shop_tupian;
    private TextView shop_describe;
    private TextView buy_price;
    private TextView buy_num;
    private TextView jie_yanjin;
    private TextView delivery_cost;
    private TextView total_price;
    private TextView ususally_invoice;
    private TextView order_data;
    private RecyclerView odetail_recy;
    private TextView order_pause;
    private TextView order_zhifi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        detail_number = (TextView) findViewById(R.id.detail_number);
        detail_name = (TextView) findViewById(R.id.detail_name);
        detail_phone = (TextView) findViewById(R.id.detail_phone);
        detail_addres = (TextView) findViewById(R.id.detail_addres);
        shop_tupian = (ImageView) findViewById(R.id.shop_tupian);
        shop_describe = (TextView) findViewById(R.id.shop_describe);
        buy_price = (TextView) findViewById(R.id.buy_price);
        buy_num = (TextView) findViewById(R.id.buy_num);
        jie_yanjin = (TextView) findViewById(R.id.jie_yanjin);
        delivery_cost = (TextView) findViewById(R.id.delivery_cost);
        total_price = (TextView) findViewById(R.id.total_price);
        ususally_invoice = (TextView) findViewById(R.id.ususally_invoice);
        order_data = (TextView) findViewById(R.id.order_data);
        odetail_recy = (RecyclerView) findViewById(R.id.odetail_recy);
        order_pause = (TextView) findViewById(R.id.order_pause);
        order_zhifi = (TextView) findViewById(R.id.order_zhifi);
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
