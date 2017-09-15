package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.AddressBean;
import com.example.administrator.lenglian.bean.OrderPushBean;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;


public class QueRenOrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back, tv_tijiao, tv_name, tv_phone, tv_address;
    private ImageView iv_address;
    private String mId, duration;
    private EditText edt_liuyan;
    private LinearLayout ll_hasAddress;
    private RelativeLayout rl_noaddress;
    private String express_id;
    private String mOrder_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ren_order);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        tv_tijiao = (TextView) findViewById(R.id.tv_tijiao);
        tv_tijiao.setOnClickListener(this);
        iv_address = (ImageView) findViewById(R.id.iv_address);
        iv_address.setOnClickListener(this);
        edt_liuyan = (EditText) findViewById(R.id.edt_liuyan);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        ll_hasAddress = (LinearLayout) findViewById(R.id.ll_hasAddress);
        rl_noaddress = (RelativeLayout) findViewById(R.id.rl_noaddress);
        rl_noaddress.setOnClickListener(this);
        initData();
    }

    private void initData() {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("user_id", SpUtils.getString(QueRenOrderActivity.this, "user_id", ""));
        arrayMap.put("token", MyUtils.getToken());
        arrayMap.put("is_default", "1");
        RetrofitManager.get(MyContants.BASEURL + "s=User/listExpress", arrayMap, new BaseObserver1<AddressBean>("") {
            @Override
            public void onSuccess(AddressBean result, String tag) {
                if (result.getCode() == 200) {
                    ll_hasAddress.setVisibility(View.VISIBLE);
                    rl_noaddress.setVisibility(View.GONE);
                    tv_name.setText(result.getDatas().get(0).getRecieve_name());
                    tv_phone.setText(result.getDatas().get(0).getMobile());
                    tv_address.setText(result.getDatas().get(0).getAddress()
                            + result.getDatas().get(0).getAddress_detail());
                    express_id = result.getDatas().get(0).getExpress_id();
                } else {
                    //101是没有数据
                    ll_hasAddress.setVisibility(View.GONE);
                    rl_noaddress.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_tijiao:
                pushOrder();
                break;
            case R.id.iv_address:
                startActivity(new Intent(this, AddressActivity.class));
                break;
            case R.id.rl_noaddress:
                startActivity(new Intent(this, AddressActivity.class));
                break;
        }
    }

    private void pushOrder() {
        mId = getIntent().getStringExtra("id");
        duration = getIntent().getStringExtra("duration");
        //        Toast.makeText(this, "id::"+mId+"   时长::"+duration, Toast.LENGTH_SHORT).show();
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("pro_id", mId);
        arrayMap2.put("token", MyUtils.getToken());
        arrayMap2.put("user_id", SpUtils.getString(QueRenOrderActivity.this, "user_id", ""));
        arrayMap2.put("duration", duration);
        arrayMap2.put("express_id", express_id);
        arrayMap2.put("order_note", edt_liuyan.getText().toString());
        RetrofitManager.post(MyContants.BASEURL + "s=Order/newOrder", arrayMap2, new BaseObserver1<OrderPushBean>("") {
            @Override
            public void onSuccess(OrderPushBean result, String tag) {
                Toast.makeText(QueRenOrderActivity.this, "xxxxxxxx", Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    Toast.makeText(QueRenOrderActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    mOrder_id = result.getDatas().getOrder_id();
                    Toast.makeText(QueRenOrderActivity.this, " " + mOrder_id, Toast.LENGTH_SHORT).show();
                    //                    startActivity(new Intent(this, OrderPayActivity.class));
                } else {
                    //101是没有数据
                    Toast.makeText(QueRenOrderActivity.this, "sssss" + result.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }
}
