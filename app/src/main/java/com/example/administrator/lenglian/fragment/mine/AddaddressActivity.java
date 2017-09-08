package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.utils.provice.AddressUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AddaddressActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_back;
    private TextView adress_save;
    private EditText etad_name;
    private EditText eted_phone;
    private LinearLayout eted_city;
    private EditText eted_xianq;
    private ImageView edimg_guanli;
    private TextView ad_address;
    boolean bool;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        adress_save = (TextView) findViewById(R.id.adress_save);
        etad_name = (EditText) findViewById(R.id.etad_name);
        eted_phone = (EditText) findViewById(R.id.eted_phone);
        eted_city = (LinearLayout) findViewById(R.id.eted_city);
        eted_xianq = (EditText) findViewById(R.id.eted_xianq);
        edimg_guanli = (ImageView) findViewById(R.id.edimg_guanli);
        ad_address = (TextView) findViewById(R.id.add_address);
        tv_back.setOnClickListener(this);
        adress_save.setOnClickListener(this);
        eted_city.setOnClickListener(this);
        edimg_guanli.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String name = etad_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = eted_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "phone不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String xianq = eted_xianq.getText().toString().trim();
        if (TextUtils.isEmpty(xianq)) {
            Toast.makeText(this, "详细地址：街道/小区/门牌号等", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
    //选择城市
    private void showAddressDialog() {
        new AddressUtils().ShowAddressDialog(this, ad_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.adress_save:
                break;
            case R.id. eted_city:
                showAddressDialog();
                break;
            case R.id. edimg_guanli:
                bool=!bool;
                 if(bool){
                     edimg_guanli.setImageResource(R.drawable.select_true);

                 }
                else {
                     bool=false;
                     edimg_guanli.setImageResource(R.drawable.sleect_false);
                 }
                break;
        }
    }
}
