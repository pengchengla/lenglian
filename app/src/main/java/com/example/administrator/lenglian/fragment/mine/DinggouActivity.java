package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DinggouActivity extends BaseActivity {
    private TextView tv_back;
    private TextView user_title;
    private TextView user_head;
    private TextView user_count;
    private TextView user_count2;
    private TextView user_head2;
    private TextView user_count3;
    private TextView user_count4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_count);
        initView();
         inintdata();
    }

    private void inintdata() {


    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        user_title = (TextView) findViewById(R.id.user_title);
        user_head = (TextView) findViewById(R.id.user_head);
        user_count = (TextView) findViewById(R.id.user_count);
        user_count2 = (TextView) findViewById(R.id.user_count2);
        user_head2 = (TextView) findViewById(R.id.user_head2);
        user_count3 = (TextView) findViewById(R.id.user_count3);
        user_count4 = (TextView) findViewById(R.id.user_count4);
         tv_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }
}
