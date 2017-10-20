package com.example.administrator.lenglian.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.widget.Toast;

import com.example.administrator.lenglian.MyApplication;
import com.example.administrator.lenglian.activity.MainActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.bean.ThreeLoginBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.SpUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by lxk on 2017/7/3.
 */

public class UMLoginActivity extends BaseActivity {

    private static Activity mContext;

    /*
            * 授权中只是能拿到uid，openid，token这些授权信息，想获取用户名和用户资料，需要使用这个接口
            * 其中umAuthListener为授权回调，构建如下，其中授权成功会回调onComplete，取消授权回调onCancel，
            * 授权错误回调onError，对应的错误信息可以用过onError的Throwable参数来打印
            * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected static void loginBySina(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.SINA, umAuthListener);
    }

    protected static void loginByWeiXin(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.WEIXIN, umAuthListener);
    }

    protected static void loginByQQ(Activity context) {
        mContext = context;
        UMShareAPI.get(context).getPlatformInfo(context, SHARE_MEDIA.QQ, umAuthListener);
    }

    private static UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
            //            Toast.makeText(MyApplication.getGloableContext(), "授权开始回调", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            /*
        * 登录成功后，第三方平台会将用户资料传回， 全部会在Map data中返回 ，由于各个平台对于用户资料的标识不同，
        * 因此为了便于开发者使用，我们将一些常用的字段做了统一封装，开发者可以直接获取，
        * 不再需要对不同平台的不同字段名做转换，这里列出我们封装的字段及含义
        * */
            Toast.makeText(MyApplication.getGloableContext(), "登陆成功", Toast.LENGTH_SHORT).show();
            final String username = data.get("name");
            final String userhead = data.get("iconurl");
            final String uid = data.get("uid");
            //            SpUtils.putString(MyApplication.getGloableContext(), "threeid", uid);
            //            SpUtils.putString(MyApplication.getGloableContext(), "logintype", "three");
            String type = "";
            if (platform.equals(SHARE_MEDIA.QQ)) {
                type = "qq";
            } else if (platform.equals(SHARE_MEDIA.WEIXIN)) {
                type = "weixin";
            } else if (platform.equals(SHARE_MEDIA.SINA)) {
                type = "weibo";
            }
            //            SpUtils.putString(MyApplication.getGloableContext(), "threetype", type);
            ArrayMap arrayMap2 = new ArrayMap();
            arrayMap2.put("openid", uid);
            arrayMap2.put("type", type);
//            arrayMap2.put("mobile", "");
            RetrofitManager.post(MyContants.BASEURL + "s=User/nt_login", arrayMap2, new BaseObserver1<ThreeLoginBean>("") {
                @Override
                public void onSuccess(ThreeLoginBean result, String tag) {
                    if (result.getCode() == 200) {
                        SpUtils.putString(mContext, "user_id", result.getData().getUser_id());
                        SpUtils.putString(mContext, "phone", result.getData().getMobile());
                        if (mContext.getIntent().getStringExtra("gologin").equals("gologin")) {
                            mContext.finish();
                            EventMessage eventMessage = new EventMessage("xxx");
                            EventBus.getDefault().postSticky(eventMessage);
                            EventMessage eventMessages = new EventMessage("bbb");
                            EventBus.getDefault().postSticky(eventMessages);
                        } else {
                            Intent intent1 = new Intent(mContext, MainActivity.class);
                            mContext.startActivity(intent1);
                        }
                    } else {
                        //101是没有数据
                    }
                }

                @Override
                public void onFailed(int code) {
                    Toast.makeText(mContext, "登录失败"+code, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MyApplication.getGloableContext(), "登陆失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MyApplication.getGloableContext(), "取消登录", Toast.LENGTH_SHORT).show();
        }
    };

    /*
    * 最后在登录所在的Activity里复写onActivityResult方法,注意不可在fragment中实现，如果在fragment中调用登录，
    * 就在fragment依赖的Activity中实现，如果不实现onActivityResult方法，会导致登录或回调无法正常进行
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
