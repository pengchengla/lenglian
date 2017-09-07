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
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class EvaluateActivity extends BaseActivity {
    private TextView tv_back;
    private RelativeLayout collect_title;
    private ListView list_evaluate;
    private List<Indexbean> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_evaluate);
        initView();
        inindata();
    }

    private void inindata() {
        Indexbean indexbean=new Indexbean();
        for (int i = 0; i < 5; i++) {
            indexbean.setCount("冷链冰柜你知道的太多了。你值得拥有还在等什么拿起电话加入吧");
            list.add(indexbean);
        }

        Evaiuateadapter evauateradapter=new Evaiuateadapter(this,list);
        list_evaluate.setAdapter(evauateradapter);
          list_evaluate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent it=new Intent(EvaluateActivity.this,EvaluatedetailActivity.class);
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
