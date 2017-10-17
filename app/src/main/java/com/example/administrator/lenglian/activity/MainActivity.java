package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.good.GoodFragment;
import com.example.administrator.lenglian.fragment.home.HomeFragment;
import com.example.administrator.lenglian.fragment.mine.MineFragment;
import com.example.administrator.lenglian.fragment.order.OrderFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioGroup rgp;
    private int position;
    private List<BaseFragment> mBaseFragmentList = new ArrayList<>(4);
    private BaseFragment preFragment;
    private long preTime;
    private BaseFragment[] mBaseFragments;
    private RadioButton rb_home, rb_good, rb_order, rb_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        rgp = (RadioGroup) findViewById(R.id.rgp);
        //        initData();
        //        initListener();
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        rb_good = (RadioButton) findViewById(R.id.rb_good);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_order = (RadioButton) findViewById(R.id.rb_order);
        initData1();
        initListener1();
    }

    private void initListener1() {
        rb_mine.setOnClickListener(this);
        rb_good.setOnClickListener(this);
        rb_home.setOnClickListener(this);
        rb_order.setOnClickListener(this);
        String pay = getIntent().getStringExtra("pay");
        if (!TextUtils.isEmpty(pay)&& pay.equals("success")){
            rgp.check(R.id.rb_order);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(mBaseFragments[2]);
            fragmentTransaction.hide(mBaseFragments[0]).hide(mBaseFragments[1]).hide(mBaseFragments[3]);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private void initData1() {
        mBaseFragments = new BaseFragment[4];
        mBaseFragments[0] = new HomeFragment();
        mBaseFragments[1] = new GoodFragment();
        mBaseFragments[2] = new OrderFragment();
        mBaseFragments[3] = new MineFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //注意add方法会走fragment的生命周期方法，相当于加载了一遍数据
        fragmentTransaction.add(R.id.fl_content, mBaseFragments[0]);
        fragmentTransaction.add(R.id.fl_content, mBaseFragments[1]);
        fragmentTransaction.add(R.id.fl_content, mBaseFragments[2]);
        fragmentTransaction.add(R.id.fl_content, mBaseFragments[3]);
        fragmentTransaction.show(mBaseFragments[0]);
        fragmentTransaction.hide(mBaseFragments[1]);
        fragmentTransaction.hide(mBaseFragments[2]);
        fragmentTransaction.hide(mBaseFragments[3]);
        rgp.check(R.id.rb_home);//刷新radiobutton的状态
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (eventMessage.getMsg().equals("allgoods")) {
            rgp.check(R.id.rb_good);
            fragmentTransaction.show(mBaseFragments[1]);
            fragmentTransaction.hide(mBaseFragments[0]).hide(mBaseFragments[2]).hide(mBaseFragments[3]);
            fragmentTransaction.commitAllowingStateLoss();
        } else if (eventMessage.getMsg().equals("order")) {
            rgp.check(R.id.rb_order);
            fragmentTransaction.show(mBaseFragments[2]);
            fragmentTransaction.hide(mBaseFragments[0]).hide(mBaseFragments[1]).hide(mBaseFragments[3]);
            fragmentTransaction.commitAllowingStateLoss();
        } else if (eventMessage.getMsg().equals("class_id")) {
            rgp.check(R.id.rb_good);
            fragmentTransaction.show(mBaseFragments[1]);
            fragmentTransaction.hide(mBaseFragments[0]).hide(mBaseFragments[2]).hide(mBaseFragments[3]);
            fragmentTransaction.commitAllowingStateLoss();
            EventMessage eventMessage2 = new EventMessage("good_title", eventMessage.getMsg2());
            EventBus.getDefault().postSticky(eventMessage2);
        }
    }

    private void initData() {
        if (mBaseFragmentList.size() <= 0) {
            mBaseFragmentList.add(new HomeFragment());
            mBaseFragmentList.add(new GoodFragment());
            mBaseFragmentList.add(new OrderFragment());
            mBaseFragmentList.add(new MineFragment());
        }
    }

    private void initListener() {
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_good:
                        position = 1;
                        break;
                    case R.id.rb_order:
                        position = 2;
                        break;
                    case R.id.rb_mine:
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //                Toast.makeText(MainActivity.this, " " + position, Toast.LENGTH_SHORT).show();
                switchFragment(preFragment, mBaseFragmentList.get(position));
            }
        });
        //        rgp.check(R.id.rb_good);//因为向商品fragment发eventbus的时候，商品fragment没创建出来，所以就加这么一个狠招
        rgp.check(R.id.rb_home);//默认选中首页
    }

    private void switchFragment(BaseFragment from, BaseFragment to) {
        if (from == to) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //判断有没有被添加
        if (!to.isAdded()) {
            //to没有被添加
            //from隐藏
            if (from != null) {
                ft.hide(from);
            }
            //添加to
            ft.add(R.id.fl_content, to).commit();//不要忘记commit
        } else {
            //to已经被添加
            // from隐藏
            if (from != null) {
                ft.hide(from);
            }
            //显示to
            if (to != null) {
                ft.show(to).commit();//不要忘记commit
            }
        }
        preFragment = to;//将要显示的fragment当然就成为了下一次切换的preFragment
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            realBack();//删除所有引用
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.rb_home:
                fragmentTransaction.show(mBaseFragments[0]);
                fragmentTransaction.hide(mBaseFragments[1]).hide(mBaseFragments[2]).hide(mBaseFragments[3]);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.rb_good:
                fragmentTransaction.show(mBaseFragments[1]);
                fragmentTransaction.hide(mBaseFragments[0]).hide(mBaseFragments[2]).hide(mBaseFragments[3]);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.rb_order:
                fragmentTransaction.show(mBaseFragments[2]);
                fragmentTransaction.hide(mBaseFragments[1]).hide(mBaseFragments[0]).hide(mBaseFragments[3]);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.rb_mine:
                fragmentTransaction.show(mBaseFragments[3]);
                fragmentTransaction.hide(mBaseFragments[1]).hide(mBaseFragments[2]).hide(mBaseFragments[0]);
                fragmentTransaction.commitAllowingStateLoss();
                break;
        }
    }
}
