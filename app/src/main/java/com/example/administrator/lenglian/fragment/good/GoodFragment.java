package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.SearchActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.GoodBean;
import com.example.administrator.lenglian.bean.GoodTypeBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class GoodFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycler_title;
    private RecyclerView recycler_content;
    private TextView tv_search;
    //    private List<TitleBean> titleList = new ArrayList<>();
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("商品总界面"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("商品总界面");
    }

    @Override
    protected void initData() {
        recycler_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        recycler_title.setLayoutManager(new LinearLayoutManager(mContext));

        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("", "");
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listClass", arrayMap, new BaseObserver1<GoodTypeBean>("") {
            @Override
            public void onSuccess(GoodTypeBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    if (mTitleAdapter == null) {
                        mTitleAdapter = new TitleAdapter(R.layout.good_title_item, result.getDatas());
                        recycler_title.setAdapter(mTitleAdapter);
                        mTitleAdapter.getData().get(0).setChecked(true);
                        mTitleAdapter.notifyDataSetChanged();//默认选中第一个
                        switchData(mTitleAdapter.getData().get(0).getClass_id());
                    }
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void switchData(String class_id) {
        //        Toast.makeText(mContext, " " + class_id, Toast.LENGTH_SHORT).show();
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("class_id", class_id);
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listProduct", arrayMap, new BaseObserver1<GoodBean>("") {
            @Override
            public void onSuccess(GoodBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    if (mContentAdapter == null) {
                        mContentAdapter = new ContentAdapter(R.layout.good_content_item, result.getDatas());
                        recycler_content.setAdapter(mContentAdapter);
                    } else {
                        mContentAdapter.setNewData(result.getDatas());
                    }
                    mContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
                            intent.putExtra("id", mContentAdapter.getData().get(position).getPro_id());
                            startActivity(intent);
                        }
                    });
                } else {
                    //没数据的时候返回的code是101
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class TitleAdapter extends BaseQuickAdapter<GoodTypeBean.DatasEntity, BaseViewHolder> {

        public TitleAdapter(@LayoutRes int layoutResId, @Nullable List<GoodTypeBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, GoodTypeBean.DatasEntity item) {
            helper.setText(R.id.rb_title, item.getClass_name());
            helper.setChecked(R.id.rb_title, item.isChecked());
            if (item.isChecked()) {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.blue);
            } else {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.white);
            }
            helper.getView(R.id.rb_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //直接在外层用adapter的点击事件就不管用，真是邪门
                    for (int i = 0; i < mTitleAdapter.getData().size(); i++) {
                        if (helper.getAdapterPosition() == i) {
                            mTitleAdapter.getData().get(i).setChecked(true);
                            helper.getView(R.id.title_left_view).setBackgroundResource(R.color.blue);
                            switchData(mTitleAdapter.getData().get(i).getClass_id());
                        } else {
                            mTitleAdapter.getData().get(i).setChecked(false);
                            helper.getView(R.id.title_left_view).setBackgroundResource(R.color.white);
                        }
                    }
                    mTitleAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    class ContentAdapter extends BaseQuickAdapter<GoodBean.DatasEntity, BaseViewHolder> {

        public ContentAdapter(@LayoutRes int layoutResId, @Nullable List<GoodBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodBean.DatasEntity item) {
            helper.setText(R.id.tv_title, item.getMain_title());
            Glide.with(mContext).load(item.getPro_pic().get(0).getUrl())
                    .into((ImageView) helper.getView(R.id.iv_tupian));
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
