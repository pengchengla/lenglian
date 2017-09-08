package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;
import com.example.administrator.lenglian.utils.MyGradeview;
import com.example.administrator.lenglian.view.MyGridView;
import com.example.administrator.lenglian.view.MyRatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class EvaluatedetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private RelativeLayout collect_title;
    private MyRatingBar ratingbar;
    private TextView evate_ping;
    private MyGradeview evate_grade;
    private TextView evate_zhuip;
    private Button zhuijia;
    private List<photobean> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_xiangq);
        initView();
        inindata();
    }

    private void inindata() {


        photobean ph=new photobean();
        ph.setPhoto(R.drawable.binggui);
        photobean ph2=new photobean();
        ph2.setPhoto(R.drawable.binggui);
        photobean ph3=new photobean();
        ph3.setPhoto(R.drawable.binggui);
        photobean pho4=new photobean();
        pho4.setPhoto(R.drawable.binggui);
        photobean pho5=new photobean();
        pho5.setPhoto(R.drawable.binggui);
        list.add(ph);
        list.add(ph2);
        list.add(ph3);
        list.add(pho4);
        list.add(pho5);
        Gradeadapter gradeadapter=new Gradeadapter(this,list);
        evate_grade.setAdapter(gradeadapter);
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        collect_title = (RelativeLayout) findViewById(R.id.collect_title);
        ratingbar = (MyRatingBar) findViewById(R.id.ratingbar);
        evate_ping = (TextView) findViewById(R.id.evate_ping);
        evate_grade = (MyGradeview) findViewById(R.id.evate_grade);
        evate_zhuip = (TextView) findViewById(R.id.evate_zhuip);
        zhuijia = (Button) findViewById(R.id.zhuijia);
        zhuijia.setOnClickListener(this);
        tv_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuijia:

                break;
            case R.id.tv_back:
                 finish();
                break;
        }
    }
}
