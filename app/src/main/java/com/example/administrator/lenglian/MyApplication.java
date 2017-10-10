package com.example.administrator.lenglian;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.administrator.lenglian.utils.SpUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2017/8/24.
 */

public class MyApplication extends LitePalApplication {
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        initUMShare();
        initUMPush();
        application=this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication  getApplication(){
        if(application == null){
            application = getApplication() ;
        }
        return application;
    }


    private void initTongJi() {
        //友盟统计
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);//普通统计场景类型
        /*
        调用 禁止默认的页面统计方式，这样将不会再自动统计Activity,这样就需要在每个fragment页面中单独的添加两个方法：
        MobclickAgent.onPageStart() 和 MobclickAgent.onPageEnd() 来统计页面跳转
         */
        MobclickAgent.openActivityDurationTrack(false);
        /*
        如果enable为true，SDK会对日志进行加密。加密模式可以有效防止网络攻击，提高数据安全性。
        如果enable为false，SDK将按照非加密的方式来传输日志。
        如果您没有设置加密模式，SDK的加密模式为false（不加密）。
        * */
        MobclickAgent.enableEncrypt(true);//6.0.0版本及以后
        /*
        使用集成测试之后，所有测试数据不会进入应用正式的统计后台，只能在“管理--集成测试--实时日志”里查看
        集成测试与普通测试两者都需要添加代码MobclickAgent.setDebugMode( true ) 打开调试模式，
        但是集成测试需要添加测试设备，集成测试隔离测试数据，普通的测试不区分测试数据与真实数据；
        集成测试的结果需在实时日志中查看，普通测试则是在友盟相关的log中查看。
         */
        MobclickAgent.setDebugMode(true);//防止测试产生的数据污染问题
        //捕获错误日志
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    private void initUMPush() {
        final PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //                Toast.makeText(MyApplication.this, "注册成功" + deviceToken, Toast.LENGTH_SHORT).show();
                System.out.println("友盟推送注册成功" + deviceToken);
                //注册成功会返回device token
                //                mRegistrationId = mPushAgent.getRegistrationId();
                //                Toast.makeText(MyApplication.this, "注册成功" + mRegistrationId, Toast.LENGTH_SHORT).show();
                //                SpUtils.putString(instance, "UMPUSHID", mRegistrationId);
                SpUtils.putString(application, "UMPUSHID", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                //                Toast.makeText(MyApplication.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUMShare() {
        Config.DEBUG = true;
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx76c60c8c929e5061", "9b4a4e4380a6012cf84956415af46523");
        PlatformConfig.setQQZone("1106303149", "xhxcwesbSLrse2xS");
        PlatformConfig.setSinaWeibo("557964441", "b52b29e8a5393bd34e2315e509fb5842", "http://www.baidu.com");//回调地址要跟微博开放平台的一样
    }

    public static Application getInstance() {
        return  application;
    }

    public static Context getGloableContext() {
        return  application.getApplicationContext();
    }
}
