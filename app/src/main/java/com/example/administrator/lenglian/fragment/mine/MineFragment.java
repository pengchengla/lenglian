package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.LoginActivity;
import com.example.administrator.lenglian.activity.MessageActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Personbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.view.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/24.ss
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    public TextView tv_msg_number;
    public LinearLayout ll_msg;
    public LinearLayout mine_set;
    public CircleImageView mine_head;
    public TextView mine_phone;
    public TextView mine_name;
    public RelativeLayout title_back;
    public RelativeLayout mine_wallet;
    public RelativeLayout mine_card;
    public RelativeLayout mine_Warranty;
    public RelativeLayout mine_alteration;
    public RelativeLayout mine_collection;
    public RelativeLayout mine_evaluate;
    public RelativeLayout mine_address;
    public RelativeLayout mine_manual;
    public RelativeLayout mine_kefu;
    public TextView tv_back;
    private TextView mine_login;
    private List<Personbean.DatasBean> datas;
    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.activity_mine, null);
        tv_msg_number = (TextView) rootView.findViewById(R.id.tv_msg_number);
        ll_msg = (LinearLayout) rootView.findViewById(R.id.ll_msg);
        mine_set = (LinearLayout) rootView.findViewById(R.id.mine_set);
        mine_head = (CircleImageView) rootView.findViewById(R.id.mine_head);
        mine_phone = (TextView) rootView.findViewById(R.id.mine_phone);
        mine_name = (TextView) rootView.findViewById(R.id.mine_name);
        title_back = (RelativeLayout) rootView.findViewById(R.id.title_back);
        mine_wallet = (RelativeLayout) rootView.findViewById(R.id.one);
        mine_card = (RelativeLayout) rootView.findViewById(R.id.two);
        mine_Warranty = (RelativeLayout) rootView.findViewById(R.id.three);
        mine_alteration = (RelativeLayout) rootView.findViewById(R.id.four);
        mine_collection = (RelativeLayout) rootView.findViewById(R.id.five);
        mine_evaluate = (RelativeLayout) rootView.findViewById(R.id.six);
        mine_address = (RelativeLayout) rootView.findViewById(R.id.seven);
        mine_manual = (RelativeLayout) rootView.findViewById(R.id.eight);
        mine_kefu = (RelativeLayout) rootView.findViewById(R.id.nine);
        mine_login = (TextView) rootView.findViewById(R.id.mine_login);
        //设置监听
        mine_Warranty.setOnClickListener(this);
        mine_wallet.setOnClickListener(this);
        mine_card.setOnClickListener(this);
        mine_wallet.setOnClickListener(this);
        mine_alteration.setOnClickListener(this);
        mine_collection.setOnClickListener(this);
        mine_evaluate.setOnClickListener(this);
        mine_address.setOnClickListener(this);
        mine_manual.setOnClickListener(this);
        mine_kefu.setOnClickListener(this);
        mine_set.setOnClickListener(this);
        mine_head.setOnClickListener(this);
        ll_msg.setOnClickListener(this);
        mine_login.setOnClickListener(this);
        if (!TextUtils.isEmpty(SpUtils.getString(mContext, "user_id", ""))) {
            mine_login.setVisibility(View.GONE);
            mine_name.setVisibility(View.VISIBLE);
            mine_phone.setVisibility(View.VISIBLE);
        }




        return rootView;
    }
    private void ppp() {
        if (!TextUtils.isEmpty(SpUtils.getString(mContext, "user_id", ""))) {
            mine_login.setVisibility(View.GONE);
            mine_name.setVisibility(View.VISIBLE);
            mine_phone.setVisibility(View.VISIBLE);
            //取头像
            String photo = SpUtils.getString(getActivity(), "photo", "");
            //加载数据
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(photo)
                    .apply(options)
                    .into(mine_head);
            if (!TextUtils.isEmpty(SpUtils.getString(getActivity(), "nick_name", ""))) {
                mine_name.setText(SpUtils.getString(getActivity(), "nick_name", ""));

            }
        }
    }

    private void xxx(){
        if (!TextUtils.isEmpty(SpUtils.getString(mContext, "user_id", ""))) {
            mine_login.setVisibility(View.GONE);
            Map<String, String> map = new HashMap<>();
            map.put("user_id", SpUtils.getString(getActivity(), "user_id", ""));
            map.put("token", MyUtils.getToken());
            RetrofitManager.get(MyContants.BASEURL + "s=User/viewProfile", map, new BaseObserver1<Personbean>("") {

                @Override
                public void onSuccess(Personbean result, String tag) {

                    if (result.getCode() == 200) {
                        datas = result.getDatas();
                        //加载数据
                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .error(R.drawable.default_square)
                                .priority(Priority.NORMAL)
                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                        Glide.with(mContext).load(datas.get(0).getHead())
                                .apply(options)
                                .into(mine_head);
                        //昵称
                        mine_name.setText(datas.get(0).getUser_name());
                        //手机号
                        String phone = SpUtils.getString(getActivity(), "phone", "");
                        mine_phone.setText(phone);

                        mine_name.setVisibility(View.VISIBLE);
                        mine_phone.setVisibility(View.VISIBLE);

                    }
                }

                @Override
                public void onFailed(int code) {

                }
            });
        }else {
            mine_head.setImageResource(R.drawable.default_circle);
            mine_login.setVisibility(View.VISIBLE);
            mine_name.setVisibility(View.GONE);
            mine_phone.setVisibility(View.GONE);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("xxx")) {
            xxx();
        }
        else  if(eventMessage.getMsg().equals("ppp")){
           ppp();
                }
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        tv_msg_number.setText("11");
        //网络请求
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(getActivity(), "user_id", ""));
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=User/viewProfile", map, new BaseObserver1<Personbean>("") {

            @Override
            public void onSuccess(Personbean result, String tag) {

                if (result.getCode() == 200) {
                    datas = result.getDatas();
                    //加载数据
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .error(R.drawable.default_square)
                            .priority(Priority.NORMAL)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                    Glide.with(mContext).load(datas.get(0).getHead())
                            .apply(options)
                            .into(mine_head);
                    //昵称
                    mine_name.setText(datas.get(0).getUser_name());
                    //手机号
                    String phone = SpUtils.getString(getActivity(), "phone", "");
                    mine_phone.setText(phone);


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
            case R.id.three://我的保修
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                } else {
                    Intent it = new Intent(getActivity(), WarrantyActivity.class);
                    startActivity(it);
                }
                break;
            case R.id.one://我的钱包
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                } else {
                    Intent it1 = new Intent(getActivity(), WalletActivity.class);
                    startActivity(it1);
                }

                break;
            case R.id.two://我的银行卡
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }else {
                    Intent it2 = new Intent(getActivity(), CardActivity.class);

                    startActivity(it2);
                }
                break;
            case R.id.four://我的退还
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                } else {
                    Intent it3 = new Intent(getActivity(), AlterationActivity.class);

                    startActivity(it3);
                }
                break;
            case R.id.five://我的收藏
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                } else {
                    Intent it4 = new Intent(getActivity(), CollectionActivity.class);

                    startActivity(it4);
                }
                break;
            case R.id.six://我的评价
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }else {
                    Intent it5 = new Intent(getActivity(), EvaluateActivity.class);
                    startActivity(it5);
                }
                break;
            case R.id.seven://我的地址
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }else {
                    Intent it6 = new Intent(getActivity(), AddressActivity.class);

                    startActivity(it6);
                }
                break;
            case R.id.eight://用户指南
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }else {
                    Intent it7 = new Intent(getActivity(), ManualActivity.class);
                    startActivity(it7);
                }
                break;
            case R.id.nine://打电话
                showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
            case R.id.mine_set:
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }else {
                    Intent set = new Intent(getActivity(), SetActivity.class);
                    startActivity(set);
                }

                break;
            case R.id.mine_head:
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }else {
                    Intent head = new Intent(getActivity(), PersoninforActivity.class);
                    startActivity(head);
                }
                break;
            case R.id.ll_msg://消息
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                } else {
                    startActivity(new Intent(mContext, MessageActivity.class));
                }
                break;
            case R.id.mine_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                break;
        }

    }

    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(mContext);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_phone)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText("确认拨打010-12345674？");
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-12345674"));
                //跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);
            }
        });
    }

}
