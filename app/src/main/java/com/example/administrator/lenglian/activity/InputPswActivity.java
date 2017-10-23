package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.ThreeLoginBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;

public class InputPswActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private EditText edt_mima, edt_mima2;
    private Button btn_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_psw);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        edt_mima = (EditText) findViewById(R.id.edt_mima);
        edt_mima2 = (EditText) findViewById(R.id.edt_mima2);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_yes:
                GoMain();
                break;
        }
    }

    private void GoMain() {
        if (TextUtils.isEmpty(edt_mima.getText().toString())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edt_mima.getText().toString().length() < 5 || edt_mima.getText().toString().length() > 20) {
            Toast.makeText(this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!edt_mima.getText().toString().equals(edt_mima2.getText().toString())) {
            Toast.makeText(this, "两次输入的密码不同，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        String openid = getIntent().getStringExtra("openid");
        String type = getIntent().getStringExtra("type");
        String code = getIntent().getStringExtra("code");
        String phone = getIntent().getStringExtra("phone");
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("openid", openid);
        arrayMap2.put("type", type);
        arrayMap2.put("password", edt_mima.getText().toString());
        arrayMap2.put("mobile", phone);
        arrayMap2.put("code", code);
        RetrofitManager.post(MyContants.BASEURL + "s=User/nt_login", arrayMap2, new BaseObserver1<ThreeLoginBean>("") {
            @Override
            public void onSuccess(ThreeLoginBean result, String tag) {
                if (result.getCode() == 200) {
                    startActivity(new Intent(InputPswActivity.this,MainActivity.class));
                } else {
                    //101是没有数据

                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }
}
