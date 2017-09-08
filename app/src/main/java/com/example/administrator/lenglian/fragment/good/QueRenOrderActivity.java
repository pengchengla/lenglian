package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;


public class QueRenOrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back,tv_tijiao;
    private ImageView iv_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_order);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tv_back= (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tv_tijiao= (TextView) findViewById(R.id.tv_tijiao);
        tv_tijiao.setOnClickListener(this);
        iv_address= (ImageView) findViewById(R.id.iv_address);
        iv_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_tijiao:
                startActivity(new Intent(this,OrderPayActivity.class));
                break;
            case R.id.iv_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
        }
    }
}
