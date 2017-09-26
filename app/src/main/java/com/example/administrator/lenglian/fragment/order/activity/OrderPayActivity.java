package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;
import com.example.administrator.lenglian.fragment.order.adapter.Order_gride;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyGradeview;
import com.example.administrator.lenglian.utils.PayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class OrderPayActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout tv_back;
    private TextView detail_number;
    private TextView detail_name;
    private TextView detail_phone;
    private TextView detail_addres;
    private ImageView shop_tupian;
    private TextView shop_describe;
    private TextView buy_price;
    private TextView buy_num;
    private TextView jie_yanjin;
    private TextView delivery_cost;
    private TextView total_price;
    private TextView ususally_invoice;
    private TextView order_data;
    private MyGradeview odetail_recy;
    private TextView order_pause;
    private TextView order_zhifi;
    private List<photobean> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);
        initView();
        inindata();
        //网络请求
        network();
    }

    private void network() {
        Map<String,String> map=new HashMap<>();
        RetrofitManager.post(MyContants.BASEURL + "", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                  if(result.getCode()==200){


                     //合计之后的价格=押金+配送费+商品价格x月份
                    //  total_price.setText("");
                }

            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void inindata() {
        photobean ph=new photobean();
        ph.setPhoto(R.drawable.binggui);
        photobean ph2=new photobean();
        ph2.setPhoto(R.drawable.binggui);
        photobean ph3=new photobean();
        ph3.setPhoto(R.drawable.binggui);
        photobean pho4=new photobean();
        pho4.setPhoto(R.drawable.binggui);
        photobean pho5=new photobean();
        pho5.setPhoto(R.drawable.binggui);
        list.add(ph);
        list.add(ph2);
        list.add(ph3);
        list.add(pho4);
        list.add(pho5);
        Order_gride gradeadapter=new  Order_gride(this,list);
        odetail_recy.setAdapter(gradeadapter);

    }

    private void initView() {
        tv_back = (RelativeLayout) findViewById(R.id.tv_back);
        detail_number = (TextView) findViewById(R.id.detail_number);
        detail_name = (TextView) findViewById(R.id.detail_name);
        detail_phone = (TextView) findViewById(R.id.detail_phone);
        detail_addres = (TextView) findViewById(R.id.detail_addres);
        shop_tupian = (ImageView) findViewById(R.id.shop_tupian);
        shop_describe = (TextView) findViewById(R.id.shop_describe);
        buy_price = (TextView) findViewById(R.id.buy_price);
        buy_num = (TextView) findViewById(R.id.buy_num);
        jie_yanjin = (TextView) findViewById(R.id.jie_yanjin);
        delivery_cost = (TextView) findViewById(R.id.delivery_cost);
        total_price = (TextView) findViewById(R.id.total_price);
        ususally_invoice = (TextView) findViewById(R.id.ususally_invoice);
        order_data = (TextView) findViewById(R.id.order_data);
        odetail_recy = (MyGradeview) findViewById(R.id.odetail_recy);
        order_pause = (TextView) findViewById(R.id.order_pause);
        order_zhifi = (TextView) findViewById(R.id.order_zhifi);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.tv_back:
                  finish();
                  break;
              case R.id.order_zhifi:
                  //支付
                  PayUtil.showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,OrderPayActivity.this);
                  break;
              case R.id. order_pause:
                  //取消

                  break;
          }

    }
}
