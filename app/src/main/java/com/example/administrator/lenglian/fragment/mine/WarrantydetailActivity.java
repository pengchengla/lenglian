package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.BaoDetail;
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

public class WarrantydetailActivity extends BaseActivity {

    private TextView tv_back;
    private ImageView waranty_tupian;
    private TextView waranty_count;
    private TextView waranty_name;
    private TextView waranty_phone;
    private TextView waranty_address;
    private TextView waranty_evolve;
    private String repair_ud;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waranty_details);
        initView();
         //加载网络请求
        network();
    }

    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("repair_id",repair_ud);
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL+"s=Order/profileRepair", map, new BaseObserver1<BaoDetail>("") {
            @Override
            public void onSuccess(BaoDetail result, String tag) {
                    if(result.getCode()==200){
                        List<BaoDetail.DatasBean> datas = result.getDatas();
                        //加载数据
                        /*
                          加载图片
                         */
                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .error(R.drawable.default_square)
                                .priority(Priority.NORMAL)
                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                        Glide.with(WarrantydetailActivity.this).load(datas.get(0).getPro_pic().get(0).getUrl())
                                .apply(options)
                                .into(waranty_tupian);

                        waranty_count.setText(datas.get(0).getMain_title());
                        waranty_name.setText(datas.get(0).getContact_name());
                        waranty_phone.setText(datas.get(0).getContact_mobile());
                        waranty_address.setText(datas.get(0).getRepair_address());
                        waranty_evolve.setText(datas.get(0).getRepair_note());

                    }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        waranty_tupian = (ImageView) findViewById(R.id.waranty_tupian);
        waranty_count = (TextView) findViewById(R.id.waranty_count);
        waranty_name = (TextView) findViewById(R.id.waranty_name);
        waranty_phone = (TextView) findViewById(R.id.waranty_phone);
        waranty_address = (TextView) findViewById(R.id.waranty_address);
        waranty_evolve = (TextView) findViewById(R.id.waranty_evolve);
        //得到数据
        Intent intent = getIntent();
        repair_ud = intent.getStringExtra("repair_ud");
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
