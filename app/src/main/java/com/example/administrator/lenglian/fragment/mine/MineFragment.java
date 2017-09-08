package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.MessageActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;
import com.example.administrator.lenglian.utils.BaseDialog;

/**
 * Created by Administrator on 2017/8/24.ss
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    public TextView tv_msg_number;
    public LinearLayout ll_msg;
    public LinearLayout mine_set;
    public ImageView mine_head;
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

    @Override
    protected View initView() {
        View rootView = View.inflate(mContext, R.layout.activity_mine, null);
        tv_msg_number= (TextView) rootView.findViewById(R.id.tv_msg_number);
        ll_msg = (LinearLayout) rootView.findViewById(R.id.ll_msg);
        mine_set = (LinearLayout) rootView.findViewById(R.id.mine_set);
        mine_head = (ImageView) rootView.findViewById(R.id.mine_head);
        mine_phone = (TextView) rootView.findViewById(R.id.mine_phone);
        mine_name = (TextView) rootView.findViewById(R.id.mine_name);
        title_back = (RelativeLayout) rootView.findViewById(R.id.title_back);
        mine_wallet= (RelativeLayout) rootView.findViewById(R.id.one);
        mine_card= (RelativeLayout) rootView.findViewById(R.id.two);
        mine_Warranty= (RelativeLayout) rootView.findViewById(R.id.three);
        mine_alteration= (RelativeLayout) rootView.findViewById(R.id.four);
        mine_collection= (RelativeLayout) rootView.findViewById(R.id.five);
        mine_evaluate= (RelativeLayout) rootView.findViewById(R.id.six);
        mine_address= (RelativeLayout) rootView.findViewById(R.id.seven);
        mine_manual= (RelativeLayout) rootView.findViewById(R.id.eight);
        mine_kefu= (RelativeLayout) rootView.findViewById(R.id.nine);
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
        return rootView;
    }

    @Override
    protected void initData() {
        tv_msg_number.setText("11");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.three://我的保修
                Intent it=new Intent(getActivity(),WarrantyActivity.class);
                startActivity(it);
                break;
            case R.id.one://我的钱包
                Intent it1=new Intent(getActivity(),WalletActivity.class);
                startActivity(it1);
                break;
            case R.id.two://我的银行卡
                Intent it2=new Intent(getActivity(),CardActivity.class);
                startActivity(it2);
                break;
            case R.id.four://我的退还
                Intent it3=new Intent(getActivity(),AlterationActivity.class);
                startActivity(it3);
                break;
            case R.id.five://我的收藏
                Intent it4=new Intent(getActivity(),CollectionActivity.class);
                startActivity(it4);
                break;
            case R.id.six://我的评价
                Intent it5=new Intent(getActivity(),EvaluateActivity.class);
                startActivity(it5);
                break;
            case R.id.seven://我的地址
                Intent it6=new Intent(getActivity(),AddressActivity.class);
                startActivity(it6);
                break;
            case R.id.eight://用户指南
                Intent it7=new Intent(getActivity(),ManualActivity.class);
                startActivity(it7);
                break;
            case R.id.nine://打电话
                showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
            case R.id.mine_set:
                Intent set=new Intent(getActivity(),SetActivity.class);
                startActivity(set);
                break;
            case R.id.mine_head:
                 Intent head=new Intent(getActivity(),PersoninforActivity.class);
                startActivity(head);
                break;
            case R.id.ll_msg://消息
                 startActivity(new Intent(mContext, MessageActivity.class));
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
