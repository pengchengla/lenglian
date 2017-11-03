package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.order.activity.OrderPayActivity;
import com.example.administrator.lenglian.fragment.order.adapter.Dingadapter;
import com.example.administrator.lenglian.fragment.order.adapter.Payadapter;
import com.example.administrator.lenglian.fragment.order.adapter.Zhifuadapter;
import com.example.administrator.lenglian.fragment.order.bean.Zhifubean;
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

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class ZhifuFragment extends BaseFragment {
    private List<Zhifubean.DatasBean> datasf;//支付
    private SpringView springview;
    Payadapter payadapter;
    private TextView textView;
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private RecyclerView list_recying;
    @Override

    protected void lazyLoad() {

    }

    @Override
    protected View initView() {
        //注册
        EventBus.getDefault().register(this);
        View view = View.inflate(mContext, R.layout.order_recying, null);
        list_recying = (RecyclerView) view.findViewById(R.id.list_recying);
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
        Zhifu();

        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            Zhifu();
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
        if (eventMessage.getMsg().equals("fff")) {
            Zhifu();
        }
        else   if(eventMessage.getMsg().equals("unPayOrder")){
            Zhifu();
        }
        else if(eventMessage.getMsg().equals("pay")){
            Zhifu();
        }
        else if(eventMessage.getMsg().equals("quxiao")){
            Zhifu();
        }
    }
    //支付
    private void Zhifu() {
        ArrayMap map = new ArrayMap<String, String>();
        map.put("user_id", SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("order_status","1");
        RetrofitManager.get(MyContants.BASEURL+"s=Order/listOrder", map, new BaseObserver1<Zhifubean>("zhifu") {
            @Override
            public void onSuccess(Zhifubean result, String tag) {
                if(result.getCode()==200) {
                    relativeLayout.setVisibility(View.GONE);
                    list_recying.setVisibility(View.VISIBLE);

                    if (tag.equals("zhifu")) {
                        datasf = result.getDatas();
                        Zhifuadapter zhifuadapter=new Zhifuadapter(getActivity(),datasf);
                        list_recying.setLayoutManager(new LinearLayoutManager(getActivity()));
                        list_recying.setAdapter(zhifuadapter);
                        zhifuadapter.setOnItemClickListener(new Dingadapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(), OrderPayActivity.class);
                                intent.putExtra("order_id", datasf.get(position).getOrder_id());
                                startActivity(intent);
                            }
                        });
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
          Zhifu();
    }
}
