package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.PayUtil;
import com.example.administrator.lenglian.view.SnappingStepper;

import java.util.HashMap;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RenewActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private ImageView renew_tupian;
    private TextView renew_count;
    private TextView renew_price;
    private SnappingStepper renew_num;
    private TextView renew_money;
    private TextView renew_geshu;
    private TextView renew_total;
    private TextView renew_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_renew);
        initView();
        //加载网络请求
        network();
        initdata();
    }

    private void initdata() {


    }
    //网络请求
    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("order_id","");
        map.put("renewal_duration",renew_num.getValue()+"" );
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=Order/renewalOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {

            }

            @Override
            public void onFailed(int code) {

            }
        });




    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        renew_tupian = (ImageView) findViewById(R.id.renew_tupian);
        renew_count = (TextView) findViewById(R.id.renew_count);
        renew_price = (TextView) findViewById(R.id.renew_price);
        renew_num = (SnappingStepper) findViewById(R.id.renew_num);
        renew_money = (TextView) findViewById(R.id.renew_money);
        renew_geshu = (TextView) findViewById(R.id.renew_geshu);
        renew_total = (TextView) findViewById(R.id.renew_total);
        renew_btn = (TextView) findViewById(R.id.renew_btn);
        tv_back.setOnClickListener(this);
        renew_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.renew_btn:
                PayUtil.showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,this);

                break;
        }
    }

}
