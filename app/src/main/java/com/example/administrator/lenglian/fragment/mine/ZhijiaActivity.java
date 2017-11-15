package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ZhijiaActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView tijiao;
    private EditText edt_pin;
    private String comment_id;
    private String repair_id;
    private String tag;
    private String order_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evalute_addto);
        initView();


    }

    private void ininnetwork() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("comment_id",comment_id);
        if("1".equals(tag)){
            map.put("repair_id",repair_id);
        }
        else if("2".equals(tag)){
            map.put("order_id",order_id);
        }
        map.put("add_content",  edt_pin.getText().toString());
        RetrofitManager.post(MyContants.BASEURL+"s=User/addComment",map,new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                      SpUtils.putString(ZhijiaActivity.this,"count",edt_pin.getText().toString());
                    EventMessage eventMessage = new EventMessage("zhuijia");
                    EventBus.getDefault().postSticky(eventMessage);
                    finish();


                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tijiao = (TextView) findViewById(R.id.tijiao);
        edt_pin = (EditText) findViewById(R.id.edt_pin);
        tv_back.setOnClickListener(this);
        tijiao.setOnClickListener(this);
        edt_pin.setOnClickListener(this);
        Intent intent = getIntent();
        comment_id = intent.getStringExtra("comment_id");
        repair_id = intent.getStringExtra("repair_id");
        tag = intent.getStringExtra("tag");
        order_id = intent.getStringExtra("order_id");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tijiao:
                ininnetwork();//加载网络
                 //提交
                break;
            case R.id.edt_pin:
                 edt_pin.setCursorVisible(true);
                break;
        }
    }
}
