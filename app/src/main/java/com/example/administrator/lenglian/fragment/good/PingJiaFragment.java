package com.example.administrator.lenglian.fragment.good;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.view.MyRatingBar;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PingJiaFragment extends BaseFragment {
    private RecyclerView recycler_pingjia;
    private PingjiaAdapter mPingjiaAdapter;
    private List<String> mList;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_pingjia, null);
        recycler_pingjia = (RecyclerView) view.findViewById(R.id.recycler_pingjia);
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("商品详情评价页"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("商品详情评价页");
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        recycler_pingjia.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_pingjia.setNestedScrollingEnabled(false);
        mPingjiaAdapter = new PingjiaAdapter(R.layout.item_pingjia, mList);
        recycler_pingjia.setAdapter(mPingjiaAdapter);
    }

    class PingjiaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PingjiaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RecyclerView recycler_photo = helper.getView(R.id.recycler_ping_photo);
            recycler_photo.setLayoutManager(new GridLayoutManager(mContext, 3));
            PingPhotoAdapter pingPhotoAdapter = new PingPhotoAdapter(R.layout.item_photo, mList);
            recycler_photo.setAdapter(pingPhotoAdapter);
            MyRatingBar ratingBar = helper.getView(R.id.ratingbar);
            ratingBar.setClickable(false);//设置可否点击
            ratingBar.setStar(3.0f);//设置显示的星星个数
            ratingBar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星

        }
    }

    class PingPhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PingPhotoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }
}
