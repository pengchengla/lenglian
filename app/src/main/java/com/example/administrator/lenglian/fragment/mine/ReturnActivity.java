package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
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
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SoftKeyboardTool;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.utils.provice.AddressUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ReturnActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private EditText tuihuan_name;
    private EditText tuihuan_phone;
    private TextView textView2;
    private TextView tuihuan_address;
    private LinearLayout tuiaddress;
    private EditText tuihuan_xianq;
    private Button tunhuan_btn;
    private String order_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_tuihuan);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tuihuan_name = (EditText) findViewById(R.id.tuihuan_name);
        tuihuan_phone = (EditText) findViewById(R.id.tuihuan_phone);
        textView2 = (TextView) findViewById(R.id.textView2);
        tuihuan_address = (TextView) findViewById(R.id.tuihuan_address);
        tuiaddress = (LinearLayout) findViewById(R.id.tuiaddress);
        tuihuan_xianq = (EditText) findViewById(R.id.tuihuan_xianq);
        tunhuan_btn = (Button) findViewById(R.id.tunhuan_btn);
        tv_back.setOnClickListener(this);
        tunhuan_btn.setOnClickListener(this);
        tuiaddress.setOnClickListener(this);
        //获取数据

        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tunhuan_btn:
                submit();
                break;
            case R.id.tv_back:
                 finish();
                break;
            case R.id.tuiaddress:
                SoftKeyboardTool.closeKeyboard(tuihuan_phone);
                showAddressDialog();
                break;
        }
    }
    private void showAddressDialog() {
        new AddressUtils().ShowAddressDialog(this,  tuihuan_address);
    }
    private void submit() {
        // validate
        String name = tuihuan_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入您的姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = tuihuan_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入您的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!MyUtils.isMobileNO(tuihuan_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        String xianq = tuihuan_xianq.getText().toString().trim();
        if (TextUtils.isEmpty(xianq)) {
            Toast.makeText(this, "详细地址：街道/小区/门牌号等", Toast.LENGTH_SHORT).show();
            return;
        }
             //加载网络请求
       network();
    }

    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token",MyUtils.getToken());
        map.put("order_id",order_id);
        map.put("contact_name",tuihuan_name.getText().toString());
        map.put("contact_mobile",tuihuan_phone.getText().toString());
        map.put("area_id",tuihuan_address.getText().toString());
        map.put("return_address",tuihuan_xianq.getText().toString());
        RetrofitManager.post(MyContants.BASEURL + "s=Order/newReturn", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(ReturnActivity.this,"提交成功");
                    finish();

                }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(ReturnActivity.this,"提交失败，请检查网络");
            }
        });
    }
}
