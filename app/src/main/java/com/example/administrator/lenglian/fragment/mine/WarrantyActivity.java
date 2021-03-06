package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.adapter.Warrantyadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Baoxiubean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private RelativeLayout linearLayout;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_warranty);

        initView();
        //注册eventbus
        EventBus.getDefault().register(this);
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
                else if(result.getCode()==101){
                            linearLayout.setVisibility(View.VISIBLE);
                            warranty_list.setVisibility(View.GONE);
                            textView.setText("我的报修    空空如也~");
                        }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(WarrantyActivity.this,code);
            }
        });
    }

    private void initdata() {
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
        linearLayout = (RelativeLayout) findViewById(R.id.kong);
        textView = (TextView) findViewById(R.id.kong_text);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("tijiao")) {
            network();
        }
        else if(eventMessage.getMsg().equals("zhuijia")){
            network();
        }
    }
}
