package com.example.administrator.lenglian.fragment.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.utils.MyContants;

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
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.activity_mineorder,null);
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager = (FrameLayout) view.findViewById(R.id.container);
        return view;
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
//        Indexbean index;
//        for (int i = 0; i < 5; i++) {
//            index=new Indexbean();
//            index.setCount("哈发发嘎啊发发发阿发啊啊"+i);
//            lists.add(index);
//        }
        apiList = new ArrayList<>();
        //放接口
        apiList.add(MyContants.BASEURL+"s=Order/listOrder");
        apiList.add(MyContants.BASEURL +"s=User/register");
        apiList.add(MyContants.BASEURL+"s=Order/listOrder");
        apiList.add(MyContants.BASEURL+"s=Order/listOrder");
        for (int i = 0; i < list.size(); i++) {
            tab.addTab(tab.newTab().setText(list.get(i)));
        }

        getFragmentManager().beginTransaction().replace(R.id.container,BlankFragment.newInstance(apiList.get(0))).commit();

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
               getFragmentManager().beginTransaction().replace(R.id.container,BlankFragment.newInstance(apiList.get(tab.getPosition()))).commit();
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
