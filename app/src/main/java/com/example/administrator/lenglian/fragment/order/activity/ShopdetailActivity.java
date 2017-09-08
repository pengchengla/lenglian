package com.example.administrator.lenglian.fragment.order.activity;

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

public class ShopdetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ImageView shop_img;
    private MyRatingBar shop_ratingbar;
    private EditText warantu_edtext;
    private TextView textView4;
    private MyRatingBar peisongatingbar;
    private MyRatingBar shopfuratingbar;
    private TextView shop_tijiao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdetail);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        shop_img = (ImageView) findViewById(R.id.shop_img);
        shop_ratingbar = (MyRatingBar) findViewById(R.id.shop_ratingbar);
        warantu_edtext = (EditText) findViewById(R.id.warantu_edtext);
        textView4 = (TextView) findViewById(R.id.textView4);
        peisongatingbar = (MyRatingBar) findViewById(R.id.peisongatingbar);
        shopfuratingbar = (MyRatingBar) findViewById(R.id.shopfuratingbar);
        shop_tijiao = (TextView) findViewById(R.id.shop_tijiao);
    }

    private void submit() {
        // validate
        String edtext = warantu_edtext.getText().toString().trim();
        if (TextUtils.isEmpty(edtext)) {
            Toast.makeText(this, "分享你的租赁心的  ", Toast.LENGTH_SHORT).show();
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
        }
    }
}
