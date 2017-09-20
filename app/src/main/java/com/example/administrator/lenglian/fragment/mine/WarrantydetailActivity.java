package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.base.Ctiy;
import com.example.administrator.lenglian.fragment.mine.bean.BaoDetail;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
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
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waranty_details);
        initView();
         //加载网络请求
     //   network();
        ww();
    }

    private void ww() {
        final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String>  city2=new ArrayList<String>();
        final ArrayList<ArrayList<String>> lists=new ArrayList<>();
        RetrofitManager.get("http://restapi.amap.com/v3/config/district?key=14e56af2ef172af0123d877d8236027a&subdistrict=3", new BaseObserver1<Ctiy>("") {
            @Override
            public void onSuccess(Ctiy result, String tag) {
                List<Ctiy.DistrictsBeanXXX> districts = result.getDistricts();

                for (int i = 0; i <districts.size() ; i++) {
                    list.add(districts.get(i).getName());
                    List<Ctiy.DistrictsBeanXXX.DistrictsBeanXX> districts1 = result.getDistricts().get(i).getDistricts();
                    for (int j = 0; j <districts1.size() ; j++) {
                        city2.add(districts1.get(i).getName());

                    }

                }
                options2Items.add(list);
                lists.add(city2);
                KLog.d("tag",options2Items.toString());
            }

            @Override
            public void onFailed(int code) {

            }

        });
    }
    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("repair_ud",repair_ud);
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL +"s=Order/profileRepair", map, new BaseObserver1<BaoDetail>("") {
            @Override
            public void onSuccess(BaoDetail result, String tag) {
                    if(result.getCode()==200){


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
