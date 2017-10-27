package com.example.administrator.lenglian.fragment.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
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
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;
import com.example.administrator.lenglian.fragment.order.adapter.Order_gride;
import com.example.administrator.lenglian.fragment.order.bean.Xiangqingbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyGradeview;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.PayUtil;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.iwgang.countdownview.CountdownView;

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
    private String order_id;
    private Xiangqingbean.DatasBean datas;
    private CountdownView countdownView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail);

        initView();
    //    inindata();
        //网络请求
        network();
    }

    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("order_id", order_id);
        RetrofitManager.get(MyContants.BASEURL+"s=Order/profileOrder", map, new BaseObserver1<Xiangqingbean>("") {



            @Override
            public void onSuccess(Xiangqingbean result, String tag) {
                if(result.getCode()==200){
                    //合计之后的价格=押金+配送费+商品价格x月份
                    //  total_price.setText("");
                    //加载数据
                    datas = result.getDatas();

                    detail_number.setText(datas.getOrder_num());
                    detail_name.setText(datas.getReceive_name());
                    detail_phone.setText(datas.getMobile());
                    detail_addres.setText(datas.getAddress_detail());
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
                            .into( shop_tupian );
                    //内容
                    shop_describe.setText(datas.getMain_title());
                    buy_price.setText("¥ "+ datas.getPro_price());
                    //z租期
                    buy_num.setText("x"+ datas.getDuration());
                    jie_yanjin.setText("¥ "+ datas.getPro_deposit());
                    delivery_cost.setText("¥ "+ datas.getExpress_money());
                    //合计
                    total_price.setText("¥ "+ datas.getOrder_price());
                    //发票
                    if(TextUtils.isEmpty(datas.getPersonal_name())&&TextUtils.isEmpty(datas.getCompany_name())&&TextUtils.isEmpty(datas.getIdentifier())){
                        ususally_invoice.setText("无发票");
                    }
                    else if(!TextUtils.isEmpty(datas.getPersonal_name())){
                        ususally_invoice.setText(datas.getPersonal_name());
                    }
                    else if(!TextUtils.isEmpty(datas.getCompany_name())){
                        ususally_invoice.setText(datas.getCompany_name());
                    }


                    //收货人
                    detail_name.setText(datas.getReceive_name());
                    //下单时间
                    order_data.setText(datas.getOrder_time());
                     /*
                  加载租了又租图片
                   */
                    List<Xiangqingbean.DatasBean.RentAgainBean> rent_again = datas.getRent_again();

                    Order_gride gradeadapter=new  Order_gride(OrderPayActivity.this, rent_again);
                    odetail_recy.setAdapter(gradeadapter);
                     //倒计时
                    long time1 = (long)datas.getExpired_time();
                    countdownView.start(time1);


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
        countdownView = (CountdownView) findViewById(R.id.cv_countdownView);
        tv_back.setOnClickListener(this);
        order_pause.setOnClickListener(this);
        //得到数据
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        order_zhifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.tv_back:
                  finish();
                  break;
              case R.id.order_zhifi:
                  //支付
                  PayUtil payUtil=new PayUtil(this,datas.getOrder_num());
                  payUtil.showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,this);
                  break;
              case R.id. order_pause:
                  //取消
                pausevoid();


                  break;
          }

    }
    public void pausevoid(){
          /*
       向服务器传送取消的订单
      */
        Map<String,String> map=new HashMap<>();
        map.put("order_id",  order_id);
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=Order/cancelOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200)  {
                    ToastUtils.showShort(OrderPayActivity.this,"取消成功");
                    finish();
                    //Eventbus发送告知刷新
                    EventMessage eventMessage = new EventMessage("fff");
                    EventBus.getDefault().postSticky(eventMessage);
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });

    }
}
