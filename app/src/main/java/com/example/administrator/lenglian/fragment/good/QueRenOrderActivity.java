package com.example.administrator.lenglian.fragment.good;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import com.example.administrator.lenglian.db.LitePalHelper;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.fragment.mine.GoPayActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.view.CustomProgressDialog;


public class QueRenOrderActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back, tv_tijiao, tv_name, tv_phone,
            tv_address, tv_title, tv_price, tv_count, tv_yajin,
            tv_peisongfei, tv_total_price, tv_total_price_bottom;
    private ImageView iv_address, iv_tupian;
    private String mId, duration;
    private EditText edt_liuyan, edt_fapiao1, edt_fapiao2;
    private LinearLayout ll_hasAddress;
    private RelativeLayout rl_noaddress;
    private String express_id;
    private String img_url;
    private String good_title;
    private String good_price;
    private String yajin;
    private String peisongfei;
    private String total_price;
    private ProgressDialog mDialog;
    private RadioGroup rgp;
    private int fapiao = 0;
    private TextView tv_fapiao_no;
    private ImageView iv_fapiao_no;
    private LinearLayout ll_root_view;
    private boolean isOpen;
    private boolean isHaveAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_que_ren_order);
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
        rgp = (RadioGroup) findViewById(R.id.rgp);
        edt_fapiao1 = (EditText) findViewById(R.id.edt_fapiao1);
        edt_fapiao2 = (EditText) findViewById(R.id.edt_fapiao2);
        tv_fapiao_no = (TextView) findViewById(R.id.tv_fapiao_no);
        tv_fapiao_no.setOnClickListener(this);
        iv_fapiao_no = (ImageView) findViewById(R.id.iv_fapiao_no);
        iv_fapiao_no.setOnClickListener(this);
        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view);
        initAddress();
        initData();
    }

    private void initData() {
        mDialog = new CustomProgressDialog(this, R.style.myprogressdialog);
        mDialog.show();
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
        tv_tijiao.setClickable(true);
        rgp.check(R.id.rb_fapiao_no);
        edt_fapiao1.setVisibility(View.GONE);
        edt_fapiao2.setVisibility(View.GONE);
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_fapiao_no:
                        fapiao = 0;
                        break;
                    case R.id.rb_fapiao_geren:
                        fapiao = 1;
                        break;
                    case R.id.rb_fapiao_danwei:
                        fapiao = 2;
                        break;
                }
                refreshFapiao();
            }
        });
    }

    private void refreshFapiao() {
        if (fapiao == 0) {
            edt_fapiao1.setVisibility(View.GONE);
            edt_fapiao2.setVisibility(View.GONE);
            tv_fapiao_no.setText("不开发票");
        } else if (fapiao == 1) {
            edt_fapiao1.setVisibility(View.VISIBLE);
            edt_fapiao1.setHint("请填写收票人姓名");
            edt_fapiao2.setVisibility(View.GONE);
            tv_fapiao_no.setText("个人");
        } else if (fapiao == 2) {
            edt_fapiao1.setVisibility(View.VISIBLE);
            edt_fapiao1.setHint("请填写单位名称");
            edt_fapiao2.setVisibility(View.VISIBLE);
            tv_fapiao_no.setText("单位");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tv_tijiao.setClickable(true);
        isHaveAddress = false;//每次回来都刷新一下
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
                    isHaveAddress = true;
                } else {
                    //101是没有数据
                    ll_hasAddress.setVisibility(View.GONE);
                    rl_noaddress.setVisibility(View.VISIBLE);
                    isHaveAddress = false;
                }
                mDialog.dismiss();
            }

            @Override
            public void onFailed(int code) {
                mDialog.dismiss();
                isHaveAddress = false;
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
            case R.id.tv_fapiao_no:
            case R.id.iv_fapiao_no:
                ll_root_view.setVisibility(ll_root_view.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                if (isOpen) {
                    iv_fapiao_no.setImageResource(R.drawable.arrow_right);
                } else {
                    iv_fapiao_no.setImageResource(R.drawable.arrow_bottom);
                }
                isOpen = !isOpen;
                break;

        }
    }

    private void pushOrder() {
        if (!isHaveAddress) {
            Toast.makeText(this, "请先设置收货地址~", Toast.LENGTH_SHORT).show();
            return;
        }
        tv_tijiao.setClickable(false);
        mDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("pro_id", mId);
                arrayMap2.put("token", MyUtils.getToken());
                arrayMap2.put("user_id", SpUtils.getString(QueRenOrderActivity.this, "user_id", ""));
                arrayMap2.put("duration", duration);
                arrayMap2.put("express_id", express_id);
                arrayMap2.put("order_note", edt_liuyan.getText().toString());
                if (fapiao == 1) {
                    arrayMap2.put("personal_name", edt_fapiao1.getText().toString());
                } else if (fapiao == 2) {
                    arrayMap2.put("company_name", edt_fapiao1.getText().toString());
                    arrayMap2.put("identifier", edt_fapiao2.getText().toString());
                }
                //        Toast.makeText(this, "id::" + mId + "   时长::" + duration, Toast.LENGTH_SHORT).show();
                RetrofitManager.post(MyContants.BASEURL + "s=Order/newOrder", arrayMap2, new BaseObserver1<OrderPushBean>("") {
                    @Override
                    public void onSuccess(OrderPushBean result, String tag) {
                        if (result.getCode() == 200) {
                            //                    Toast.makeText(QueRenOrderActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            //                    Toast.makeText(QueRenOrderActivity.this, " " + mOrder_id, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(QueRenOrderActivity.this, GoPayActivity.class);
                            intent.putExtra("order_num", result.getDatas().getOrder_num());
                            startActivity(intent);
                            finish();
                            LitePalHelper.deleteOne(mId);//数据库中删除这个商品
                            mDialog.dismiss();
                        } else {
                            mDialog.dismiss();
                            //101是没有数据
                            Toast.makeText(QueRenOrderActivity.this, "提交失败" + result.getError(), Toast.LENGTH_SHORT).show();
                            tv_tijiao.setClickable(true);
                        }
                    }

                    @Override
                    public void onFailed(int code) {
                        Toast.makeText(QueRenOrderActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                        tv_tijiao.setClickable(true);
                    }
                });
            }
        }, 1000);
    }
}
