package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.adapter.DingdanAdapter;
import com.example.administrator.lenglian.fragment.order.activity.AppraiseActivity;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;
import com.example.administrator.lenglian.fragment.order.activity.ReceiptActivity;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class AllDingFragment extends BaseFragment {

    private ListView list_recying;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private SpringView springview;
    private List<Dingdanbean.DatasBean> datas;//总
    private DingdanAdapter dindanadapter;
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected View initView() {
        //注册
        EventBus.getDefault().register(this);
        View view=View.inflate(mContext, R.layout.order_recying,null);
        list_recying = (ListView) view.findViewById(R.id.list_recying);
        springview = (SpringView) view.findViewById(R.id.springview);
        //设置类型
        springview.setType(SpringView.Type.FOLLOW);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.kong);
        textView = (TextView) view.findViewById(R.id.kong_text);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    protected void initData() {
        ininjson();

        //条目跳转
        list_recying.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                }
                else if("12".equals(datas.get(position).getOrder_status())){
                       return;
                }
                else {
                    Intent intent = new Intent(getActivity(), AppraiseActivity.class);
                    intent.putExtra("order_id", datas.get(position).getOrder_id());
                    startActivity(intent);
                }
            }
        });
        /*
          刷新
         */
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            ininjson();

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
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {

           if(eventMessage.getMsg().equals("unPayOrder")){

            ininjson();
        }
        else if(eventMessage.getMsg().equals("xufei")){
            ininjson();
        }
        else if(eventMessage.getMsg().equals("ping")){
            ininjson();
        }
        else if(eventMessage.getMsg().equals("pings")){
            ininjson();
        }
        else if(eventMessage.getMsg().equals("pay")){
            ininjson();
        }
        else if(eventMessage.getMsg().equals("dingshouhuo")){
            ininjson();
        }
        else if(eventMessage.getMsg().equals("tuihuan")){
            ininjson();
        }
        else if(eventMessage.getMsg().equals("qu")){
               ininjson();
           }
    }
    //全部订单
    private void ininjson() {
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL+"s=Order/listOrder", map, new BaseObserver1<Dingdanbean>("") {
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
                    // ToastUtils.showShort(getActivity(),"hahah");
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
              ininjson();

    }

}
