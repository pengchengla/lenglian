package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;


public class QueRenOrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back,tv_tijiao;
    private ImageView iv_address;
    private String mId,duration;
    private EditText edt_liuyan;

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
        edt_liuyan= (EditText) findViewById(R.id.edt_liuyan);
        initData();
    }

    private void initData() {
        mId=getIntent().getStringExtra("id");
        duration=getIntent().getStringExtra("duration");
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("pro_id", mId);
        arrayMap2.put("token", MyUtils.getToken());
        arrayMap2.put("user_id", SpUtils.getString(QueRenOrderActivity.this, "user_id", ""));
        arrayMap2.put("duration", duration);
        arrayMap2.put("express_id", "");
        arrayMap2.put("order_note", edt_liuyan.getText().toString());
        arrayMap2.put("pay_id", "");
        RetrofitManager.get(MyContants.BASEURL + "s=Order/newOrder", arrayMap2, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {
                if (result.getCode() == 200) {

                } else {
                    //101是没有数据
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
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
