package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.MessageActivity;
import com.example.administrator.lenglian.activity.SearchActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.good.GoodDetailActivity;
import com.example.administrator.lenglian.fragment.mine.AlterationActivity;
import com.example.administrator.lenglian.utils.BannerUtils;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_search, tv_msg_number,
            tv_allgoods, tv_myorder, tv_mytui, tv_mycar;
    private LinearLayout ll_msg, ll_supermarket, ll_chufang, ll_haixian, ll_xuegao;
    private Banner banner;
    private List<String> picList = new ArrayList<>();
    private RecyclerView recycler_cuxiao, recycler_changxiao, recycler_comment;
    private RelativeLayout rl_cuxiao, rl_changxiao, rl_comment, rl_top;
    private CuxiaoAdapter mCuxiaoAdapter;
    private ChangxiaoAdapter mChangxiaoAdapter;
    private CommentAdapter mCommentAdapter;
    private NestedScrollView nestView;
    private int mDistanceY;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        tv_search = (TextView) view.findViewById(R.id.tv_search);
        tv_msg_number = (TextView) view.findViewById(R.id.tv_msg_number);
        tv_search.setOnClickListener(this);
        ll_msg = (LinearLayout) view.findViewById(R.id.ll_msg);
        ll_msg.setOnClickListener(this);
        banner = (Banner) view.findViewById(R.id.banner);
        tv_allgoods = (TextView) view.findViewById(R.id.tv_allgoods);
        tv_allgoods.setOnClickListener(this);
        tv_myorder = (TextView) view.findViewById(R.id.tv_myorder);
        tv_myorder.setOnClickListener(this);
        tv_mytui = (TextView) view.findViewById(R.id.tv_mytui);
        tv_mytui.setOnClickListener(this);
        tv_mycar = (TextView) view.findViewById(R.id.tv_mycar);
        tv_mycar.setOnClickListener(this);
        ll_supermarket = (LinearLayout) view.findViewById(R.id.ll_supermarket);
        ll_supermarket.setOnClickListener(this);
        ll_chufang = (LinearLayout) view.findViewById(R.id.ll_chufang);
        ll_chufang.setOnClickListener(this);
        ll_haixian = (LinearLayout) view.findViewById(R.id.ll_haixian);
        ll_haixian.setOnClickListener(this);
        ll_xuegao = (LinearLayout) view.findViewById(R.id.ll_xuegao);
        ll_xuegao.setOnClickListener(this);
        recycler_cuxiao = (RecyclerView) view.findViewById(R.id.recycler_cuxiao);
        recycler_changxiao = (RecyclerView) view.findViewById(R.id.recycler_changxiao);
        recycler_comment = (RecyclerView) view.findViewById(R.id.recycler_comment);
        rl_cuxiao = (RelativeLayout) view.findViewById(R.id.rl_cuxiao);
        rl_cuxiao.setOnClickListener(this);
        rl_changxiao = (RelativeLayout) view.findViewById(R.id.rl_changxiao);
        rl_changxiao.setOnClickListener(this);
        rl_comment = (RelativeLayout) view.findViewById(R.id.rl_comment);
        rl_comment.setOnClickListener(this);
        rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
        nestView = (NestedScrollView) view.findViewById(R.id.nestView);
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("APP主页"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("APP主页");
    }

    @Override
    protected void initData() {
        nestView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //滑动的距离
                mDistanceY += scrollY - oldScrollY;
                //toolbar的高度
                int toolbarHeight = 300;//我写死的高度

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    float scale = (float) mDistanceY / toolbarHeight;
                    float alpha = scale * 255;
                    rl_top.setBackgroundColor(Color.argb((int) alpha, 103, 152, 231));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    rl_top.setBackgroundResource(R.color.colorPrimary);
                }
            }
        });
        tv_msg_number.setText("11");
        picList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
        picList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
        picList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
        BannerUtils.startBanner(banner, picList);
        if (mCuxiaoAdapter == null) {
            mCuxiaoAdapter = new CuxiaoAdapter(R.layout.home_cuxiao_item, picList);
        }
        recycler_cuxiao.setLayoutManager(new GridLayoutManager(mContext, 3));
        recycler_cuxiao.setNestedScrollingEnabled(false);
        recycler_cuxiao.setAdapter(mCuxiaoAdapter);
        mCuxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });

        if (mChangxiaoAdapter == null) {
            mChangxiaoAdapter = new ChangxiaoAdapter(R.layout.home_changxiao_item, picList);
        }
        recycler_changxiao.setLayoutManager(new GridLayoutManager(mContext, 3));
        recycler_changxiao.setNestedScrollingEnabled(false);
        recycler_changxiao.setAdapter(mChangxiaoAdapter);
        mChangxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });


        if (mCommentAdapter == null) {
            mCommentAdapter = new CommentAdapter(R.layout.home_comment_item, picList);
        }
        recycler_comment.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_comment.setNestedScrollingEnabled(false);
        recycler_comment.setAdapter(mCommentAdapter);
        mCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext, GoodDetailActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_msg:
                Intent intent2 = new Intent(mContext, MessageActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_allgoods:
                EventMessage eventMessage = new EventMessage();
                eventMessage.setMsg("allgoods");
                EventBus.getDefault().postSticky(eventMessage);
                break;
            case R.id.tv_myorder:
                EventMessage eventMessage2 = new EventMessage();
                eventMessage2.setMsg("order");
                EventBus.getDefault().postSticky(eventMessage2);
                break;
            case R.id.tv_mytui:
                startActivity(new Intent(mContext, AlterationActivity.class));
                break;
            case R.id.tv_mycar:
                Intent intent3 = new Intent(mContext, MyShopCarActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_supermarket:
                EventMessage eventMessage3 = new EventMessage();
                eventMessage3.setMsg("allgoods");
                EventBus.getDefault().postSticky(eventMessage3);
                break;
            case R.id.ll_chufang:
                EventMessage eventMessage4 = new EventMessage();
                eventMessage4.setMsg("allgoods");
                EventBus.getDefault().postSticky(eventMessage4);
                break;
            case R.id.ll_haixian:
                EventMessage eventMessage5 = new EventMessage();
                eventMessage5.setMsg("allgoods");
                EventBus.getDefault().postSticky(eventMessage5);
                break;
            case R.id.ll_xuegao:
                EventMessage eventMessage6 = new EventMessage();
                eventMessage6.setMsg("allgoods");
                EventBus.getDefault().postSticky(eventMessage6);
                break;
            case R.id.rl_cuxiao:
                Intent intent4 = new Intent(mContext, CuXiaoActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_changxiao:
                Intent intent5 = new Intent(mContext, ChangXiaoActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_comment:
                break;
        }
    }

    class CuxiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CuxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }

    class ChangxiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ChangxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    class CommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CommentAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
