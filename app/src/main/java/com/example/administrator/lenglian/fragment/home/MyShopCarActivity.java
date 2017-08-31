package com.example.administrator.lenglian.fragment.home;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;

public class MyShopCarActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back, tv_edit, tv_jiesuan;
    private RecyclerView recycler_shopcar;
    private ShopCarAdapter mCarAdapter;
    private boolean isEditing;
    private List<ShopCarBean> mList;

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
        tv_jiesuan = (TextView) findViewById(R.id.tv_jiesuan);
        tv_jiesuan.setOnClickListener(this);
        mList = new ArrayList<>();
        mList.add(new ShopCarBean("111111111111111", false));
        mList.add(new ShopCarBean("222222222222222", false));
        mList.add(new ShopCarBean("333333333333333", false));
        mList.add(new ShopCarBean("444444444444444", false));
        mCarAdapter = new ShopCarAdapter(R.layout.item_mycar, mList);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_jiesuan:

                break;
            case R.id.tv_edit:
                isEditing = !isEditing;
                if (isEditing) {
                    tv_edit.setText("完成");
                    tv_jiesuan.setText("删除");
                } else {
                    tv_edit.setText("编辑全部");
                    tv_jiesuan.setText("去结算");
                }
                mCarAdapter.notifyDataSetChanged();
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
                        .setVisible(R.id.tv_size, false);
            } else if (!isEditing) {
                helper.setVisible(R.id.stepper, false)
                        .setVisible(R.id.tv_delete, false)
                        .setVisible(R.id.tv_title, true)
                        .setVisible(R.id.tv_price, true)
                        .setVisible(R.id.tv_size, true);
            }
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
                        } else {
                            mList.get(i).setChecked(false);
                        }
                    }
                    mCarAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
