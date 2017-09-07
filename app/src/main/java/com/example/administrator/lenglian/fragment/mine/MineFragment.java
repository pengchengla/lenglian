package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;

/**
 * Created by Administrator on 2017/8/24.ss
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    public TextView tv_msg_number;
    public LinearLayout ll_msg;
    public TextView mine_set;
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
        mine_set = (TextView) rootView.findViewById(R.id.mine_set);
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
        return rootView;
    }

    @Override
    protected void initData() {

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
            case R.id.nine:
                break;
            case R.id.mine_set:
                Intent set=new Intent(getActivity(),SetActivity.class);
                startActivity(set);
                break;
            case R.id.mine_head:
                 Intent head=new Intent(getActivity(),PersoninforActivity.class);
                startActivity(head);
                break;
        }
    }





}
