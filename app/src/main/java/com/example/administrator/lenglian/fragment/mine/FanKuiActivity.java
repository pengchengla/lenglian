package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

public class FanKuiActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private EditText edt_title;
    private EditText edt_content;
    private TextView btn_tijiao;
    private LinearLayout activity_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_kui);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        edt_title = (EditText) findViewById(R.id.edt_title);
        edt_content = (EditText) findViewById(R.id.edt_content);
        btn_tijiao = (TextView) findViewById(R.id.btn_tijiao);
        btn_tijiao.setOnClickListener(this);
        activity_feedback = (LinearLayout) findViewById(R.id.activity_feedback);
    }

    private void submit() {
        // validate
        String title = edt_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String content = edt_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("user_id", SpUtils.getString(this, "user_id", ""));
        arrayMap.put("token", MyUtils.getToken());
        arrayMap.put("content", "标题：" + title + "  内容：" + content);
        RetrofitManager.get(MyContants.BASEURL + "s=User/feedback", arrayMap, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    Toast.makeText(FanKuiActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FanKuiActivity.this, "反馈失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_tijiao:
                submit();
                break;
        }
    }
}
