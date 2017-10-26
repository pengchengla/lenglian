package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.RegisterActivity;
import com.example.administrator.lenglian.activity.ZiLiaoActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
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
import com.example.administrator.lenglian.fragment.order.bean.Zhifubean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private List<Indexbean> list = new ArrayList<>();
    private String api;
    private List<Dingdanbean.DatasBean> datas;//总
    private List<Dingdanbean.DatasBean> datasp;//评价
    private List<Dingdanbean.DatasBean> datash;//收货
    private List<Zhifubean.DatasBean> datasf;//支付
    private SpringView springview;
    Payadapter payadapter;
    Deliveryadapter deliveryadapter;
    private Evaluateadapter evaluateadapyer;
    private LinearLayout mLl_parent;

    private DingdanAdapter dindanadapter;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private ImageView imageView;

    public static BlankFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("api", text);
        BlankFragment blankFragment = new BlankFragment();
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected View initView() {
        //注册
        EventBus.getDefault().register(this);
        View view = View.inflate(mContext, R.layout.order_recying, null);
        this.tv_back = (TextView) view.findViewById(R.id.tv_back);
        this.list_recying = (ListView) view.findViewById(R.id.list_recying);
        this.springview = (SpringView) view.findViewById(R.id.springview);
        //设置类型
        springview.setType(SpringView.Type.FOLLOW);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.kong);
        textView = (TextView) view.findViewById(R.id.kong_text);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //  接口
        if (!TextUtils.isEmpty(SpUtils.getString(getActivity(), "user_id", ""))) {


            api = getArguments().getString("api");
            if (api.equals(MyContants.BASEURL + "s=Order/listOrder")) {
                //网络请求
                ininjson();

            } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/order_status=1")) {
                //待支付
                Zhifu();

            } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/order_status=2,3,4")) {

                //待收货
                delivery();

            } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/is_comment=0/order_status=5,6,7,8,9,10")) {
                //待评价
                evaluate();

            }
        }

        /*
          -----------------------点击跳转
         */
        list_recying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (api.equals(MyContants.BASEURL + "s=Order/listOrder")) {
                    if ("1".equals(datas.get(position).getOrder_status())) {
                        Intent intent = new Intent(getActivity(), OrderPayActivity.class);
                        intent.putExtra("order_id", datas.get(position).getOrder_id());
                        String order_id = datas.get(position).getOrder_id();
                        KLog.a(order_id);
                        startActivity(intent);
                    } else if ("2".equals(datas.get(position).getOrder_status()) || "3".equals(datas.get(position).getOrder_status()) || "4".equals(datas.get(position).getOrder_status())) {
                        Intent it = new Intent(getActivity(), ReceiptActivity.class);
                        it.putExtra("order_id", datas.get(position).getOrder_id());
                        startActivity(it);
                    } else {
                        Intent intent = new Intent(getActivity(), AppraiseActivity.class);
                        intent.putExtra("order_id", datas.get(position).getOrder_id());
                        startActivity(intent);
                    }
                    //    Intent inetn=new Intent(this,class);
                    //跳支付
                } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/order_status=1")) {
                    Intent intent = new Intent(getActivity(), OrderPayActivity.class);
                    intent.putExtra("order_id", datasf.get(position).getOrder_id());
                    startActivity(intent);
                }
                //跳收货
                else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/order_status=2,3,4")) {
                    Intent it = new Intent(getActivity(), ReceiptActivity.class);
                    it.putExtra("order_id", datash.get(position).getOrder_id());
                    startActivity(it);
                }
                //跳评价
                else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/is_comment=0/order_status=5,6,7,8,9,10")) {
                    Intent intent = new Intent(getActivity(), AppraiseActivity.class);
                    intent.putExtra("order_id", datasp.get(position).getOrder_id());
                    startActivity(intent);
                }
            }
        });


        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (api.equals(MyContants.BASEURL + "s=Order/listOrder")) {
                            if (datas != null) {
                                dindanadapter.notifyDataSetChanged();
                            }

                        } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/order_status=1")) {
                            //待支付
//                            if(datasf!=null){
//                                payadapter.notifyDataSetChanged();
//                            }
                            Zhifu();


                        } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/order_status=2,3,4")) {

