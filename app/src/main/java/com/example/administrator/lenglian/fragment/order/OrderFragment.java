package com.example.administrator.lenglian.fragment.order;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

public class OrderFragment extends BaseFragment {
    private TabLayout tab;
    private ArrayList<String> apiList;
    private List<String> list;
    private FrameLayout pager;
    private List<Indexbean> lists=new ArrayList<>();
    private LinearLayout liner_tab;
    private Button view_btn;
    private RelativeLayout real_login;

    @Override
    protected View initView() {
        EventBus.getDefault().register(this);
        View view=View.inflate(mContext, R.layout.activity_mineorder,null);
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager = (FrameLayout) view.findViewById(R.id.container);
        liner_tab = (LinearLayout) view.findViewById(R.id.liner_tab);
        view_btn = (Button) view.findViewById(R.id.view_btn);
        real_login = (RelativeLayout)view.findViewById(R.id.real_login);

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
        LinearLayout linearLayout = (LinearLayout) tab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.drawable.layout_divider));
        list = new ArrayList<>();
        list.add(String.format(Locale.CHINA, "全部订单"));
        list.add(String.format(Locale.CHINA, "待支付"));
        list.add(String.format(Locale.CHINA, "待收货"));
        list.add(String.format(Locale.CHINA, "待评价"));
        apiList = new ArrayList<>();
        //放接口
        apiList.add(MyContants.BASEURL+"s=Order/listOrder");
        //支付
        apiList.add(MyContants.BASEURL+"s=Order/listOrder/order_status=1");
        //收货
        apiList.add(MyContants.BASEURL+"s=Order/listOrder/order_status=2,3");
        //评价
        apiList.add(MyContants.BASEURL+"s=Order/listOrder/is_comment=0");
        for (int i = 0; i < list.size(); i++) {
            tab.addTab(tab.newTab().setText(list.get(i)));
        }

        getChildFragmentManager().beginTransaction().replace(R.id.container,BlankFragment.newInstance(apiList.get(0))).commit();

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//
//                for (int i = 0; i < list.size(); i++) {
//
//                    if (!BlankFragment.newInstance(apiList.get(i)).isAdded()){
//                        fragmentTransaction.add(R.id.container, BlankFragment.newInstance(apiList.get(i)));
//                    }
//
//                    if (i == tab.getPosition()) {
//                        fragmentTransaction.show(BlankFragment.newInstance(apiList.get(tab.getPosition())));
//                    } else {
//                        fragmentTransaction.hide(BlankFragment.newInstance(apiList.get(i)));
//                       // transaction.hide(fragments.get(i));
//                    }
//                }
//                fragmentTransaction.commit();
               getChildFragmentManager().beginTransaction().replace(R.id.container,BlankFragment.newInstance(apiList.get(tab.getPosition()))).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }


}
