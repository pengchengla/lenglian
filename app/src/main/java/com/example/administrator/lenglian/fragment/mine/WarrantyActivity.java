package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Warrantyadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Baoxiubean;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class WarrantyActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ListView warranty_list;
    private List<Baoxiubean.DatasBean> datas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_warranty);
        initView();
        //加载网络请求
        network();
        initdata();

    }
    //网络请求数据
    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL +"s=Order/listRepair", map, new BaseObserver1<Baoxiubean>("") {



            @Override
            public void onSuccess(Baoxiubean result, String tag) {
                        if(result.getCode()==200){
                            //加载数据
                            datas = result.getDatas();
                            Warrantyadapter warrantyadapter = new Warrantyadapter(WarrantyActivity.this, datas);
                            warranty_list.setAdapter(warrantyadapter);


                        }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void initdata() {
//          Indexbean indexbean=new Indexbean();
//        for (int i = 0; i < 5; i++) {
//            indexbean.setDuration("阿拉阿发发发 阿达阿达啊大大啊大大啊啊啊阿达"+i);
//            list.add(indexbean);
//        }
//
//        Warrantyadapter warrantyadapter = new Warrantyadapter(this,list);
//        warranty_list.setAdapter(warrantyadapter);
        //listview点击
        warranty_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(WarrantyActivity.this,WarrantydetailActivity.class);
                 it.putExtra("repair_ud",datas.get(position).getRepair_id());
                startActivity(it);
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        warranty_list = (ListView) findViewById(R.id.warranty_list);
        tv_back.setOnClickListener(this);
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
