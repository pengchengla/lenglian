package com.example.administrator.lenglian.fragment.mine;

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

public class WalletActivity extends BaseActivity {

    private TextView tv_back;
    private ImageView img_yu;
    private TextView yue;
    private TextView balance;
    private RelativeLayout recl_title;
    private RelativeLayout wallet_time;
    private TextView wallet_date;
    private RelativeLayout wallet_dates;
    private TextView wallet_cash;
    private TextView cash_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_wallet);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        img_yu = (ImageView) findViewById(R.id.img_yu);
        yue = (TextView) findViewById(R.id.yue);
        balance = (TextView) findViewById(R.id.balance);//余额
        recl_title = (RelativeLayout) findViewById(R.id.recl_title);
        wallet_time = (RelativeLayout) findViewById(R.id.wallet_time);
        wallet_date = (TextView) findViewById(R.id.wallet_date);//使用时间
        wallet_dates = (RelativeLayout) findViewById(R.id.wallet_dates);
        wallet_cash = (TextView) findViewById(R.id.wallet_cash);
        cash_num = (TextView) findViewById(R.id.cash_num);//押金
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
