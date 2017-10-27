package com.example.administrator.lenglian.fragment.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.MyApplication;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.order.bean.Detailbean;
import com.example.administrator.lenglian.fragment.order.bean.Xiangqingbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AppraiseActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fan;
    private RelativeLayout tv_back;
    private TextView details_number;
    private TextView details_person;
    private TextView details_num;
    private TextView details_address;
    private ImageView details_tupian;
    private TextView details_miaoshu;
    private TextView details_price;
    private TextView jie_yanjin;
    private TextView details_cost;
    private TextView details_zprice;
    private TextView details_invoice;
    private TextView details_data;
    private TextView details_songdadata;
    private TextView distribution_paymentmethod;
    private TextView distribution_sum;
    private String order_id;
    private TextView distribution_state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        initView();
        network();
    }

    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("order_id",order_id);
        RetrofitManager.get(MyContants.BASEURL + "s=Order/profileOrder", map, new BaseObserver1<Xiangqingbean>("") {
            
            @Override
            public void onSuccess(Xiangqingbean result, String tag) {
            if(result.getCode()==200) {
                Xiangqingbean.DatasBean datas = result.getDatas();
                //加载数据
                details_number.setText(datas.getOrder_num());
                //收货人
                details_person.setText(datas.getReceive_name());
                //电话

                details_num.setText(datas.getMobile());
                details_address.setText(datas.getAddress_detail());
                 /*
                  加载图片
                   */
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.default_square)
                        .priority(Priority.NORMAL)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                Glide.with(MyApplication.getApplication()).load(datas.getSingle_pic())
                        .apply(options)
                        .into(details_tupian  );
                //内容
                details_miaoshu .setText(datas.getMain_title());
                details_price .setText("¥ "+datas.getPro_price());
                jie_yanjin.setText("¥ "+datas.getPro_deposit());
                details_cost.setText("¥ "+datas.getExpress_money());
                //合计
                details_zprice.setText("¥ "+datas.getOrder_price());
                //发票
                details_invoice.setText("");
                //发票
                if(TextUtils.isEmpty(datas.getPersonal_name())&&TextUtils.isEmpty(datas.getCompany_name())&&TextUtils.isEmpty(datas.getIdentifier())){
                    details_invoice.setText("无发票");
                }
                else if(!TextUtils.isEmpty(datas.getPersonal_name())){
                    details_invoice.setText(datas.getPersonal_name());
                }
                else if(!TextUtils.isEmpty(datas.getCompany_name())){
                    details_invoice.setText(datas.getCompany_name());
                }
                //下单时间
                details_data.setText(datas.getOrder_time());
                details_songdadata.setText("¥ "+datas.getExpress_money());
                distribution_sum.setText("¥ "+datas.getOrder_price());
                // details_person.setText(datas.get(0).);
                    /*
                      合计=押金+配送费+商品价格
                     */
                String order_status = datas.getOrder_status();
                if("5".equals(order_status)){
                    distribution_state.setText("待激活");
                }
                else if("6".equals(order_status)){
                    distribution_state.setText("已激活");
                }
                else if("7".equals(order_status)){
                    distribution_state.setText("待维修");
                }
                else if("8".equals(order_status)){
                    distribution_state.setText("维修中");
                }
                else if("9".equals(order_status)){
                    distribution_state.setText("待取回");
                }
                else if("10".equals(order_status)){
                    distribution_state.setText("取回中");
                }
                else if("11".equals(order_status)){
                    distribution_state.setText("已取消");
                }
                else if("12".equals(order_status)){
                    distribution_state.setText("已完成");
                }


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
        details_number = (TextView) findViewById(R.id.details_number);
        details_person = (TextView) findViewById(R.id.details_person);
        details_num = (TextView) findViewById(R.id.details_num);
        details_address = (TextView) findViewById(R.id.details_address);
        details_tupian = (ImageView) findViewById(R.id.details_tupian);
        details_miaoshu = (TextView) findViewById(R.id.details_miaoshu);
        details_price = (TextView) findViewById(R.id.details_price);
        jie_yanjin = (TextView) findViewById(R.id.jie_yanjin);
        details_cost = (TextView) findViewById(R.id.details_cost);
        details_zprice = (TextView) findViewById(R.id.details_zprice);
        details_invoice = (TextView) findViewById(R.id.details_invoice);
        details_data = (TextView) findViewById(R.id.details_data);
        details_songdadata = (TextView) findViewById(R.id.details_songdadata);
        distribution_paymentmethod = (TextView) findViewById(R.id.distribution_paymentmethod);
        distribution_sum = (TextView) findViewById(R.id.distribution_sum);
        distribution_state = (TextView) findViewById(R.id.distribution_state);
        tv_back.setOnClickListener(this);
        //得到数据
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
        }

    }
}