//                            //待收货
//                            if(datash!=null){
//                                deliveryadapter.notifyDataSetChanged();
//                            }
                            delivery();


                        } else if (api.equals(MyContants.BASEURL + "s=Order/listOrder/is_comment=5,6,7,8,9,10")) {
//                            //待评价
//                            if(datasp!=null){
//                                evaluateadapyer.notifyDataSetChanged();
//                            }
                            evaluate();


                        }
                        springview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springview.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        springview.setHeader(new DefaultHeader(getActivity()));
        springview.setFooter(new DefaultFooter(getActivity()));


    }


    private void evaluate() {
        //评价
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(mContext, "user_id", ""));
        map.put("token", MyUtils.getToken());
        map.put("order_status", "5,6,7,8,9,10");
        map.put("is_comment", "0");
        RetrofitManager.get(MyContants.BASEURL + "s=Order/listOrder", map, new BaseObserver1<Dingdanbean>("") {


            @Override
            public void onSuccess(Dingdanbean result, String tag) {
                if (result.getCode() == 200) {
                    relativeLayout.setVisibility(View.GONE);
                    list_recying.setVisibility(View.VISIBLE);
                    datasp = result.getDatas();
                    evaluateadapyer = new Evaluateadapter(getActivity(), datasp);
                    list_recying.setAdapter(evaluateadapyer);
                }
                else if (result.getCode() == 101) {
                    relativeLayout.setVisibility(View.VISIBLE);
                    list_recying.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.nodingdan);

                }

            }

            @Override
            public void onFailed(int code) {
            }
        });
    }

    //收货
    private void delivery() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(mContext, "user_id", ""));
        map.put("token", MyUtils.getToken());
        map.put("order_status","2,3,4");
        RetrofitManager.get(MyContants.BASEURL+"s=Order/listOrder", map, new BaseObserver1<Dingdanbean>("") {

            @Override
            public void onSuccess(Dingdanbean result, String tag) {
                if (result.getCode() == 200) {
                    relativeLayout.setVisibility(View.GONE);
                    list_recying.setVisibility(View.VISIBLE);
                    datash = result.getDatas();
                    deliveryadapter = new Deliveryadapter(getActivity(), datash);
                    list_recying.setAdapter(deliveryadapter);
                } else if (result.getCode() == 101) {
                    relativeLayout.setVisibility(View.VISIBLE);
                    list_recying.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.nodingdan);

                }
            }

            @Override
            public void onFailed(int code) {
                KLog.a(code);
                ToastUtils.showShort(getContext(), code+"");
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
                    if (result.getCode() == 200) {
                        relativeLayout.setVisibility(View.GONE);
                        list_recying.setVisibility(View.VISIBLE);
                            datas = result.getDatas();
                            dindanadapter = new DingdanAdapter(getActivity(), datas);
                            list_recying.setAdapter(dindanadapter);

                }
                    else if (result.getCode() == 101) {
                        relativeLayout.setVisibility(View.VISIBLE);
                        list_recying.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        imageView.setImageResource(R.drawable.nodingdan);

                    }
            }
            @Override
            public void onFailed(int code) {
            }
        });
    }
    //支付
    private void Zhifu() {
        ArrayMap map = new ArrayMap<String, String>();
        map.put("user_id",SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("order_status","1");
        KLog.a(api);
        RetrofitManager.get(MyContants.BASEURL+"s=Order/listOrder", map, new BaseObserver1<Zhifubean>("zhifu") {



            @Override
            public void onSuccess(Zhifubean result, String tag) {
                 if(result.getCode()==200) {
                     relativeLayout.setVisibility(View.GONE);
                     list_recying.setVisibility(View.VISIBLE);

                     if (tag.equals("zhifu")) {
                         datasf = result.getDatas();
                         payadapter = new Payadapter(getActivity(), datasf);
                         list_recying.setAdapter(payadapter);
                     }
                 }
                 else if (result.getCode() == 101) {
                     relativeLayout.setVisibility(View.VISIBLE);
                     list_recying.setVisibility(View.GONE);
                     textView.setVisibility(View.GONE);
                     imageView.setImageResource(R.drawable.nodingdan);

                 }
            }

            @Override
            public void onFailed(int code) {
            }
        });
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("fff")) {
            fff();
        }
       else   if(eventMessage.getMsg().equals("unPayOrder")){
             Zhifu();
            ininjson();
         }
        else if(eventMessage.getMsg().equals("xufei")){
            evaluate();

        }
        else if(eventMessage.getMsg().equals("ping")){
            evaluate();
        }
        else if(eventMessage.getMsg().equals("pay")){
                ininjson();
                 Zhifu();
        }
    }
    public void fff(){
        ArrayMap map = new ArrayMap<String, String>();
        map.put("user_id",SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("order_status","1");
        KLog.a(api);
        RetrofitManager.get(MyContants.BASEURL+"s=Order/listOrder", map, new BaseObserver1<Zhifubean>("zhifu") {



            @Override
            public void onSuccess(Zhifubean result, String tag) {

                if  (tag.equals("zhifu")) {
                    datasf = result.getDatas();
                    payadapter = new Payadapter(getActivity(), datasf);
                    list_recying.setAdapter(payadapter);
                }
            }

            @Override
            public void onFailed(int code) {
            }
        });
    }
}
