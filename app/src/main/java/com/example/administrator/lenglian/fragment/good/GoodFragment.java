package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.SearchActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.bean.GoodBean;
import com.example.administrator.lenglian.bean.GoodTypeBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class GoodFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycler_title;
    private RecyclerView recycler_content;
    private TextView tv_search;
    private TitleAdapter mTitleAdapter;
    private ContentAdapter mContentAdapter;
    private List<GoodTypeBean.DatasEntity> mTitleDatas;
    private LinearLayoutManager mTitleLayoutManager;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_good, null);
        recycler_title = (RecyclerView) view.findViewById(R.id.recycler_title);
        recycler_content = (RecyclerView) view.findViewById(R.id.recycler_content);
        tv_search = (TextView) view.findViewById(R.id.tv_search);
        tv_search.setOnClickListener(this);
        EventBus.getDefault().register(this);  //注册
        recycler_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        mTitleLayoutManager = new LinearLayoutManager(mContext);
        recycler_title.setLayoutManager(mTitleLayoutManager);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("商品总界面"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("商品总界面");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myEvent(EventMessage eventMessage) {
        if (eventMessage.getMsg().equals("good_title")) {
            String msg = eventMessage.getMsg2();
            //            Toast.makeText(mContext, " " + msg, Toast.LENGTH_SHORT).show();
            switchTitleOne(msg);
        }
    }

    @Override
    protected void initData() {
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("", "");
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listClass", arrayMap, new BaseObserver1<GoodTypeBean>("") {
            @Override
            public void onSuccess(GoodTypeBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mTitleDatas = result.getDatas();
                    if (mTitleAdapter == null) {
                        mTitleDatas.get(0).setChecked(true);//默认选中第一个
                        mTitleAdapter = new TitleAdapter(R.layout.good_title_item, mTitleDatas);
                        recycler_title.setAdapter(mTitleAdapter);
                        switchData(mTitleDatas.get(0).getClass_id());
                    }
                    mTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            mTitleAdapter.getData().get(scrollPosition).setChecked(false);
                            scrollPosition = position;
                            mTitleAdapter.getData().get(scrollPosition).setChecked(true);
                            mTitleAdapter.notifyDataSetChanged();
                            switchData(mTitleAdapter.getData().get(scrollPosition).getClass_id());
                        }
                    });
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void switchTitleOne(String msg) {
        for (int i = 0; i < mTitleAdapter.getData().size(); i++) {
            if (mTitleAdapter.getData().get(i).getClass_id().equals(msg)) {
                mTitleAdapter.getData().get(i).setChecked(true);
                switchData(mTitleAdapter.getData().get(i).getClass_id());
                scrollPosition = i;
            } else {
                mTitleAdapter.getData().get(i).setChecked(false);
            }
        }
        mTitleAdapter.notifyDataSetChanged();
        //        recycler_title.smoothScrollToPosition(scrollPosition);/////////////
        smoothMoveToPosition(scrollPosition);
        moveToCenter(scrollPosition);
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
                    mContentAdapter.getData().clear();
                    mContentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(int code) {
//                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int scrollPosition;

    class TitleAdapter extends BaseQuickAdapter<GoodTypeBean.DatasEntity, BaseViewHolder> {

        public TitleAdapter(@LayoutRes int layoutResId, @Nullable List<GoodTypeBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final GoodTypeBean.DatasEntity item) {
            helper.setText(R.id.tv_title, item.getClass_name());
            TextView tv = helper.getView(R.id.tv_title);
            if (item.isChecked()) {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.blue);
                tv.setBackgroundColor(getResources().getColor(R.color.huise));
                tv.setTextColor(getResources().getColor(R.color.blue));
            } else {
                helper.getView(R.id.title_left_view).setBackgroundResource(R.color.white);
                tv.setBackgroundColor(getResources().getColor(R.color.white));
                tv.setTextColor(getResources().getColor(R.color.textblack));
            }
        }
    }

    class ContentAdapter extends BaseQuickAdapter<GoodBean.DatasEntity, BaseViewHolder> {

        public ContentAdapter(@LayoutRes int layoutResId, @Nullable List<GoodBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodBean.DatasEntity item) {
            helper.setText(R.id.tv_title, item.getMain_title());
            RequestOptions options = new RequestOptions()
//                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            if (item.getPro_pic() != null && item.getPro_pic().size() > 0) {
                Glide.with(mContext).load(item.getSingle_pic())
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.iv_tupian));
            }
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

    private void smoothMoveToPosition(int n) {
        int firstItem = mTitleLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mTitleLayoutManager.findLastVisibleItemPosition();
        Log.e("first--->", String.valueOf(firstItem));
        Log.e("last--->", String.valueOf(lastItem));
        if (n <= firstItem) {
            recycler_title.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            Log.e("pos---->", String.valueOf(n) + " VS " + firstItem);
            int top = recycler_title.getChildAt(n - firstItem).getTop();
            Log.e("top---->", String.valueOf(top));
            recycler_title.scrollBy(0, top);
        } else {
            recycler_title.smoothScrollToPosition(n);
        }
    }

    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = recycler_title.getChildAt(position - mTitleLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - recycler_title.getHeight() / 2);
            recycler_title.smoothScrollBy(0, y);
        }
    }

}
