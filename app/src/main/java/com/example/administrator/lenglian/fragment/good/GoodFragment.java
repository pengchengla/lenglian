package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.SearchActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class GoodFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycler_title;
    private RecyclerView recycler_content;
    private TextView tv_search;
    private List<TitleBean> titleList = new ArrayList<>();
    private List<String> contentList = new ArrayList<>();
    private TitleAdapter mTitleAdapter;
    private ContentAdapter mContentAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_good, null);
        recycler_title = (RecyclerView) view.findViewById(R.id.recycler_title);
        recycler_content = (RecyclerView) view.findViewById(R.id.recycler_content);
        tv_search = (TextView) view.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
        return view;
    }

    @Override
    protected void initData() {
        recycler_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        recycler_title.setLayoutManager(new LinearLayoutManager(mContext));
        titleList.add(new TitleBean("超市组合柜",true));
        titleList.add(new TitleBean("豪华展示柜"));
        titleList.add(new TitleBean("豪华岛柜"));
        titleList.add(new TitleBean("双温厨房柜"));
        titleList.add(new TitleBean("双温点菜柜"));
        titleList.add(new TitleBean("卧式冷柜"));
        titleList.add(new TitleBean("豪华雪糕柜"));
        titleList.add(new TitleBean("药店阴凉柜"));
        mTitleAdapter = new TitleAdapter(R.layout.good_title_item, titleList);
        recycler_title.setAdapter(mTitleAdapter);


        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        contentList.add("");
        mContentAdapter = new ContentAdapter(R.layout.good_content_item, contentList);
        recycler_content.setAdapter(mContentAdapter);
        mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,GoodDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    class TitleAdapter extends BaseQuickAdapter<TitleBean, BaseViewHolder> {

        public TitleAdapter(@LayoutRes int layoutResId, @Nullable List<TitleBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, TitleBean item) {
            helper.setText(R.id.rb_title, item.getContent());
            helper.setChecked(R.id.rb_title, item.isChecked());
            if (item.isChecked()){
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.blue);
            }else {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.white);
            }
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < titleList.size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            titleList.get(i).setChecked(true);
                            helper.getView(R.id.title_left_view).setBackgroundResource(R.color.blue);
                        } else {
                            titleList.get(i).setChecked(false);
                            helper.getView(R.id.title_left_view).setBackgroundResource(R.color.white);
                        }
                    }
                    mTitleAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    class ContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ContentAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
