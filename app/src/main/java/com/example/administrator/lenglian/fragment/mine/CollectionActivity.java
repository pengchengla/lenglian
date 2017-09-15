package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Collectbean;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class CollectionActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView collect_bianji;
    private TextView collect_ok;
    private RelativeLayout collect_title;
    private RecyclerView list_collect;
    private RelativeLayout select_delte;
    private CheckBox collect_check;
    private Button collect_btn;
    private List<Collectbean> mList;
    private boolean isEditing;
    private Collectionadapter collectionadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_collect);
        initView();
        inindata();
    }

    private void inindata() {
        mList = new ArrayList<>();
        mList.add(new Collectbean("豪华冰柜你值得拥有，没说错散打", false));
        mList.add(new Collectbean("豪华冰柜你值得拥有，没说错散打", false));
        mList.add(new Collectbean("奢侈冰柜你值得拥有，没说错散打", false));
        mList.add(new Collectbean("豪华冰柜你值得拥有哈哈哈，没说错散打", false));
        collectionadapter = new Collectionadapter(R.layout.collect_item, mList);
        list_collect.setAdapter(collectionadapter);
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        collect_bianji = (TextView) findViewById(R.id.collect_bianji);
        collect_ok = (TextView) findViewById(R.id.collect_ok);
        collect_title = (RelativeLayout) findViewById(R.id.collect_title);
        list_collect = (RecyclerView) findViewById(R.id.list_collect);
        list_collect.setLayoutManager(new LinearLayoutManager(this));
        select_delte = (RelativeLayout) findViewById(R.id.select_delte);
        collect_check = (CheckBox) findViewById(R.id.collect_check);
        collect_btn = (Button) findViewById(R.id.collect_btn);
        tv_back.setOnClickListener(this);
        collect_bianji.setOnClickListener(this);
        collect_ok.setOnClickListener(this);
        collect_btn.setOnClickListener(this);
        collect_check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.collect_bianji:
                isEditing = !isEditing;
                if (isEditing) {
                    collect_bianji.setText("完成");
                    select_delte.setVisibility(View.VISIBLE);
                } else {
                    collect_bianji.setText("编辑");
                    select_delte.setVisibility(View.GONE);
                }
                collectionadapter.notifyDataSetChanged();

                break;
            case R.id.collect_btn:
                break;
            case R.id.collect_check:
                break;
        }
    }

    class Collectionadapter extends BaseQuickAdapter<Collectbean, BaseViewHolder> {
        public Collectionadapter(@LayoutRes int layoutResId, @Nullable List<Collectbean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, Collectbean item) {
            if (isEditing) {
                helper.setVisible(R.id.collect_check, true);
            } else if (!isEditing) {
                helper.setVisible(R.id.collect_check, false);
            }
            helper.setText(R.id.collect_count, item.getTitle())
                    .setChecked(R.id.collect_check, item.isChecked());
            helper.getView(R.id.ll_root_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < mList.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            mList.get(i).setChecked(!collectionadapter.getData().get(i).isChecked());
                        } else {
                            mList.get(i).setChecked(false);
                        }
                    }
                    collectionadapter.notifyDataSetChanged();
                }
            });
        }
    }
}
