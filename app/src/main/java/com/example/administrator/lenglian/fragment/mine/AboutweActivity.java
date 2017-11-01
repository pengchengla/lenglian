package com.example.administrator.lenglian.fragment.mine;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.MyApplication;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AboutweActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private TextView  set_edwe,text_count,text_count1,text_count2;
    private String count="兰州阿童网络科技有限公司一家以共享冷链为市场导向的互联网科技有限";
    private String count1="公司。在国家大力提倡互联网+、以及共享经济的大环境下诞生CFC冷链是一个提供冷藏冷冻柜、冷藏车及冷藏冷冻设备等多项业务的移动互联网租赁共享平台。CFC冷链app软件的诞生改变了传统购买冷柜的习惯，而是一种互联网+O2O,F2C模式的共享冷链平台。让食品冷冻冷藏产业链中的仓储，物流，派送，超市，便利店特种冷柜设施在时间上得到充分利用，大大提高设备的“利用率”，解决食品厂家长期购买冷藏柜送于超市，便利店及各终端零售店巨额的市场投入，降低营销成本，CFC冷链解决了冷库最大化的释放其价值，冷藏车的空置时间，从而推动“共享经济”的发展，解决冷链行业的冷库仓储，冷藏车，各种特种冷藏冷冻柜的空置浪费问题。让企业降低了运营成本。";

    private String count2="创始人顺势而为，以“打造世界一流的冷链平台”的远大企业愿景服务于每";
    private String count3="位使用冷藏冷冻设备的个人、商家、厂家、企事业单位等，同时让用户体验到了共享冷链所带来的方便、经济，快捷，力争做一家世界一流的冷链大平台企业。做为始作俑者，我们肩负起：“为健康保鲜每一天”的使命！同年11月公司CFC冷链平台软件上线，代表者我公司正式转型进入互联网+ 的共享时代";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_aboutwe);
        initView();


    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        set_edwe = (TextView) findViewById(R.id.set_edwe);
        text_count= (TextView) findViewById(R.id.text_count);
        text_count1= (TextView) findViewById(R.id.text_count1);
        text_count2= (TextView) findViewById(R.id.text_count2);
        tv_back.setOnClickListener(this);
        set_edwe.setOnClickListener(this);
        SpannableString spannableString = new SpannableString(count);
        StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.BLACK);
        spannableString.setSpan(colorSpan, 0, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan_B, 0, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        set_edwe.setText(spannableString);
        text_count.setText(count1);
        text_count1.setText(count2);
        text_count2.setText(count3);


    }

    private void submit() {
        // validate
        String edwe = set_edwe.getText().toString().trim();
        if (TextUtils.isEmpty(edwe)) {
            Toast.makeText(this, "关于我们的内容", Toast.LENGTH_SHORT).show();
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
            case R.id. set_edwe:
                set_edwe.setCursorVisible(true);
                break;
        }
    }
}
