package com.example.administrator.lenglian.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.UMLoginActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.bean.LoginBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.view.CustomProgressDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/8/24.
 */

public class LoginActivity extends UMLoginActivity implements View.OnClickListener {
    private EditText edt_phone, edt_mima, edt_code;
    private Button btn_login;
    private TextView tv_forgetpsw, tv_zhuce;
    private ImageView iv_weixin, iv_qq, iv_weibo;
    private static ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_mima = (EditText) findViewById(R.id.edt_mima);
        edt_code = (EditText) findViewById(R.id.edt_code);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_forgetpsw = (TextView) findViewById(R.id.tv_forgetpsw);
        tv_forgetpsw.setOnClickListener(this);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        tv_zhuce.setOnClickListener(this);
        iv_weixin = (ImageView) findViewById(R.id.iv_weixin);
        iv_weixin.setOnClickListener(this);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_weibo = (ImageView) findViewById(R.id.iv_weibo);
        iv_weibo.setOnClickListener(this);
        mDialog = new CustomProgressDialog(this, R.style.myprogressdialog);


        //        edt_phone.setText(SpUtils.getString(LoginActivity.this,"phone",""));
        //        edt_mima.setText(SpUtils.getString(LoginActivity.this,"password",""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_zhuce:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_forgetpsw:
                Intent intent3 = new Intent(this, ForgetpswActivity.class);
                startActivity(intent3);
                break;
            case R.id.iv_weibo:
                mDialog.show();
                loginBySina(this);
                break;
            case R.id.iv_weixin:
                mDialog.show();
                loginByWeiXin(this);
                break;
            case R.id.iv_qq:
                mDialog.show();
                loginByQQ(this);
                break;
        }
    }

    private void login() {
        btn_login.setClickable(false);
        if (TextUtils.isEmpty(edt_phone.getText().toString())) {
            Toast.makeText(this, "请填写您的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edt_mima.getText().toString())) {
            Toast.makeText(this, "请填写您的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("mobile", edt_phone.getText().toString().trim());
        arrayMap.put("password", edt_mima.getText().toString().trim());
        arrayMap.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=User/login", arrayMap, new BaseObserver1<LoginBean>("") {
            @Override
            public void onSuccess(LoginBean result, String tag) {
                if (result.getCode() == 200) {
                    Toast.makeText(LoginActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                    SpUtils.putString(LoginActivity.this, "user_id", result.getDatas().getUser_id());
                    SpUtils.putString(LoginActivity.this, "phone", edt_phone.getText().toString().trim());
                    //                    SpUtils.putString(LoginActivity.this, "password", edt_mima.getText().toString().trim());
                    //在其他页面转到登录界面，finish掉登录界面即可
                    if (getIntent().getStringExtra("gologin").equals("gologin")) {
                        finish();
                        EventMessage eventMessage = new EventMessage("xxx");
                        EventBus.getDefault().postSticky(eventMessage);
                        EventMessage eventMessages = new EventMessage("bbb");
                        EventBus.getDefault().postSticky(eventMessages);
                    } else {
                        Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }
                    //                    Toast.makeText(LoginActivity.this, result.getDatas().getUser_id(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
                    btn_login.setClickable(true);
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(LoginActivity.this, "登录失败，请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
                btn_login.setClickable(true);
            }
        });
    }

    public static void closeDialog(){
        mDialog.dismiss();
        mDialog=null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
