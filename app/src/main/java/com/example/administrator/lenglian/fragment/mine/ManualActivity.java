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
import com.example.administrator.lenglian.fragment.order.adapter.Useradapter;
import com.example.administrator.lenglian.fragment.order.bean.Userbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ManualActivity extends BaseActivity {
    private TextView tv_back;
    private TextView dinggou;
    private RelativeLayout about_ding;
    private RelativeLayout shservice;
    private ListView list_user;
    private List<Userbean.DatasBean> datas;
    private Useradapter useradapter;
    private TextView tv_back1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_guideline);
        initView();
        network();
         data();
    }
    /*
      listview点击事件

     */

    private void data() {
         list_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent it=new Intent(ManualActivity.this,UserDetailActivity.class);
                 it.putExtra("guide_id",datas.get(position).getGuide_id());
                 startActivity(it);
             }
         });
   tv_back.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           finish();
       }
   });
    }

    private void network() {
        RetrofitManager.get(MyContants.BASEURL +"s=Guide/listGuide", new BaseObserver1<Userbean>("") {
            @Override
            public void onSuccess(Userbean result, String tag) {
                if(result.getCode()==200){
                    datas = result.getDatas();
                    useradapter = new Useradapter(ManualActivity.this, datas);
                    list_user.setAdapter(useradapter);


                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void initView() {
        list_user = (ListView) findViewById(R.id.list_user);
        tv_back1 = (TextView) findViewById(R.id.tv_back);

    }

}
