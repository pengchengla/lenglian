package com.example.administrator.lenglian.pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EventMessage;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/1/14.
 */
public class PaySuccessActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back, tv_price, tv_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tv_ok = (TextView) findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(this);
        tv_price.setText(getIntent().getStringExtra("price")+"元");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_ok:
                EventMessage eventMessage2 = new EventMessage("order");
                EventBus.getDefault().postSticky(eventMessage2);
                finish();
                break;
        }
    }
}
