package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.util.Rfc822Token;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.RegisterActivity;
import com.example.administrator.lenglian.activity.ZiLiaoActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.RegisterBean;
import com.example.administrator.lenglian.fragment.mine.adapter.DingdanAdapter;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.order.activity.AppraiseActivity;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;
import com.example.administrator.lenglian.fragment.order.activity.ReceiptActivity;
import com.example.administrator.lenglian.fragment.order.adapter.Deliveryadapter;
import com.example.administrator.lenglian.fragment.order.adapter.Evaluateadapter;
import com.example.administrator.lenglian.fragment.order.adapter.Payadapter;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(api.equals(MyContants.BASEURL+"s=Order/listOrder")){
            //网络请求
            ininjson();
        }
        else if(api.equals(MyContants.BASEURL +"s=Order/listOrder/order_status=10")){
            //待支付
            Zhifu();
        }
        else if(api.equals(MyContants.BASEURL +"s=Order/listOrder/order_status=1")){

               //待收货
            delivery();
        }
        else if(api.equals(MyContants.BASEURL +"s=Order/listOrder/order_status=8")){
            //待评价
            evaluate();
        }

       //点击跳转
        list_recying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (api.equals("第一个接口")){
                //    Intent inetn=new Intent(this,class);
                    //跳支付
                }else if (api.equals(MyContants.BASEURL +"s=Order/listOrder/order_status=10")){
                    Intent intent=new Intent(getActivity(), OrderPayActivity.class);
                    startActivity(intent);
                }
                //跳收货
                else  if(api.equals(MyContants.BASEURL +"s=Order/listOrder/order_status=1")){
                      Intent it=new Intent(getActivity(), ReceiptActivity.class);
                    startActivity(it);
                }
                else if(api.equals(MyContants.BASEURL +"s=Order/listOrder/order_status=8")){
                       Intent intent=new Intent(getActivity(), AppraiseActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private void evaluate() {
        //评价
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(api, map, new BaseObserver1<Dingdanbean>("") {
            @Override
            public void onSuccess(Dingdanbean result, String tag) {
                Toast.makeText(getActivity(), result.getMsg()+"hahah", Toast.LENGTH_SHORT).show();
                Indexbean index;
                for (int i = 0; i < 5; i++) {
                    index=new Indexbean();
                    index.setCount("哈发发嘎啊发发发阿发啊啊"+i);
                    list.add(index);
                }
               Evaluateadapter evaluateadapyer=new  Evaluateadapter (getActivity(),list);
                list_recying.setAdapter(evaluateadapyer);
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(getActivity(), "注册失败，请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //收货
    private void delivery() {

        Map<String,String> map=new HashMap<>();
        map.put("user_id",SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(api, map, new BaseObserver1<Dingdanbean>("") {
            @Override
            public void onSuccess(Dingdanbean result, String tag) {
                Toast.makeText(getActivity(), result.getMsg()+"hahah", Toast.LENGTH_SHORT).show();
                Indexbean index;
                for (int i = 0; i < 5; i++) {
                    index=new Indexbean();
                    index.setCount("哈发发嘎啊发发发阿发啊啊"+i);
                    list.add(index);
                }
                Deliveryadapter deliveryadapter=new Deliveryadapter(getActivity(),list);
                list_recying.setAdapter(deliveryadapter);
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(getActivity(), "注册失败，请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
   //全部订单
    private void ininjson() {

        Map<String,String> map=new HashMap<>();
          map.put("user_id",SpUtils.getString(mContext,"user_id",""));
          map.put("token", MyUtils.getToken());
        RetrofitManager.get(api, map, new BaseObserver1<Dingdanbean>("") {
            @Override
            public void onSuccess(Dingdanbean result, String tag) {
                Toast.makeText(getActivity(), result.getMsg()+"hahah", Toast.LENGTH_SHORT).show();
                if(result.getCode()==200){
                    List<Dingdanbean.DatasBean> datas = result.getDatas();
                    DingdanAdapter dindanadapter=new DingdanAdapter(getActivity(),datas);
                    list_recying.setAdapter(dindanadapter);
                }
//                Indexbean index;
//                for (int i = 0; i < 5; i++) {
//                    index=new Indexbean();
//                    index.setCount("哈发发嘎啊发发发阿发啊啊"+i);
//                    list.add(index);
//                }

            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(getActivity(), "注册失败，请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //支付
    private void Zhifu() {
        ArrayMap map = new ArrayMap<String, String>();
        map.put("user_id",SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(api, map, new BaseObserver1<Dingdanbean>("") {
            @Override
            public void onSuccess(Dingdanbean result, String tag) {

                        Toast.makeText(getActivity(), result.getCode()+"哈哈", Toast.LENGTH_SHORT).show();
                Indexbean index;
                for (int i = 0; i < 5; i++) {
                    index=new Indexbean();
                    index.setCount("哈发发嘎啊发发发阿发啊啊"+i);
                    list.add(index);
                }
                Payadapter payadapter=new Payadapter(getActivity(),list);
                list_recying.setAdapter(payadapter);
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(getActivity(), "注册失败，请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
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
//        DingdanAdapter dindanadapter=new DingdanAdapter(getActivity(),list);
//        list_recying.setAdapter(dindanadapter);
    }




}
