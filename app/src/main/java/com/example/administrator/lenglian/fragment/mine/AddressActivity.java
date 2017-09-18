package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Addressadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Addressbean;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddressActivity extends BaseActivity implements View.OnClickListener{

    private TextView tv_back;
    private TextView adress_add;
    private ListView list_address;
    private List<Indexbean> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_address);
        initView();
        //加载网络请求
        ininnetwork();
    }

    private void ininnetwork() {
        Map<String,String> map=new HashMap<>();
          map.put("user_id", SpUtils.getString(this,"user_id",""));//传过来的--------------
          map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL +"s=User/listExpress",map, new BaseObserver1<Addressbean>("") {
            @Override
            public void onSuccess(Addressbean result, String tag) {
                int code = result.getCode();
                if (code==200){
                    ToastUtils.showShort(AddressActivity.this,result.getSuccess());
                    List<Addressbean.DatasBean> datas = result.getDatas();
                    Addressadapter addressadapter=new Addressadapter(AddressActivity.this,datas);
                    list_address.setAdapter(addressadapter);


                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        adress_add = (TextView) findViewById(R.id.adress_add);
        list_address = (ListView) findViewById(R.id.list_address);
        tv_back.setOnClickListener(this);
        adress_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.adress_add:
                Intent it=new Intent(AddressActivity.this,AddaddressActivity.class);
                startActivity(it);
                break;
        }
    }


}
