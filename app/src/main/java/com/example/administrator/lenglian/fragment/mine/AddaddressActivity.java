package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
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
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SoftKeyboardTool;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.utils.provice.AddressUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

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
    private boolean aBoolean;
    private String ad_name;
    private String ad_phone;
    private String ad_city;
    private String ad_citydetail;
    private String ad_default;
    private String express_id;


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
        //得到数据
        Intent it=getIntent();
        aBoolean = it.getBooleanExtra("boolean", false);
        ad_name = it.getStringExtra("ad_name");
        ad_phone = it.getStringExtra("ad_phone");
        ad_city = it.getStringExtra("ad_city");
        ad_citydetail = it.getStringExtra("ad_citydetail");
        ad_default = it.getStringExtra("ad_default");
        express_id = it.getStringExtra("express_id");
        if(aBoolean){
    /*
      走编辑网络请求

     */
            //加载数据
            etad_name.setText(ad_name);
            eted_phone.setText(ad_phone);
            ad_address.setText(ad_city);
            eted_xianq.setText(ad_citydetail);
             if( "1".equals(ad_default)){
                 edimg_guanli.setImageResource(R.drawable.select_true);

             }
            else {
                 edimg_guanli.setImageResource(R.drawable.sleect_false);
             }

        }

    }

    private void submit() {
        // validate
        String name = etad_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = eted_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!MyUtils.isMobileNO(eted_phone.getText().toString())) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        String address= ad_address.getText().toString();
        if(TextUtils.isEmpty(address)){
            ToastUtils.showShort(AddaddressActivity.this,"请选择省市区");
            return;
        }



        String xianq = eted_xianq.getText().toString().trim();
        if (TextUtils.isEmpty(xianq)) {
            Toast.makeText(this, "详细地址：街道/小区/门牌号等", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
   if(aBoolean){
    /*
      走编辑网络请求

     */

       ininbianji();
   }
        else {
       inindata();
   }


    }

    private void ininbianji() {
        //加载网络请求
        Map<String,String> map=new HashMap<>();
        map.put("user_id",SpUtils.getString(this,"user_id",""));//随时传过来的----------------
        map.put("token",MyUtils.getToken());
        map.put("receive_name",etad_name.getText().toString());
        map.put("mobile",eted_phone.getText().toString());
        map.put("express_id",express_id);//传过来的--------------
        map.put("area_id",ad_address.getText().toString());
        map.put("address_detail",eted_xianq.getText().toString());
        if(bool){
            map.put("is_default","1");

        }
        else {
            map.put("is_default","2");
        }
        RetrofitManager.get(MyContants.BASEURL + "s=User/editExpress", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                int code = result.getCode();
                if(code==200){
                    ToastUtils.showShort(AddaddressActivity.this,"添加成功");
                     Intent intent=new Intent(AddaddressActivity.this,AddressActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void inindata() {
        //加载网络请求
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token",MyUtils.getToken());
        map.put("receive_name", etad_name.getText().toString());
        map.put("mobile",eted_phone.getText().toString());
        map.put("area_id",ad_address.getText().toString());
        map.put("address_detail",eted_xianq.getText().toString());
        if(bool){
            map.put("is_default","1");

        }
        else {
            map.put("is_default","2");
        }
        RetrofitManager.get(MyContants.BASEURL + "s=User/editExpress", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                int code = result.getCode();
                if(code==200){
                   ToastUtils.showShort(AddaddressActivity.this,"添加成功");
                    finish();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
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
                submit();
                 //发送Eventbus
                EventMessage eventMessage = new EventMessage("ddd");
                EventBus.getDefault().postSticky(eventMessage);
                break;
            case R.id. eted_city:
                showAddressDialog();
                SoftKeyboardTool.closeKeyboard( eted_phone);
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
