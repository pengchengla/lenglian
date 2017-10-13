package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.LoginActivity;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.db.LitePalHelper;
import com.example.administrator.lenglian.db.ShopCarBean;
import com.example.administrator.lenglian.fragment.good.QueRenOrderActivity;
import com.example.administrator.lenglian.listener.SnappingStepperValueChangeListener;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.view.SnappingStepper;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import static com.example.administrator.lenglian.R.id.stepper;

public class MyShopCarActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back, tv_edit, tv_jiesuan, tv_total_count, tv_total_price, tv_quanxuan;
    private RecyclerView recycler_shopcar;
    private ShopCarAdapter mCarAdapter;
    private boolean isEditing;
    private LinearLayout ll_money;
    private List<ShopCarBean> mData;
    private String mGoodId;
    private String mImgUrl;
    private String mName;
    private String mPeisongfei;
    private String mYajin;
    private String mDuration;
    private String mPrice;
    private FrameLayout fl_havecar;
    private RelativeLayout rl_nocar;
    private Button btn_login, btn_go;

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
        ll_money = (LinearLayout) findViewById(R.id.ll_money);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        fl_havecar = (FrameLayout) findViewById(R.id.fl_havecar);
        rl_nocar = (RelativeLayout) findViewById(R.id.rl_nocar);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(this);
        initData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    private void initData() {
        mData = LitePalHelper.search();
        if (mData != null && mData.size() > 0) {
            fl_havecar.setVisibility(View.VISIBLE);
            rl_nocar.setVisibility(View.GONE);
            tv_edit.setVisibility(View.VISIBLE);
            mCarAdapter = new ShopCarAdapter(R.layout.item_mycar, mData);
            recycler_shopcar.setAdapter(mCarAdapter);
            mCarAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.tv_delete) {
                        if (noOneChecked()) {
                            Toast.makeText(MyShopCarActivity.this, "请先选中产品", Toast.LENGTH_SHORT).show();
                        } else {
                            LitePalHelper.deleteOne(mCarAdapter.getData().get(position).getGoodId());
                            //                        mCarAdapter.getData().remove(position);
                            //                        mCarAdapter.notifyItemRemoved(position);
                            initData();
                        }
                    }
                }
            });
        } else {
            fl_havecar.setVisibility(View.GONE);
            rl_nocar.setVisibility(View.VISIBLE);
            tv_edit.setVisibility(View.GONE);
            if (TextUtils.isEmpty(SpUtils.getString(this, "user_id", ""))) {
                btn_login.setVisibility(View.VISIBLE);
                btn_go.setVisibility(View.GONE);
            } else {
                btn_login.setVisibility(View.GONE);
                btn_go.setVisibility(View.VISIBLE);
            }
        }
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
                        //                        mData.clear();
                        //                        mCarAdapter.notifyDataSetChanged();
                        tv_quanxuan.setVisibility(View.GONE);
                        LitePalHelper.deleteAll();
                        initData();
                    } else {
                        Toast.makeText(this, "请先点击全选", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (noOneChecked()) {
                        Toast.makeText(this, "请先选中产品", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(this, QueRenOrderActivity.class);
                        intent.putExtra("id", mGoodId);
                        intent.putExtra("duration", mDuration);
                        intent.putExtra("imgUrl", mImgUrl);
                        intent.putExtra("name", mName);
                        intent.putExtra("price", mPrice);
                        intent.putExtra("yajin", mYajin);
                        intent.putExtra("peisongfei", mPeisongfei);
                        startActivity(intent);
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
                for (int i = 0; i < mData.size(); i++) {
                    mData.get(i).setChecked(true);
                }
                if (mCarAdapter != null) {
                    mCarAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.btn_go:
//                EventMessage eventMessage = new EventMessage("allgoods");
//                EventBus.getDefault().postSticky(eventMessage);
//                finish();
                Intent intent=new Intent(MyShopCarActivity.this,CuXiaoActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    class ShopCarAdapter extends BaseQuickAdapter<ShopCarBean, BaseViewHolder> {

        public ShopCarAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCarBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ShopCarBean item) {
            if (isEditing) {
                helper.setVisible(stepper, true)
                        .setVisible(R.id.tv_delete, true)
                        .setVisible(R.id.tv_title, false)
                        .setVisible(R.id.tv_price_pro, false)
                        .setVisible(R.id.tv_qian, false)
                        .setVisible(R.id.tv_size, false)
                        .setVisible(R.id.tv_xxxxxx, false);
            } else if (!isEditing) {
                helper.setVisible(stepper, false)
                        .setVisible(R.id.tv_delete, false)
                        .setVisible(R.id.tv_title, true)
                        .setVisible(R.id.tv_qian, true)
                        .setVisible(R.id.tv_price_pro, true)
                        .setVisible(R.id.tv_size, true)
                        .setVisible(R.id.tv_xxxxxx, true);
            }
            helper.setText(R.id.tv_title, item.getName())
                    .setChecked(R.id.cb_car, item.isChecked())
                    .addOnClickListener(R.id.tv_delete);
            final TextView tv_size = helper.getView(R.id.tv_size);
            final TextView tv_price = helper.getView(R.id.tv_price_pro);
            tv_size.setText(item.getDuration() + "");
            tv_price.setText(Float.parseFloat(item.getPrice()) * item.getDuration() + "");
            helper.getView(R.id.ll_root_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < mData.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            mData.get(i).setChecked(!mData.get(i).isChecked());
                            mDuration = mData.get(i).getDuration() + "";
                            tv_total_count.setText(mDuration);
                            mPrice = mData.get(i).getPrice();
                            tv_total_price.setText(tv_price.getText().toString());
                            mGoodId = mData.get(i).getGoodId();
                            mImgUrl = mData.get(i).getImgUrl();
                            mName = mData.get(i).getName();
                            mPeisongfei = mData.get(i).getPeisongfei();
                            mYajin = mData.get(i).getYajin();
                        } else {
                            mData.get(i).setChecked(false);
                        }
                    }
                    mCarAdapter.notifyDataSetChanged();
                }
            });
            final SnappingStepper stepper = helper.getView(R.id.stepper);
            stepper.setText(item.getDuration() + "");
            stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override
                public void onValueChange(View view, int value) {
                    if (!noOneChecked()) {
                        tv_size.setText(value + "");
                        tv_price.setText(Float.parseFloat(item.getPrice()) * value + "");
                        tv_total_count.setText(value + "");
                        tv_total_price.setText(Float.parseFloat(item.getPrice()) * value + "");
                        LitePalHelper.updateCount(item.getGoodId(), value);
                        mData = LitePalHelper.search();
                        notifyDataSetChanged();
                    } else {
                        stepper.setText(item.getDuration() + "");
                        Toast.makeText(MyShopCarActivity.this, "请先选中一款产品", Toast.LENGTH_SHORT).show();
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
        for (ShopCarBean shopCarBean : mData) {
            if (shopCarBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllChecked() {
        for (ShopCarBean shopCarBean : mData) {
            if (!shopCarBean.isChecked()) {
                return false;
            }
        }
        return true;
    }
}
