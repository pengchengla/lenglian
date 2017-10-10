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

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.AddressBean;
import com.example.administrator.lenglian.bean.OrderPushBean;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;


public class QueRenOrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back, tv_tijiao, tv_name, tv_phone,
            tv_address, tv_title, tv_price, tv_count, tv_yajin,
            tv_peisongfei, tv_total_price, tv_total_price_bottom;
    private ImageView iv_address, iv_tupian;
    private String mId, duration;
    private EditText edt_liuyan;
    private LinearLayout ll_hasAddress;
    private RelativeLayout rl_noaddress;
    private String express_id;
    private String mOrder_id;
    private String img_url;
    private String good_title;
    private String good_price;
    private String yajin;
    private String peisongfei;
    private String total_price;

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
        iv_tupian = (ImageView) findViewById(R.id.iv_tupian);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_yajin = (TextView) findViewById(R.id.tv_yajin);
        tv_peisongfei = (TextView) findViewById(R.id.tv_peisongfei);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_total_price_bottom = (TextView) findViewById(R.id.tv_total_price_bottom);
        initAddress();
        initData();
    }

    private void initData() {
        mId = getIntent().getStringExtra("id");
        duration = getIntent().getStringExtra("duration");
        img_url = getIntent().getStringExtra("imgUrl");
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(this).load(img_url)
                .apply(options)
                .into(iv_tupian);
        good_title = getIntent().getStringExtra("name");
        tv_title.setText(good_title);
        good_price = getIntent().getStringExtra("price");
        tv_price.setText("￥" + good_price);
        tv_count.setText("x" + duration);
        yajin = getIntent().getStringExtra("yajin");
        tv_yajin.setText("￥" + yajin);
        peisongfei = getIntent().getStringExtra("peisongfei");
        tv_peisongfei.setText("￥" + peisongfei);
        float price = Float.parseFloat(good_price) * Integer.parseInt(duration)
                + Float.parseFloat(yajin) + Float.parseFloat(peisongfei);
        total_price = price + "";
        tv_total_price.setText("￥" + total_price);
        tv_total_price_bottom.setText("￥" + total_price);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initAddress();
    }

    private void initAddress() {
        ArrayMap arrayMap = new ArrayMap();
//        Toast.makeText(this, " " + SpUtils.getString(QueRenOrderActivity.this, "user_id", "")
//                , Toast.LENGTH_SHORT).show();
        arrayMap.put("user_id", SpUtils.getString(QueRenOrderActivity.this, "user_id", ""));
        arrayMap.put("token", MyUtils.getToken());
        arrayMap.put("is_default", "1");
        RetrofitManager.get(MyContants.BASEURL + "s=User/listExpress", arrayMap, new BaseObserver1<AddressBean>("") {
            @Override
            public void onSuccess(AddressBean result, String tag) {
                if (result.getCode() == 200) {
                    ll_hasAddress.setVisibility(View.VISIBLE);
                    rl_noaddress.setVisibility(View.GONE);
                    tv_name.setText(result.getDatas().get(0).getReceive_name());
                    tv_phone.setText(result.getDatas().get(0).getMobile());
                    tv_address.setText(result.getDatas().get(0).getArea_id()
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
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("pro_id", mId);
        arrayMap2.put("token", MyUtils.getToken());
        arrayMap2.put("user_id", SpUtils.getString(QueRenOrderActivity.this, "user_id", ""));
        arrayMap2.put("duration", duration);
        arrayMap2.put("express_id", express_id);
        arrayMap2.put("order_note", edt_liuyan.getText().toString());
        //        Toast.makeText(this, "id::" + mId + "   时长::" + duration, Toast.LENGTH_SHORT).show();
        RetrofitManager.post(MyContants.BASEURL + "s=Order/newOrder", arrayMap2, new BaseObserver1<OrderPushBean>("") {
            @Override
            public void onSuccess(OrderPushBean result, String tag) {
                if (result.getCode() == 200) {
//                    Toast.makeText(QueRenOrderActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    mOrder_id = result.getDatas().getOrder_id();
                    Toast.makeText(QueRenOrderActivity.this, " " + mOrder_id, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QueRenOrderActivity.this, OrderPayActivity.class);
                    intent.putExtra("orderid",mOrder_id);
                    startActivity(intent);
                } else {
                    //101是没有数据
                    Toast.makeText(QueRenOrderActivity.this, "提交失败" + result.getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }
}
