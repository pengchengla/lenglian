package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.view.SnappingStepper;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class RenewActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private ImageView renew_tupian;
    private TextView renew_count;
    private TextView renew_price;
    private SnappingStepper renew_num;
    private TextView renew_money;
    private TextView renew_geshu;
    private TextView renew_total;
    private TextView renew_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_renew);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        renew_tupian = (ImageView) findViewById(R.id.renew_tupian);
        renew_count = (TextView) findViewById(R.id.renew_count);
        renew_price = (TextView) findViewById(R.id.renew_price);
        renew_num = (SnappingStepper) findViewById(R.id.renew_num);
        renew_money = (TextView) findViewById(R.id.renew_money);
        renew_geshu = (TextView) findViewById(R.id.renew_geshu);
        renew_total = (TextView) findViewById(R.id.renew_total);
        renew_btn = (TextView) findViewById(R.id.renew_btn);
        tv_back.setOnClickListener(this);
        renew_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.renew_btn:
                showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);
                break;
        }
    }
      //支付
      private void showGenderDialog(int grary, int animationStyle) {
          BaseDialog.Builder builder = new BaseDialog.Builder(this);
          final BaseDialog dialog = builder.setViewId(R.layout.order_diagzhifu)
                  //设置dialogpadding
                  .setPaddingdp(10, 0, 10, 0)
                  //设置显示位置
                  .setGravity(grary)
                  //设置动画
                  .setAnimation(animationStyle)
                  //设置dialog的宽高
                  .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                  //设置触摸dialog外围是否关闭
                  .isOnTouchCanceled(true)
                  //设置监听事件
                  .builder();
          //支付宝
          dialog.getView(R.id.zhifu_zhifubao).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  dialog.dismiss();
              }
          });
          //微信
          dialog.getView(R.id.zhifu_weixin).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  dialog.dismiss();
              }
          });
          //银行
          dialog.getView(R.id.zhifu_bank).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  dialog.dismiss();
              }
          });
          //银行2
          dialog.getView(R.id.zhifu_bank2).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  dialog.dismiss();
              }
          });
          dialog.getView(R.id.zhifu_cancel).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dialog.dismiss();
              }
          });
          dialog.show();
      }
}
