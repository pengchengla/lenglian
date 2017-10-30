package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.LoginActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/8/24.
 */

public class OrderFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout tab;
    private ArrayList<String> apiList;
    private List<String> list;
    private FrameLayout pager;
    private List<Indexbean> lists=new ArrayList<>();
    private LinearLayout liner_tab;
    private Button view_btn;
    private RelativeLayout real_login;
    private TextView all_dingdan;
    private TextView ding_zhifu;
    private TextView ding_shouhuo;
    private TextView ding_pinjia;
    private View v1;
    private View v2;
    private View v3;
    private View v4;
    private Fragment currentf;
    private AllDingFragment allDingFragment;
    private ZhifuFragment zhifuFragment;
    private ShouhuoFragment shouhuoFragment;
    private PingjiaFragment pingjiaFragment;


    @Override
    protected void lazyLoad() {

    }

    @Override
    protected View initView() {
        EventBus.getDefault().register(this);
        View view=View.inflate(mContext, R.layout.activity_mineorder,null);
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager = (FrameLayout) view.findViewById(R.id.container);
        liner_tab = (LinearLayout) view.findViewById(R.id.liner_tab);
        view_btn = (Button) view.findViewById(R.id.view_btn);
        real_login = (RelativeLayout)view.findViewById(R.id.real_login);
        /*
        ----------------------------------------------------------
         */
        all_dingdan = (TextView) view.findViewById(R.id.alldingdan);
        ding_zhifu = (TextView) view.findViewById(R.id.ding_zhifu);
        ding_shouhuo = (TextView) view.findViewById(R.id.ding_shuohuo);
        ding_pinjia = (TextView) view.findViewById(R.id.ding_pinjia);
        v1 = view.findViewById(R.id.v1);
        v2 = view.findViewById(R.id.v2);
        v3 = view.findViewById(R.id.v3);
        v4 = view.findViewById(R.id.v4);
        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        v3.setOnClickListener(this);
        v4.setOnClickListener(this);
        all_dingdan.setOnClickListener(this);
        ding_zhifu.setOnClickListener(this);
        ding_shouhuo.setOnClickListener(this);
        ding_pinjia.setOnClickListener(this);
        allDingFragment=new AllDingFragment();
        addFragments(allDingFragment);


        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("gologin", "gologin");
                startActivity(intent);

            }
        });

        if (!TextUtils.isEmpty( SpUtils.getString(mContext,"user_id",""))){
              real_login.setVisibility(View.GONE);
            liner_tab.setVisibility(View.VISIBLE);

        }
        return view;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("bbb")) {
            bbb();
        }
    }
      private void bbb(){
          if (!TextUtils.isEmpty( SpUtils.getString(mContext,"user_id",""))){
              real_login.setVisibility(View.GONE);
              liner_tab.setVisibility(View.VISIBLE);


          }
          else {
              real_login.setVisibility(View.VISIBLE);
              liner_tab.setVisibility(View.GONE);

          }

      }

    @Override
    protected void initData() {
//        LinearLayout linearLayout = (LinearLayout) tab.getChildAt(0);
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
//                R.drawable.layout_divider));
//        list = new ArrayList<>();
//        list.add(String.format(Locale.CHINA, "全部订单"));
//        list.add(String.format(Locale.CHINA, "待支付"));
//        list.add(String.format(Locale.CHINA, "待收货"));
//        list.add(String.format(Locale.CHINA, "待评价"));
//        apiList = new ArrayList<>();
//        //放接口
//        apiList.add(MyContants.BASEURL+"s=Order/listOrder");
//        //支付
//        apiList.add(MyContants.BASEURL+"s=Order/listOrder/order_status=1");
//        //收货
//        apiList.add(MyContants.BASEURL+"s=Order/listOrder/order_status=2,3,4");
//        //评价
//        apiList.add(MyContants.BASEURL+"s=Order/listOrder/is_comment=0/order_status=5,6,7,8,9,10");
//        for (int i = 0; i < list.size(); i++) {
//            tab.addTab(tab.newTab().setText(list.get(i)));
//        }
//
//        getChildFragmentManager().beginTransaction().replace(R.id.container,BlankFragment.newInstance(apiList.get(0))).commit();
//
//        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//               getChildFragmentManager().beginTransaction().replace(R.id.container,BlankFragment.newInstance(apiList.get(tab.getPosition()))).commit();
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alldingdan:
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                if(allDingFragment==null){
                    allDingFragment=new AllDingFragment();
                }
                addFragments(allDingFragment);
                break;
            case R.id.ding_zhifu:
                if(zhifuFragment==null){
                    zhifuFragment=new ZhifuFragment();
                }
                addFragments(zhifuFragment);
                v2.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                break;
            case R.id.ding_shuohuo:
                if(shouhuoFragment==null){
                    shouhuoFragment=new ShouhuoFragment();
                }
                addFragments(shouhuoFragment);
                v3.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v4.setVisibility(View.INVISIBLE);
                break;
            case R.id.ding_pinjia:
                if(pingjiaFragment==null){
                    pingjiaFragment=new PingjiaFragment();
                }
                addFragments(pingjiaFragment);
                v4.setVisibility(View.VISIBLE);
                v1.setVisibility(View.INVISIBLE);
                v2.setVisibility(View.INVISIBLE);
                v3.setVisibility(View.INVISIBLE);
                break;
        }
    }
    private void addFragments(Fragment f) {
        // 第一步：得到fragment管理类
        FragmentManager manager = getChildFragmentManager();
        // 第二步：开启一个事务
        FragmentTransaction transaction = manager.beginTransaction();

        if (currentf != null) {
            //每次把前一个fragment给隐藏了
            transaction.hide(currentf);
        }

        //isAdded:判断当前的fragment对象是否被加载过
        if (!f.isAdded()) {
            // 第三步：调用添加fragment的方法 第一个参数：容器的id 第二个参数：要放置的fragment的一个实例对象
            transaction.add(R.id.container, f);
        }
        //显示当前的fragment
        transaction.show(f);

        // 第四步：提交
        transaction.commit();

        currentf = f;
    }
}
