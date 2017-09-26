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
import com.example.administrator.lenglian.fragment.mine.adapter.Evaiuateadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Evaluatebean;
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

public class EvaluateActivity extends BaseActivity {
    private TextView tv_back;
    private RelativeLayout collect_title;
    private ListView list_evaluate;
    private List<Indexbean> list=new ArrayList<>();
    private List<Evaluatebean.DatasBean> datas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_evaluate);
        initView();
        inindata();
        //加载网络请求
        inintwork();
    }

    private void inintwork() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));//接口传过来
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=User/listComment",map,new BaseObserver1<Evaluatebean>("") {



            @Override
            public void onSuccess(Evaluatebean result, String tag) {
             /*
                加载网络请求数据
              */
                int code = result.getCode();
                if(code==200){
                    datas = result.getDatas();
                    Evaiuateadapter evauateradapter=new Evaiuateadapter(EvaluateActivity.this,datas);
                    list_evaluate.setAdapter(evauateradapter);
                }

            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void inindata() {
//        Indexbean indexbean=new Indexbean();
//        for (int i = 0; i < 5; i++) {
//            indexbean.setDuration("冷链冰柜你知道的太多了。你值得拥有还在等什么拿起电话加入吧");
//            list.add(indexbean);
//        }
//
//        Evaiuateadapter evauateradapter=new Evaiuateadapter(this,list);
//        list_evaluate.setAdapter(evauateradapter);
          list_evaluate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent it=new Intent(EvaluateActivity.this,EvaluatedetailActivity.class);
                  it.putExtra("comment_id",datas.get(position).getComment_id());
                  String pro_pic = datas.get(position).getPro_pic();
                  it.putExtra("image", pro_pic);
                  startActivity(it);
              }
          });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        collect_title = (RelativeLayout) findViewById(R.id.collect_title);
        list_evaluate = (ListView) findViewById(R.id.list_evaluate);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
