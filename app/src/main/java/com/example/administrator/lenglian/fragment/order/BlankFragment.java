package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.fragment.mine.adapter.DingdanAdapter;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 */

public class BlankFragment extends BaseFragment {

    private TextView tv_back;
    private ListView list_recying;
    private List<Indexbean> list=new ArrayList<>();
    private String api;

    public static BlankFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("api", text);
        BlankFragment blankFragment = new BlankFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.order_recying, null);
        this.tv_back = (TextView) view.findViewById(R.id.tv_back);
        this.list_recying = (ListView) view.findViewById(R.id.list_recying);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //  接口
        api = getArguments().getString("api");
       //网络请求

       //点击跳转
        list_recying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (api.equals("第一个接口")){
                //    Intent inetn=new Intent(this,class);
                }else if (api.equals("第二个接口")){

                }
            }
        });


    }

    @Override
    protected void initData() {
        Indexbean index;
        for (int i = 0; i < 5; i++) {
            index=new Indexbean();
            index.setCount("哈发发嘎啊发发发阿发啊啊"+i);
            list.add(index);
        }
        DingdanAdapter dindanadapter=new DingdanAdapter(getActivity(),list);
        list_recying.setAdapter(dindanadapter);
    }




}
