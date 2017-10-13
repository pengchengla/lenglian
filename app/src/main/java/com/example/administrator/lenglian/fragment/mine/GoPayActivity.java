package com.example.administrator.lenglian.fragment.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Paybean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.pay.AliPayBean;
import com.example.administrator.lenglian.pay.PayResult;
import com.example.administrator.lenglian.pay.PaySuccessActivity;
import com.example.administrator.lenglian.utils.MyContants;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private String mOrder_num;
    private static final int SDK_PAY_FLAG = 1;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wx76c60c8c929e5061";// 微信appid
    private String payPrice;

    //-------------------------------------支付宝支付---------------------------------------------
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    Toast.makeText(GoPayActivity.this, " " + payResult.getResultStatus(), Toast.LENGTH_SHORT).show();
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(GoPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //自己写的跳转到自定义的支付宝支付成功界面
                        Intent intent = new Intent(GoPayActivity.this, PaySuccessActivity.class);
                        intent.putExtra("price",payPrice);
                        startActivity(intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(GoPayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(GoPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

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
        mOrder_num = getIntent().getStringExtra("order_num");
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
        initData();
    }

    private void doPay() {
        String payStyle = getPayStyle();
        if (TextUtils.isEmpty(payStyle)) {
            return;
        }
        if (payStyle.equals("支付宝")) {
            //调起支付宝支付
            goAliPay();
        } else if (payStyle.equals("微信")) {
            //调起微信支付
            weixinPay();
        } else {
            //银行卡支付
        }
    }

    private void goAliPay() {
        //        Toast.makeText(this, mOrder_num + "", Toast.LENGTH_SHORT).show();
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("type", "ali");
        arrayMap.put("order_num", mOrder_num);
        RetrofitManager.get(MyContants.BASEURL + "s=Payment/pay", arrayMap, new BaseObserver1<AliPayBean>("") {
            @Override
            public void onSuccess(AliPayBean result, String tag) {
                payPrice=result.getPay_price();
                final String orderStr = result.getOrderStr();
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(GoPayActivity.this);
                        String result = alipay.pay(orderStr, true);//调用支付接口，获取支付结果
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用，支付或者授权的行为需要在独立的非ui线程中执行
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(GoPayActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //-------------------------------------微信支付---------------------------------------------
    private void weixinPay() {
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(MyContants.BASEURL+"");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String str = null;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    br.close();
                    conn.disconnect();
                    // 拿到数据做解析
                    JSONObject json = new JSONObject(sb.toString());
                    if (null != json) {// && !json.has("retcode")
                        PayReq req = new PayReq();
                        req.appId = json.getString(WX_APPID);// 微信开放平台审核通过的应用APPID
                        req.partnerId = json.getString("partnerid");// 微信支付分配的商户号
                        req.prepayId = json.getString("prepayid");// 预支付订单号，app服务器调用“统一下单”接口获取
                        req.nonceStr = json.getString("noncestr");// 随机字符串，不长于32位，服务器小哥会给咱生成
                        req.timeStamp = json.getString("timestamp");// 时间戳，app服务器小哥给出
                        req.packageValue = json.getString("package");// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                        req.sign = json.getString("sign");// 签名，服务器小哥给出
                        //                        req.extData = "app data"; // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);//调起支付
                        finish();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
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
