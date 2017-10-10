package com.example.administrator.lenglian.fragment.mine.bean;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AlterdetailActivity extends BaseActivity {
    private TextView tv_back;
    private ImageView alterdetail_tupian;
    private TextView alterdetail_count;
    private TextView alterdetail_price;
    private TextView alterdetail_zhuantai;
    private TextView alter_name;
    private TextView alter_phone;
    private TextView alter_address;
    private TextView alter_dingdan;
    private TextView dingdan;
    private TextView alter_time;
    private TextView alter_timeok;
    private String return_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alteration_xiangq);
        initView();
        //加载网络
        network();
    }

    private void network() {
        //
        Map<String,String> map=new HashMap<>();
        map.put(" return_id", return_id);
        map.put("token",MyContants.BASEURL);
        RetrofitManager.get(MyContants.BASEURL +"s=Order/profileReturn", map, new BaseObserver1<Returntailbean>("") {
            @Override
            public void onSuccess(Returntailbean result, String tag) {
                if(result.getCode()==200){
                    List<Returntailbean.DatasBean> datas = result.getDatas();
                    //添加数据
                    /*
                     photo
                     */
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .error(R.drawable.default_square)
                            .priority(Priority.NORMAL)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                    Glide.with(AlterdetailActivity.this).load(datas.get(0).getPro_pic().get(0).getUrl())
                            .apply(options)
                            .into(alterdetail_tupian);
                    alterdetail_count.setText(datas.get(0).getMain_title());
                    alterdetail_price.setText(datas.get(0).getPro_price());
                    if(datas.get(0).getReturn_status().equals("1")){
                        alterdetail_zhuantai.setText("待支付");

                    }
                    else if(datas.get(0).getReturn_status().equals("2")){
                        alterdetail_zhuantai.setText("待配送");

                    }
                    else if(datas.get(0).getReturn_status().equals("3")){
                        alterdetail_zhuantai.setText("配送中");

                    }
                    else if(datas.get(0).getReturn_status().equals("8")){
                        alterdetail_zhuantai.setText("待取回");

                    }
                    else if(datas.get(0).getReturn_status().equals("9")){
                        alterdetail_zhuantai.setText("取回中");
                    }
                    alter_name.setText(datas.get(0).getContact_name());
                    alter_phone.setText(datas.get(0).getContact_mobile());
                    alter_address.setText(datas.get(0).getReturn_address());
                    alter_dingdan.setText(datas.get(0).getReturn_id());
                    dingdan.setText(datas.get(0).getOrder_num());
                    alter_time.setText(datas.get(0).getCommit_time());
                    alter_timeok.setText(datas.get(0).getOk_time());

                }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(AlterdetailActivity.this,code+"");
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        alterdetail_tupian = (ImageView) findViewById(R.id.alterdetail_tupian);
        alterdetail_count = (TextView) findViewById(R.id.alterdetail_count);
        alterdetail_price = (TextView) findViewById(R.id.alterdetail_price);
        alterdetail_zhuantai = (TextView) findViewById(R.id.alterdetail_zhuantai);
        alter_name = (TextView) findViewById(R.id.alter_name);
        alter_phone = (TextView) findViewById(R.id.alter_phone);
        alter_address = (TextView) findViewById(R.id.alter_address);
        alter_dingdan = (TextView) findViewById(R.id.alter_dingdan);
        dingdan = (TextView) findViewById(R.id.dingdan);
        alter_time = (TextView) findViewById(R.id.alter_time);
        alter_timeok = (TextView) findViewById(R.id.alter_timeok);
        //得到数据
        Intent intent = getIntent();
        return_id = intent.getStringExtra("return_id");
        tv_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }
}
