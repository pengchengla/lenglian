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
import com.example.administrator.lenglian.fragment.mine.adapter.Alterationadapter;
import com.example.administrator.lenglian.fragment.mine.bean.AlterdetailActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.mine.bean.Returnbean;
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

public class AlterationActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ListView list_alteration;
    private List<Indexbean> list=new ArrayList<>();
    private List<Returnbean.DatasBean> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_alteration);
        initView();

        //加载网络请求
        initnetwork();
        inindata();

    }

    private void initnetwork() {
        Map<String,String> map=new HashMap<>();
         map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
         RetrofitManager.get(MyContants.BASEURL + "s=Order/listReturn", map, new BaseObserver1<Returnbean>("") {


             @Override
             public void onSuccess(Returnbean result, String tag) {
                   if(result.getCode()==200){
                       datas = result.getDatas();
                       //加载适配器
                       list_alteration.setAdapter(new Alterationadapter(AlterationActivity.this, datas));
                   }
             }

             @Override
             public void onFailed(int code) {

             }
         });
    }

    private void inindata() {
//        Indexbean index=new Indexbean();
//        for (int i = 0; i < 5; i++) {
//               index.setDuration("豪华的冰柜，你值得拥有。爱不释手无与伦比哈哈哈"+i);
//            list.add(index);
//        }



        list_alteration.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(AlterationActivity.this,AlterdetailActivity.class);
                it.putExtra("return_id",datas.get(position).getReturn_id());
                startActivity(it);
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        list_alteration = (ListView) findViewById(R.id.list_alteration);
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
