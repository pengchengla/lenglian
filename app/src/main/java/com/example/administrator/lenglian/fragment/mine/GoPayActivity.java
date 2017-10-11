package com.example.administrator.lenglian.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Paybean;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class GoPayActivity extends BaseActivity {

    private RecyclerView recycler_pay;
    private RelativeLayout tv_back;
    private List<Paybean> mPaybeanList = new ArrayList<>();
    private PayStyleAdapter mPayStyleAdapter;
    private Button btn_yes;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_way);
        recycler_pay = (RecyclerView) findViewById(R.id.recycler_pay);
        tv_back = (RelativeLayout) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPay();
            }
        });
        String orderid = getIntent().getStringExtra("orderid");
        initData();
    }

    private void doPay() {
        String payStyle = getPayStyle();
        if (TextUtils.isEmpty(payStyle)) {
            return;
        }
        if (payStyle.equals("支付宝")) {
            //调起支付宝支付

        } else if (payStyle.equals("微信")) {
            //调起微信支付

        } else {
            //银行卡支付
        }
    }

    private String getPayStyle() {
        for (int i = 0; i < mPayStyleAdapter.getData().size(); i++) {
            if (mPayStyleAdapter.getData().get(i).isChecked()) {
                return mPayStyleAdapter.getData().get(i).getName();
            }
        }
        return "";
    }

    private void initData() {
        recycler_pay.setLayoutManager(new LinearLayoutManager(this));
        mPaybeanList.add(new Paybean("", "支付宝"));
        mPaybeanList.add(new Paybean("", "微信"));
        mPaybeanList.get(0).setChecked(true);//默认选中第一个
        mPayStyleAdapter = new PayStyleAdapter(R.layout.pay_item, mPaybeanList);
        recycler_pay.setAdapter(mPayStyleAdapter);
    }

    class PayStyleAdapter extends BaseQuickAdapter<Paybean, BaseViewHolder> {

        public PayStyleAdapter(@LayoutRes int layoutResId, @Nullable List<Paybean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, Paybean item) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            if (helper.getAdapterPosition() == 0) {
                Glide.with(mContext).load(R.drawable.zhifubao)
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.pay_image));
            } else if (helper.getAdapterPosition() == 1) {
                Glide.with(mContext).load(R.drawable.weixin)
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.pay_image));
            } else {
                Glide.with(mContext).load(item.getImage())
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.pay_image));
            }
            if (item.isChecked()) {
                helper.setChecked(R.id.pay_cb, true);
            } else {
                helper.setChecked(R.id.pay_cb, false);
            }
            helper.setText(R.id.tv_name, item.getName());
            helper.getView(R.id.rl_root_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < mPayStyleAdapter.getData().size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            mPayStyleAdapter.getData().get(i).setChecked(true);
                        } else {
                            mPayStyleAdapter.getData().get(i).setChecked(false);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
        goBack();
    }

    //弹出对话框进行判断是否退出这个页面
    private void goBack() {
        mDialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("确定要放弃支付吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        mDialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("我点错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
