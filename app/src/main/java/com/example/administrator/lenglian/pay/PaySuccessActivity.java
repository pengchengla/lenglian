package com.example.administrator.lenglian.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.MainActivity;
import com.example.administrator.lenglian.base.BaseActivity;

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
        tv_price.setText(getIntent().getStringExtra("price") + "元");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
            case R.id.tv_ok:
                goOrder();
                break;
        }
    }

    private void goOrder() {
        finish();
        removeAllActivitys();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("pay", "success");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
        goOrder();
    }
}
