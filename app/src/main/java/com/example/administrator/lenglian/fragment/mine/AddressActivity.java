package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.adapter.Addressadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Addressbean;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private SpringView springView;
    private List<Addressbean.DatasBean> datas;
    private Addressadapter addressadapter;
    private TextView kong_text;
    private RelativeLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_address);
        initView();
        //加载网络请求
        ininnetwork();
         //设置类型
        springView.setType(SpringView.Type.FOLLOW);
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
                    linearLayout.setVisibility(View.GONE);
                    list_address.setVisibility(View.VISIBLE);
                    datas = result.getDatas();
                    addressadapter = new Addressadapter(AddressActivity.this, datas);
                    list_address.setAdapter(addressadapter);


                }
                else  if(code==101){
                    kong_text.setText("我的地址    空空如也~");
                    linearLayout.setVisibility(View.VISIBLE);
                    list_address.setVisibility(View.GONE);
                    addressadapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailed(int code) {
            ToastUtils.showShort(AddressActivity.this,"网络失败,请检查网络");
            }
        });
    }

    @Override
    protected void onDestroy() {
        finish();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("ddd")) {
            ddd();
        }
        else if(eventMessage.getMsg().equals("add")){
            ininnetwork();
        }
    }
     /*
       调用eventbus方法
      */
public void ddd(){
    //加载网络请求
    ininnetwork();

}
    private void initView() {
        //注册
        EventBus.getDefault().register(this);
        tv_back = (TextView) findViewById(R.id.tv_back);
        adress_add = (TextView) findViewById(R.id.adress_add);
        list_address = (ListView) findViewById(R.id.list_address);
        springView = (SpringView) findViewById(R.id.springview);
        kong_text = (TextView) findViewById(R.id.kong_text);
        linearLayout = (RelativeLayout) findViewById(R.id.kong);
        tv_back.setOnClickListener(this);
        adress_add.setOnClickListener(this);
         /*
           刷新数据
          */
           springView.setListener(new SpringView.OnFreshListener() {
               @Override
               public void onRefresh() {
                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           ininnetwork();
                           springView.onFinishFreshAndLoad();
                       }
                   }, 1000);
               }

               @Override
               public void onLoadmore() {
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         addressadapter.notifyDataSetChanged();
                         springView.onFinishFreshAndLoad();
                     }
                 }, 1000);
               }


           });
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultHeader(this));
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
