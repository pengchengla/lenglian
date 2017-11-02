package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.widget.Button;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LancherActivity extends BaseActivity {
    private Button btn_time;
    private int time = 3;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                time--;
                btn_time.setText(time + "s");
                if (time == 0) {
                    boolean isguide = SpUtils.getBoolean(LancherActivity.this, "guide", false);
                    if (isguide) {
                        startActivity(new Intent(LancherActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(LancherActivity.this, GuidePageActivity.class));
                    }
                    finish();
                } else {
                    Message message = mHandler.obtainMessage(1);
                    mHandler.sendMessageDelayed(message, 1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        btn_time = (Button) findViewById(R.id.btn_time);
        //        Message message = mHandler.obtainMessage(1);
        //        mHandler.sendMessageDelayed(message, 1000);
        String umpushid = SpUtils.getString(this, "UMPUSHID", "");
        if (!TextUtils.isEmpty(umpushid)) {
            ArrayMap arrayMap = new ArrayMap<String, String>();
            arrayMap.put("user_id", SpUtils.getString(this, "user_id", ""));
            arrayMap.put("token", MyUtils.getToken());
            arrayMap.put("device_token", umpushid);
            RetrofitManager.get(MyContants.BASEURL + "s=User/upush", arrayMap, new BaseObserver1<EasyBean>("") {
                @Override
                public void onSuccess(EasyBean result, String tag) {

                    //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                    if (result.getCode() == 200) {

                    } else {

                    }
                }

                @Override
                public void onFailed(int code) {
                }
            });
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isguide = SpUtils.getBoolean(LancherActivity.this, "guide", false);
                if (isguide) {
                    startActivity(new Intent(LancherActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(LancherActivity.this, GuidePageActivity.class));
                }
                finish();
            }
        }, 1500);
    }
}
