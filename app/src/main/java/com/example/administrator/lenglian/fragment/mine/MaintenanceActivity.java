package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.view.MyRatingBar;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MaintenanceActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private ImageView waranty_img;
    private MyRatingBar ratingbar;
    private EditText warantu_edtext;
    private MyRatingBar furatingbar;
    private TextView tijiao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waranty_evaluate);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        waranty_img = (ImageView) findViewById(R.id.waranty_img);
        ratingbar = (MyRatingBar) findViewById(R.id.ratingbar);
        warantu_edtext = (EditText) findViewById(R.id.warantu_edtext);
        furatingbar = (MyRatingBar) findViewById(R.id.furatingbar);
        tijiao = (TextView) findViewById(R.id.tijiao);
        ratingbar.setClickable(true);//设置可否点击
        ratingbar.setStar(0f);//设置显示的星星个数
        ratingbar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星
        furatingbar.setClickable(true);
        furatingbar.setStar(0f);//设置显示的星星个数
        furatingbar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星
        tv_back.setOnClickListener(this);
        tijiao.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String edtext = warantu_edtext.getText().toString().trim();
        if (TextUtils.isEmpty(edtext)) {
            Toast.makeText(this, "维修评价  ", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tijiao:
                break;
        }
    }
}
