package com.example.administrator.lenglian.pay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.lenglian.base.BaseActivity;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Administrator on 2017/10/9.
 */

public class PayUtilsActivity extends BaseActivity {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private String WX_APPID = "wx76c60c8c929e5061";// 微信appid
    private static final int SDK_PAY_FLAG = 1;
    // 支付宝商户PID
    public static final String PARTNER = PayKeys.DEFAULT_PARTNER;
    // 支付宝商户收款账号
    public static final String SELLER = PayKeys.DEFAULT_SELLER;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, WX_APPID, false);
        // 将该app注册到微信
        api.registerApp(WX_APPID);
    }

    //-------------------------------------支付宝支付---------------------------------------------
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayUtilsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //自己写的跳转到自定义的支付宝支付成功界面
                        Intent intent = new Intent(PayUtilsActivity.this, PaySuccessActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        /*
                        "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，
                        最终交易是否成功以服务端异步通知为准（小概率状态）
                         */
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayUtilsActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayUtilsActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * call alipay sdk pay. 调用SDK支付--支付宝
     */
    private void aliPay() {
        //第3个参数是商品价格，别忘了将总价格中的特殊符号置换为空
        String orderInfo = getOrderInfo("商品名称", "商品详情",
                //                tvShopcartTotal.getText().toString().replace("￥", "")
                0.01 + ""
        );//总价格

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = "";//调用服务器接口获取签名
        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                /*
                  PayTask接口---开发包提供支付，查询的对象接口。
                 构造PayTask 对象，PayTask对象主要为商户提供订单支付功能，及获取当前开发包版本号。
                  */
                PayTask alipay = new PayTask(PayUtilsActivity.this);
                /*
                第一个参数主要包含商户的订单信息，key=“value”形式，以&连接。
                第二个参数表示用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，
                这个值设置为true，将会在调用pay接口的时候直接唤起一个loading，
                直到唤起H5支付页面或者唤起外部的钱包付款页面loading才消失。
                （建议将该值设置为true，优化点击付款到支付唤起支付页面的过渡过程。）
                 */
                String result = alipay.pay(payInfo, true);//调用支付接口，获取支付结果

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


    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }


    /**
     * create the order info. 创建订单信息
     */

    private String getOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        /*
         服务器异步通知页面路径
         商户需要提供一个http协议的接口，包含在参数里传递给快捷支付，即notify_url。
         支付宝服务器在支付完成后，会以POST方式调用notify_url，以xml数据格式传输支付结果。
          */
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    //-------------------------------------微信支付---------------------------------------------
    private void weixinPay() {
        /*1.客户端代码得到用户购买的商品信息，将之传给自己公司app服务器，参数包含但不限于以下：
        *   params.put("appid", appID);// 微信appid，选择性上传，服务器写死亦可
            params.put("money", money);// 支付金额，单位：分
            params.put("name", goodsName);// 商品名称
            params.put("currencytype", "CNY");// 支付币种，只能填CNY，代表人民币
            备注：以上是参数字段是我们公司服务器定义，给服务器用以获取预支付订单号prePayId用。
            备注2：详细字段请参考：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
            2.app服务器调用微信“统一下单”接口，得到prePayId订单号并返回prePayId给手机客户端；
            3.手机客户端使用prePayId及商品信息调起微信客户端进行支付；
                3.1用户操作：输入密码进行支付；返回键取消支付；网络无连接支付失败等；
            4.微信客户端回调支付结果给咱们的APP客户端；
            5.微信服务器异步通知咱们公司app服务器支付结果（服务器的工作，与客户端无关）
        * */
        //联网访问我们的app服务器,上传商品信息，获取订单数据
        new Thread() {
            public void run() {
                try {
                    URL url = new URL("");
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
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

}
