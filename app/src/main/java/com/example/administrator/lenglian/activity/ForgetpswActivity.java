package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SendSmsTimerUtils;

/**
 * Created by Administrator on 2017/8/24.
 */

public class ForgetpswActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back, tv_getcode;
    private EditText edt_phone, edt_code, edt_mima, edt_mima2;
    private Button btn_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsw);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_code = (EditText) findViewById(R.id.edt_code);
        edt_mima = (EditText) findViewById(R.id.edt_mima);
        edt_mima2 = (EditText) findViewById(R.id.edt_mima2);
        tv_getcode = (TextView) findViewById(R.id.tv_getcode);
        tv_getcode.setOnClickListener(this);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_getcode:
                getCode();
                break;
            case R.id.btn_yes:
                modifyPsw();
                break;
        }
    }

    public void getCode() {
        if (TextUtils.isEmpty(edt_phone.getText().toString())) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (!MyUtils.isMobileNO(edt_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("mobile", edt_phone.getText().toString().trim());
        RetrofitManager.get(MyContants.BASEURL + "s=Verify/sendSMS", arrayMap, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    Toast.makeText(ForgetpswActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                    SendSmsTimerUtils.sendSms(tv_getcode, R.color.white, R.color.text_red);
                } else {
                    Toast.makeText(ForgetpswActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(ForgetpswActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void modifyPsw() {
        if (TextUtils.isEmpty(edt_phone.getText().toString())) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!MyUtils.isMobileNO(edt_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edt_code.getText().toString())) {
            Toast.makeText(ForgetpswActivity.this, "请填写验证码", Toast.LENGTH_SHORT).show();
            return;
        }
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
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("token", MyUtils.getToken());
        arrayMap.put("mobile", edt_phone.getText().toString().trim());
        arrayMap.put("code", edt_code.getText().toString().trim());
        arrayMap.put("new_password", edt_mima.getText().toString().trim());
        RetrofitManager.get(MyContants.BASEURL + "s=User/changeNonePassword", arrayMap, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    Toast.makeText(ForgetpswActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    finish();//返回登录界面
                } else {
                    Toast.makeText(ForgetpswActivity.this, "密码修改失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(ForgetpswActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
