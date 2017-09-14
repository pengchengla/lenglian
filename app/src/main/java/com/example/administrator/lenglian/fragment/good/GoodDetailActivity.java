package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.fragment.home.MyShopCarActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class GoodDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_back, iv_car;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<BaseFragment> mBaseFragmentList = new ArrayList<>();
    private GoodDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        iv_car = (ImageView) findViewById(R.id.iv_car);
        iv_car.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("商品详情界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("商品详情界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    private void initData() {
        if (mBaseFragmentList.size() == 0) {
            mBaseFragmentList.add(new ShangPinFragment());
            mBaseFragmentList.add(new XiangQingFragment());
            mBaseFragmentList.add(new PingJiaFragment());
        }
        mAdapter = new GoodDetailAdapter(getSupportFragmentManager(), mBaseFragmentList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class GoodDetailAdapter extends FragmentStatePagerAdapter {
        private final List<BaseFragment> mFragmentList;
        private String[] title = {"商品", "详情", "评价"};

        public GoodDetailAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
            super(fm);
            this.mFragmentList = fragmentList;
        }

        //别忘了这个方法一定要有
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override//返回position位置的Fragment对象
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return title.length;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_car:
                startActivity(new Intent(this, MyShopCarActivity.class));
                break;
        }
    }
}
