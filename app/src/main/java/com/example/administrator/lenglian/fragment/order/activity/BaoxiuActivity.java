package com.example.administrator.lenglian.fragment.order.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.utils.SoftKeyboardTool;
import com.example.administrator.lenglian.utils.provice.AddressUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class BaoxiuActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private EditText baoxiu_name;
    private EditText baoxiu_phone;
    private TextView textView2;
    private TextView baoxiu_address;
    private LinearLayout baoaddress;
    private EditText tuihuan_xianq;
    private EditText baoxiu_xianqing;
    private Button baoxiu_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_baoxiu);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        baoxiu_name = (EditText) findViewById(R.id.baoxiu_name);
        baoxiu_phone = (EditText) findViewById(R.id.baoxiu_phone);
        textView2 = (TextView) findViewById(R.id.textView2);
        baoxiu_address = (TextView) findViewById(R.id.baoxiu_address);
        baoaddress = (LinearLayout) findViewById(R.id.baoaddress);
        tuihuan_xianq = (EditText) findViewById(R.id.tuihuan_xianq);
        baoxiu_xianqing = (EditText) findViewById(R.id.baoxiu_xianqing);
        baoxiu_btn = (Button) findViewById(R.id.baoxiu_btn);

        baoxiu_btn.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        baoaddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baoxiu_btn:

                break;
            case R.id.tv_back:
                   finish();
                break;
            case R.id.baoaddress:
                SoftKeyboardTool.closeKeyboard(baoxiu_phone);
                showAddressDialog();
                break;

        }
    }

    private void submit() {
        // validate
        String name = baoxiu_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = baoxiu_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "phone不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String xianq = tuihuan_xianq.getText().toString().trim();
        if (TextUtils.isEmpty(xianq)) {
            Toast.makeText(this, "详细地址：街道/小区/门牌号等", Toast.LENGTH_SHORT).show();
            return;
        }

        String xianqing = baoxiu_xianqing.getText().toString().trim();
        if (TextUtils.isEmpty(xianqing)) {
            Toast.makeText(this, "请简述保修情况", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
    //选择城市
    private void showAddressDialog() {
        new AddressUtils().ShowAddressDialog(this,  baoxiu_address);
    }
}
