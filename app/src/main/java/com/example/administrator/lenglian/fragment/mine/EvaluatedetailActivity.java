package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Evaluatedetailbean;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyGradeview;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.view.MyGridView;
import com.example.administrator.lenglian.view.MyRatingBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String comment_id;
    private ImageView evaluate_img;
    private String image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_xiangq);
        initView();
        inindata();
        //加载网络请求
        ininnetwork();

    }

    private void ininnetwork() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));//传过来的--------
        map.put("token", MyUtils.getToken());
        map.put("comment_id",comment_id);
        RetrofitManager.get(MyContants.BASEURL + "s=User/profileComment", map,new BaseObserver1<Evaluatedetailbean>("") {

            private int i;
            /*
              网络请求 ------------------------
           */
            @Override
            public void onSuccess(Evaluatedetailbean result, String tag) {
                int code = result.getCode();
                 if(code==200){
                     Evaluatedetailbean.DatasBean datas = result.getDatas();

                    Glide.with(EvaluatedetailActivity.this)
                            .load(image).into(evaluate_img);
                     evate_ping.setText(datas.getContent());
                     ratingbar.setClickable(false);//设置可否点击
                     String pro_score = datas.getPro_score();
                     try {
                         i = Integer.parseInt(pro_score);
                     } catch (NumberFormatException e) {
                         e.printStackTrace();
                     }
                     ratingbar.setStar(i);//设置显示的星星个数
                     evate_zhuip.setText(datas.getAdd_content());
                     List<Evaluatedetailbean.DatasBean.PicBean> pic = datas.getPic();
//                     Gradeadapter gradeadapter=new Gradeadapter(this,pic);
//                     evate_grade.setAdapter(gradeadapter);


                 }
            }

            @Override
            public void onFailed(int code) {

            }
        });


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
        evaluate_img = (ImageView) findViewById(R.id.evaluate_img);
        zhuijia.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        Intent intent = getIntent();
        comment_id = intent.getStringExtra("comment_id");
        image = intent.getStringExtra("image");
       Intent s= getIntent();
        String count = s.getStringExtra("count");
        evate_zhuip.setText(count);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuijia:
                 //跳转
                Intent intent=new Intent(EvaluatedetailActivity.this,ZhijiaActivity.class);
                 intent.putExtra("comment_id", comment_id);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_back:
                 finish();
                break;
        }
    }
}
