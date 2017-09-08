package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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

public class MainActivity extends BaseActivity {
    private RadioGroup rgp;
    private int position;
    private List<BaseFragment> mBaseFragmentList = new ArrayList<>();
    private BaseFragment preFragment;
    private long preTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        rgp = (RadioGroup) findViewById(R.id.rgp);
        initData();
        initListener();
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
        if (eventMessage.getMsg().equals("allgoods")){
            rgp.check(R.id.rb_good);
        }else if (eventMessage.getMsg().equals("order")){
            rgp.check(R.id.rb_order);
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
                switchFragment(preFragment, mBaseFragmentList.get(position));
            }
        });
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
            if (to != null) {
                ft.add(R.id.fl_content, to).commit();//不要忘记commit
            }
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
}
