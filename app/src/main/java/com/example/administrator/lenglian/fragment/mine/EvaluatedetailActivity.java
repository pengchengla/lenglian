package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.Evaluatedetailbean;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyGradeview;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.view.MyRatingBar;

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
    private LinearLayout liner_zhuijia;
    private View liner_zhui;
    private Evaluatedetailbean.DatasBean datas;
    private String order_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_xiangq);
        initView();
        inindata();
        //加载网络请求
        ininnetwork();

    }

    private void inindata() {

    }

    private void ininnetwork() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id",SpUtils.getString(this,"user_id",""));//传过来的--------
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
                     datas = result.getDatas();

                    /*
          加载图片
         */
                     RequestOptions options=new RequestOptions()
                             .error(R.drawable.default_square)
                             .priority(Priority.NORMAL)
                             .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                     Glide.with(EvaluatedetailActivity.this).load(image)
                             .apply(options)
                             .into( evaluate_img);
                         if("2".equals(datas.getAdd_status())){
                             liner_zhuijia.setVisibility(View.VISIBLE);
                             liner_zhui.setVisibility(View.VISIBLE);
                             evate_ping.setText(datas.getContent());
                         }
                     else {
                             liner_zhuijia.setVisibility(View.GONE);
                             liner_zhui.setVisibility(View.GONE);
                         }

                     ratingbar.setClickable(false);//设置可否点击
                     String pro_score = datas.getPro_score();
                     try {
                         i = Integer.parseInt(pro_score);
                     } catch (NumberFormatException e) {
                         e.printStackTrace();
                     }
                     ratingbar.setStar(i);//设置显示的星星个数
                     evate_zhuip.setText(datas.getAdd_content());
                     evate_ping.setText(datas.getContent());
                     List<Evaluatedetailbean.DatasBean.PicBean> pic = datas.getPic();
                    Gradeadapter gradeadapter=new Gradeadapter(EvaluatedetailActivity.this,pic);
                  evate_grade.setAdapter(gradeadapter);


                 }
            }

            @Override
            public void onFailed(int code) {

            }
        });


    }


    private void initView() {
        //注册EventBus
        EventBus.getDefault().register(this);
        tv_back = (TextView) findViewById(R.id.tv_back);
        collect_title = (RelativeLayout) findViewById(R.id.collect_title);
        ratingbar = (MyRatingBar) findViewById(R.id.ratingbar);
        evate_ping = (TextView) findViewById(R.id.evate_ping);
        evate_grade = (MyGradeview) findViewById(R.id.evate_grade);
        evate_zhuip = (TextView) findViewById(R.id.evate_zhuip);
        zhuijia = (Button) findViewById(R.id.zhuijia);
        evaluate_img = (ImageView) findViewById(R.id.evaluate_img);
        liner_zhuijia = (LinearLayout) findViewById(R.id.liner_zhuijia);
        liner_zhui = (View)findViewById(R.id.liner_zhui);
        zhuijia.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        Intent intent = getIntent();
        comment_id = intent.getStringExtra("comment_id");
        image = intent.getStringExtra("image");
        order_id = intent.getStringExtra("order_id");
        Intent s= getIntent();
        String count = s.getStringExtra("count");
        evate_zhuip.setText(count);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("zhuijia")) {
            zzz();
        }
    }

    private void zzz() {
          //获取数据
        String count = SpUtils.getString(EvaluatedetailActivity.this, "count", "");
        liner_zhuijia.setVisibility(View.VISIBLE);
        liner_zhui.setVisibility(View.VISIBLE);
        evate_zhuip.setText(count);
        ininnetwork();
//        //跳转
//        if(!TextUtils.isEmpty(evate_zhuip.getText().toString())){
//            ToastUtils.showShort(this,"您已追加评论，不能在评论");
//
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuijia:
                /*
            判断评价状态值

           */
                if("1".equals(datas.getAdd_status())){
                    Intent intent = new Intent(EvaluatedetailActivity.this, ZhijiaActivity.class);
                    intent.putExtra("comment_id", comment_id);
                    intent.putExtra("order_id",order_id);
                    intent.putExtra("tag","2");
                    startActivity(intent);
                }
                  else if("2".equals(datas.getAdd_status())){
                    liner_zhuijia.setVisibility(View.VISIBLE);
                    liner_zhui.setVisibility(View.VISIBLE);
                    //跳转
                    if(!TextUtils.isEmpty(evate_zhuip.getText().toString())){
                        ToastUtils.showShort(this,"您已追加评论，不能在评论");

                    }
                  }


                break;
            case R.id.tv_back:
                 finish();
                break;
        }
    }
}
