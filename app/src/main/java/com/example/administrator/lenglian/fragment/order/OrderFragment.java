package com.example.administrator.lenglian.fragment.order;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;

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
    private ViewPager pager;
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.activity_mineorder,null);
        tab = (TabLayout) view.findViewById(R.id.tab);
        pager = (ViewPager) view.findViewById(R.id.container);
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

        apiList = new ArrayList<>();
        //放接口
        apiList.add("");
        apiList.add("");
        apiList.add("");
        apiList.add("");
        for (int i = 0; i < list.size(); i++) {
            tab.addTab(tab.newTab().setText(list.get(i)));
        }

        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getChildFragmentManager().beginTransaction().add(R.id.container,BlankFragment.newInstance(apiList.get(tab.getPosition()))).show(BlankFragment.newInstance(apiList.get(tab.getPosition()))).commit();
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
