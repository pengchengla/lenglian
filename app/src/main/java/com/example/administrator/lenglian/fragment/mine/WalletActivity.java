package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.Walletadapter;
import com.example.administrator.lenglian.fragment.order.bean.Walletbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class WalletActivity extends BaseActivity {

    private TextView tv_back;
    private ImageView img_yu;
    private TextView yue;
    private TextView balance;
    private RelativeLayout recl_title;
    private RelativeLayout wallet_time;
    private TextView wallet_date;
    private RelativeLayout wallet_dates;
    private TextView wallet_cash;
    private TextView cash_num;
    private ListView wallet_list;
    private RelativeLayout linearLayout;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_wallet);
        initView();
        //添加数据
        inindata();
    }

    private void inindata() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));//传过来的--------------------
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=User/viewWallet", map, new BaseObserver1<Walletbean>("") {
            @Override
            public void onSuccess(Walletbean result, String tag) {
                  if(result.getCode()==200){
                      List<Walletbean.DatasBean> datas = result.getDatas();
                      Walletadapter walletadapter=new Walletadapter(WalletActivity.this,datas);
                      wallet_list.setAdapter(walletadapter);



                  }
                  else if(result.getCode()==101){
                      linearLayout.setVisibility(View.VISIBLE);
                      wallet_list.setVisibility(View.GONE);
                      textView.setText("我的钱包    空空如也~");
                  }

            }

            @Override
            public void onFailed(int code) {
//                Toast.makeText(WalletActivity.this,"失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
//        img_yu = (ImageView) findViewById(R.id.img_yu);
//        yue = (TextView) findViewById(R.id.yue);
//        balance = (TextView) findViewById(R.id.balance);//余额
//        recl_title = (RelativeLayout) findViewById(R.id.recl_title);
//        wallet_time = (RelativeLayout) findViewById(R.id.wallet_time);
//        wallet_date = (TextView) findViewById(R.id.wallet_date);//使用时间
//        wallet_dates = (RelativeLayout) findViewById(R.id.wallet_dates);
//        wallet_cash = (TextView) findViewById(R.id.wallet_cash);
//        cash_num = (TextView) findViewById(R.id.cash_num);//押金
        wallet_list = (ListView) findViewById(R.id.wallet_list);
        linearLayout = (RelativeLayout) findViewById(R.id.kong);
        textView = (TextView) findViewById(R.id.kong_text);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
