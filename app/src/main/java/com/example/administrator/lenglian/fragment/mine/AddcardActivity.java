package com.example.administrator.lenglian.fragment.mine;

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
import com.example.administrator.lenglian.activity.RegisterActivity;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BankCardUtils;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SendSmsTimerUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddcardActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private EditText ced_name;
    private EditText ced_phone;
    private EditText ced_cardnum;
    private EditText edt_code;
    private TextView tv_getcode;
    private Button btn_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_add);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        ced_name = (EditText) findViewById(R.id.ced_name);
        ced_phone = (EditText) findViewById(R.id.ced_phone);
        ced_cardnum = (EditText) findViewById(R.id.ced_cardnum);
        edt_code = (EditText) findViewById(R.id.edt_code);
        tv_getcode = (TextView) findViewById(R.id.tv_getcode);
        btn_yes = (Button) findViewById(R.id.btn_yes);

        btn_yes.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        //获取验证码
        tv_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();

            }
        });
    }

    public void getCode() {
        if (TextUtils.isEmpty(ced_phone.getText().toString())) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (!MyUtils.isMobileNO(ced_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("mobile", ced_phone.getText().toString().trim());
        RetrofitManager.get(MyContants.BASEURL + "s=Verify/sendSMS", arrayMap, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {
                if (result.getCode() == 200) {
                    Toast.makeText(AddcardActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                    SendSmsTimerUtils.sendSms(tv_getcode, R.color.text_red, R.color.text_red);
                } else {
                    Toast.makeText(AddcardActivity.this, "验证码发送失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(AddcardActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                  //判断银行卡是否正确
                submit();
                boolean b = BankCardUtils.checkBankCard(ced_cardnum.getText().toString());
                if(b){

                    //将数据上传到服务器

                }
                else {
                    ToastUtils.showShort(AddcardActivity.this,"请输入正确的银行卡号");
                }


                break;
            case R.id.tv_back:
                  finish();
                break;
        }
    }

    private void submit() {
        // validate
        String name = ced_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = ced_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        } else if (!MyUtils.isMobileNO(ced_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        String cardnum = ced_cardnum.getText().toString().trim();
        if (TextUtils.isEmpty(cardnum)) {
            Toast.makeText(this, "请输入银行卡号", Toast.LENGTH_SHORT).show();

            return;
        }



        String code = edt_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }



    }
}
