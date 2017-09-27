package com.example.administrator.lenglian.fragment.order.activity;

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
    private String order_id;

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
        //获取数据
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baoxiu_btn:
                submit();
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
            Toast.makeText(this, "请输入您的名字", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = baoxiu_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入您的手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (!MyUtils.isMobileNO(baoxiu_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
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

        // 加载网络请求
       network();


    }
   //网络请求

    private void network() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",SpUtils.getString(this,"user_id","")));
        map.put("token", MyUtils.getToken());
        map.put("order_id",order_id);
        map.put("contact_name",baoxiu_name.getText().toString());
        map.put("contact_mobile",baoxiu_phone.getText().toString());
        map.put("area_id", baoxiu_address.getText().toString());
        map.put("repair_address",tuihuan_xianq.getText().toString());
        map.put("repair_note",baoxiu_xianqing.getText().toString());

        RetrofitManager.post(MyContants.BASEURL + "s=Order/newRepair", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                 if(result.getCode()==200){
                     ToastUtils.showShort(BaoxiuActivity.this,"提交成功");
                     finish();
                 }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(BaoxiuActivity.this,"提交失败，请检查网络");
            }
        });

    }

    //选择城市
    private void showAddressDialog() {
        new AddressUtils().ShowAddressDialog(this,  baoxiu_address);
    }
}
