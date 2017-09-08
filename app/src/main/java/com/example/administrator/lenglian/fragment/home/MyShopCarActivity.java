package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.ShopCarBean;
import com.example.administrator.lenglian.fragment.good.QueRenOrderActivity;
import com.example.administrator.lenglian.listener.SnappingStepperValueChangeListener;
import com.example.administrator.lenglian.view.SnappingStepper;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MyShopCarActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back, tv_edit, tv_jiesuan, tv_total_count, tv_total_price, tv_quanxuan;
    private RecyclerView recycler_shopcar;
    private ShopCarAdapter mCarAdapter;
    private boolean isEditing;
    private List<ShopCarBean> mList;
    private LinearLayout ll_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop_car);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        recycler_shopcar = (RecyclerView) findViewById(R.id.recycler_shopcar);
        recycler_shopcar.setLayoutManager(new LinearLayoutManager(this));
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(this);
        tv_quanxuan = (TextView) findViewById(R.id.tv_quanxuan);
        tv_quanxuan.setOnClickListener(this);
        tv_jiesuan = (TextView) findViewById(R.id.tv_jiesuan);
        tv_jiesuan.setOnClickListener(this);
        tv_total_count = (TextView) findViewById(R.id.tv_count);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        mList = new ArrayList<>();
        mList.add(new ShopCarBean("111111111111111", false));
        mList.add(new ShopCarBean("222222222222222", false));
        mList.add(new ShopCarBean("333333333333333", false));
        mList.add(new ShopCarBean("444444444444444", false));
        mCarAdapter = new ShopCarAdapter(R.layout.item_mycar, mList);
        ll_money = (LinearLayout) findViewById(R.id.ll_money);
        recycler_shopcar.setAdapter(mCarAdapter);
        mCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_delete) {
                    mCarAdapter.getData().remove(position);
                    mCarAdapter.notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("我的租赁车界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("我的租赁车界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_jiesuan:
                if (tv_jiesuan.getText().toString().equals("全部删除")) {
                    if (isAllChecked()) {
                        mList.clear();
                        mCarAdapter.notifyDataSetChanged();
                        tv_quanxuan.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(this, "请先点击全选", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (noOneChecked()) {
                        Toast.makeText(this, "请先选中产品", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(this, QueRenOrderActivity.class));
                    }
                }
                break;
            case R.id.tv_edit:
                isEditing = !isEditing;
                if (isEditing) {
                    tv_edit.setText("完成");
                    tv_jiesuan.setText("全部删除");
                    ll_money.setVisibility(View.GONE);
                    tv_quanxuan.setVisibility(View.VISIBLE);
                } else {
                    tv_edit.setText("编辑全部");
                    tv_jiesuan.setText("去结算");
                    ll_money.setVisibility(View.VISIBLE);
                    tv_quanxuan.setVisibility(View.GONE);
                }
                mCarAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_quanxuan:
                for (int i = 0; i < mList.size(); i++) {
                    mList.get(i).setChecked(true);
                }
                if (mCarAdapter != null) {
                    mCarAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    class ShopCarAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {

        public ShopCarAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCarBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, ShopCarBean item) {
            if (isEditing) {
                helper.setVisible(R.id.stepper, true)
                        .setVisible(R.id.tv_delete, true)
                        .setVisible(R.id.tv_title, false)
                        .setVisible(R.id.tv_price, false)
                        .setVisible(R.id.tv_qian, false)
                        .setVisible(R.id.tv_size, false)
                        .setVisible(R.id.tv_xxxxxx, false);
            } else if (!isEditing) {
                helper.setVisible(R.id.stepper, false)
                        .setVisible(R.id.tv_delete, false)
                        .setVisible(R.id.tv_title, true)
                        .setVisible(R.id.tv_qian, true)
                        .setVisible(R.id.tv_price, true)
                        .setVisible(R.id.tv_size, true)
                        .setVisible(R.id.tv_xxxxxx, true);
            }
            final TextView tv_size = helper.getView(R.id.tv_size);
            final TextView tv_price = helper.getView(R.id.tv_price);
            helper.setText(R.id.tv_title, item.getTitle())
                    .setChecked(R.id.cb_car, item.isChecked())
                    .addOnClickListener(R.id.tv_delete);
            helper.getView(R.id.ll_root_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < mList.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            mList.get(i).setChecked(!mCarAdapter.getData().get(i).isChecked());
                            tv_total_count.setText(tv_size.getText().toString());
                            tv_total_price.setText(tv_price.getText().toString() + "");
                        } else {
                            mList.get(i).setChecked(false);
                        }
                    }
                    mCarAdapter.notifyDataSetChanged();
                }
            });
            SnappingStepper stepper = helper.getView(R.id.stepper);
            stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override
                public void onValueChange(View view, int value) {
                    helper.setText(R.id.tv_size, value + "");
                    helper.setText(R.id.tv_price, 1777 * value + "");
                    if (!noOneChecked()) {
                        tv_total_count.setText(tv_size.getText().toString());
                        tv_total_price.setText(tv_price.getText().toString() + "");
                    }
                }
            });
            if (noOneChecked()) {
                tv_total_count.setText("0");
                tv_total_price.setText("0.00");
            }
        }
    }

    private boolean noOneChecked() {
        for (ShopCarBean shopCarBean : mList) {
            if (shopCarBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllChecked() {
        for (ShopCarBean shopCarBean : mList) {
            if (!shopCarBean.isChecked()) {
                return false;
            }
        }
        return true;
    }
}
