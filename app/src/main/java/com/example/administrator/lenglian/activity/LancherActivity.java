package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
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
        Message message = mHandler.obtainMessage(1);
        mHandler.sendMessageDelayed(message, 1000);
    }
}
