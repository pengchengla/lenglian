package com.example.administrator.lenglian.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.order.adapter.Diagpaydapter;
import com.example.administrator.lenglian.fragment.order.bean.Bank;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.pay.AliPayBean;
import com.example.administrator.lenglian.pay.PayResult;
import com.example.administrator.lenglian.pay.PaySuccessActivity;
import com.example.administrator.lenglian.pay.WXPayBean;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PayUtil {
      private Context context;
     private String order_num;
    private String payPrice;
    private static final int SDK_PAY_FLAG = 1;
    private int num;
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wx76c60c8c929e5061";// 微信appid
    public PayUtil(Context context,String order_num,int num) {
        this.context = context;
        this.order_num=order_num;
        this.num=num;
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, WX_APPID, false);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
    }

    private static Bank bank;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    Toast.makeText(context, " " + payResult.getResultStatus(), Toast.LENGTH_SHORT).show();
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                        //自己写的跳转到自定义的支付宝支付成功界面
                        Intent intent = new Intent(context, PaySuccessActivity.class);
                        intent.putExtra("price", payPrice);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(context, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    //支付
    public void showGenderDialog(int grary, int animationStyle, final Context context) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_pay)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        //Listview bank
       ListView viewById = (ListView) dialog.getView(R.id.pay_listview).findViewById(R.id.pay_listview);
         final List<Bank> list=new ArrayList<>();
         Bank bank1=new Bank();
        bank1.setName("支付宝支付");
        list.add(bank1);
        Bank bank2=new Bank();
        bank2.setName("微信支付");
        list.add(bank2);
//        for (int i = 0; i <10; i++) {
//            bank = new Bank();
//            bank.setName("中国银行"+i);
//           list.add(bank);
//
//        }
       Diagpaydapter diagpaydapter= new Diagpaydapter(list,context);
        viewById.setAdapter(diagpaydapter);//添加适配器
        diagpaydapter.notifyDataSetChanged();
        viewById.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  if(list.get(position).getName().equals("支付宝支付")){
                         goAliPay();
                      dialog.dismiss();
                      //调用支付宝

                  }
                else if("微信支付".equals(list.get(position).getName())){
                      weixinPay();
                      dialog.dismiss();

                  }
                  //银行卡支付
                else {
                      ToastUtils.showShort(context,list.get(position).getName());
                  }
            }
        });
        dialog.getView(R.id.zhifu_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
     /*
        支付宝支付
      */
    private void goAliPay() {
        //        Toast.makeText(this, mOrder_num + "", Toast.LENGTH_SHORT).show();
        ArrayMap arrayMap = new ArrayMap<String, String>();
          if(num==1){
              arrayMap.put("order_num", order_num);
          }
        else if(num==2){
              arrayMap.put("renewal_num", order_num);
          }
        arrayMap.put("type", "ali");

        RetrofitManager.get(MyContants.BASEURL + "s=Payment/pay", arrayMap, new BaseObserver1<AliPayBean>("") {
            @Override
            public void onSuccess(AliPayBean result, String tag) {
                AliPayBean.DatasEntity datas = result.getDatas();
                payPrice = datas.getPay_price();
                final String orderStr = datas.getOrderStr();
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask((Activity) context);
                        String result = alipay.pay(orderStr, true);//调用支付接口，获取支付结果
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                        EventMessage eventMessage = new EventMessage("pay");
                        EventBus.getDefault().postSticky(eventMessage);
                        EventMessage eventMessages = new EventMessage("renew");
                        EventBus.getDefault().postSticky(eventMessage);

                    }
                };

                // 必须异步调用，支付或者授权的行为需要在独立的非ui线程中执行
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
            @Override
            public void onFailed(int code) {
                Toast.makeText(context, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
     /*
       微信支付
      */

    private void weixinPay() {
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("type", "wx");
        if(num==1){
            arrayMap.put("order_num", order_num);
        }
        else if(num==2){
            arrayMap.put("renewal_num", order_num);
        }
        RetrofitManager.get(MyContants.BASEURL + "s=Payment/pay", arrayMap, new BaseObserver1<WXPayBean>("") {
            @Override
            public void onSuccess(WXPayBean result, String tag) {
                PayReq req = new PayReq();
                WXPayBean.DatasEntity datas = result.getDatas();
                req.appId = datas.getAppid();// 微信开放平台审核通过的应用APPID
                req.partnerId = datas.getPartnerid();// 微信支付分配的商户号
                req.prepayId = datas.getPrepayid();// 预支付订单号，app服务器调用“统一下单”接口获取
                req.nonceStr = datas.getNoncestr();// 随机字符串，不长于32位，服务器小哥会给咱生成
                req.timeStamp = datas.getTimestamp();// 时间戳，app服务器小哥给出
                req.packageValue = datas.getPackage1();// 固定值Sign=WXPay，可以直接写死，服务器返回的也是这个固定值
                req.sign = datas.getSign();// 签名，服务器小哥给出
                //                        req.extData = "app data"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);//调起支付
                //微信支付的金额我用sp存的，之前用eventbus不管用
                SpUtils.putString(context,"wxprice",datas.getPay_price());
                EventMessage eventMessage = new EventMessage("pay");
                EventBus.getDefault().postSticky(eventMessage);
                EventMessage eventMessages = new EventMessage("renew");
                EventBus.getDefault().postSticky(eventMessages);
            }
            @Override
            public void onFailed(int code) {
                Toast.makeText(context, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
