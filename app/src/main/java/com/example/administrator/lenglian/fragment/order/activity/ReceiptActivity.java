package com.example.administrator.lenglian.fragment.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ReceiptActivity extends BaseActivity implements View.OnClickListener {
    private ImageView fan;
    private RelativeLayout tv_back;
    private TextView peisong_number;
    private TextView distribution_state;
    private TextView consignee_person;
    private TextView consign_num;
    private TextView consignee_address;
    private ImageView distribution_tupian;
    private TextView distribution_miaoshu;
    private TextView distribution_price;
    private TextView jie_yanjin;
    private TextView distribution_cost;
    private TextView distribution_zprice;
    private TextView ususally_invoice;
    private TextView distribution_data;
    private TextView distribution_songdadata;
    private TextView distribution_paymentmethod;
    private TextView distribution_sum;
    private Button btn_receiving;
    private String order_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_distribution);
        initView();
        //网络请求
        network();
    }

    private void network() {
        Map<String,String> map=new HashMap<>();
          map.put("user_id", SpUtils.getString(this,"user_id",""));
          map.put("token", MyUtils.getToken());
          map.put("order_id",order_id);
        RetrofitManager.get(MyContants.BASEURL + "", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){

                    //加载数据
                    peisong_number.setText("");//订单号
                    distribution_state.setText("");//订单状态
                    consignee_person.setText("");//收货人





                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void initView() {
        fan = (ImageView) findViewById(R.id.fan);
        tv_back = (RelativeLayout) findViewById(R.id.tv_back);
        peisong_number = (TextView) findViewById(R.id.peisong_number);
        distribution_state = (TextView) findViewById(R.id.distribution_state);
        consignee_person = (TextView) findViewById(R.id.consignee_person);
        consign_num = (TextView) findViewById(R.id.consign_num);
        consignee_address = (TextView) findViewById(R.id.consignee_address);
        distribution_tupian = (ImageView) findViewById(R.id.distribution_tupian);
        distribution_miaoshu = (TextView) findViewById(R.id.distribution_miaoshu);
        distribution_price = (TextView) findViewById(R.id.distribution_price);
        jie_yanjin = (TextView) findViewById(R.id.jie_yanjin);
        distribution_cost = (TextView) findViewById(R.id.distribution_cost);
        distribution_zprice = (TextView) findViewById(R.id.distribution_zprice);
        ususally_invoice = (TextView) findViewById(R.id.ususally_invoice);
        distribution_data = (TextView) findViewById(R.id.distribution_data);
        distribution_songdadata = (TextView) findViewById(R.id.distribution_songdadata);
        distribution_paymentmethod = (TextView) findViewById(R.id.distribution_paymentmethod);
        distribution_sum = (TextView) findViewById(R.id.distribution_sum);
        btn_receiving = (Button) findViewById(R.id.btn_receiving);

        btn_receiving.setOnClickListener(this);
        tv_back.setOnClickListener(this);
         //得到数据
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_receiving:

                break;
            case R.id. tv_back:
                finish();
                break;
        }
    }
}
