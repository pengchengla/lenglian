package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.LoginActivity;
import com.example.administrator.lenglian.activity.MessageActivity;
import com.example.administrator.lenglian.activity.SearchActivity;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.bean.HomeBean;
import com.example.administrator.lenglian.fragment.good.GoodDetailActivity;
import com.example.administrator.lenglian.fragment.mine.AlterationActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BannerUtils;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_search, tv_msg_number,
            tv_allgoods, tv_myorder, tv_mytui, tv_mycar, tv_text_msg;
    private LinearLayout ll_msg;
    private Banner banner;
    private List<String> picList = new ArrayList<>();
    private RecyclerView recycler_cuxiao, recycler_changxiao, recycler_comment, recycler_class;
    private RelativeLayout rl_cuxiao, rl_changxiao, rl_comment, rl_top;
    private CuxiaoAdapter mCuxiaoAdapter;
    private ChangxiaoAdapter mChangxiaoAdapter;
    private CommentAdapter mCommentAdapter;
    private NestedScrollView nestView;
    private int mDistanceY;
    private ImageView iv_msg, iv_changxiao_big;
    private MiddleAdapter mMiddleAdapter;

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
        tv_text_msg = (TextView) view.findViewById(R.id.tv_text_msg);
        tv_allgoods.setOnClickListener(this);
        tv_myorder = (TextView) view.findViewById(R.id.tv_myorder);
        tv_myorder.setOnClickListener(this);
        tv_mytui = (TextView) view.findViewById(R.id.tv_mytui);
        tv_mytui.setOnClickListener(this);
        tv_mycar = (TextView) view.findViewById(R.id.tv_mycar);
        tv_mycar.setOnClickListener(this);
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
        iv_msg = (ImageView) view.findViewById(R.id.iv_msg);
        iv_changxiao_big = (ImageView) view.findViewById(R.id.iv_changxiao_big);
        recycler_class = (RecyclerView) view.findViewById(R.id.recycler_class);
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
        Toast.makeText(mContext, " "+ SpUtils.getString(mContext,"user_id",""), Toast.LENGTH_SHORT).show();
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
                    tv_text_msg.setTextColor(Color.parseColor("#666666"));
                    iv_msg.setImageResource(R.drawable.icon_msg_black);
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    rl_top.setBackgroundResource(R.color.colorPrimary);
                    tv_text_msg.setTextColor(getResources().getColor(R.color.white));
                    iv_msg.setImageResource(R.drawable.icon_msg_white);
                }
            }
        });
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("", "");
        RetrofitManager.get(MyContants.BASEURL + "s=Home/home", arrayMap, new BaseObserver1<HomeBean>("") {
            @Override
            public void onSuccess(final HomeBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    if (picList.size() == 0) {
                        for (int i = 0; i < result.getDatas().getBanner().size(); i++) {
                            picList.add(result.getDatas().getBanner().get(i).getUrl());
                        }
                    }
                    BannerUtils.startBanner(banner, picList);
                    banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Intent intent = new Intent(mContext, WebActivity.class);
                            intent.putExtra("url", result.getDatas().getBanner().get(position).getBanner_address());
                            startActivity(intent);
                        }
                    });
                    if (mChangxiaoAdapter == null) {
                        List<HomeBean.DatasEntity.BestEntity> bestEntityList = new ArrayList<HomeBean.DatasEntity.BestEntity>();
                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .error(R.drawable.default_banner)
                                .priority(Priority.NORMAL)
                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                        Glide.with(mContext).load(result.getDatas().getBest().get(0).getPro_pic().get(0).getUrl())
                                .apply(options)
                                .into(iv_changxiao_big);
                        iv_changxiao_big.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, GoodDetailActivity.class);
                                intent.putExtra("id", result.getDatas().getBest().get(0).getPro_id());
                                startActivity(intent);
                            }
                        });
                        for (int i = 1; i < result.getDatas().getBest().size(); i++) {
                            bestEntityList.add(result.getDatas().getBest().get(i));
                        }
                        mChangxiaoAdapter = new ChangxiaoAdapter(R.layout.home_changxiao_item, bestEntityList);
                    }
                    recycler_changxiao.setLayoutManager(new GridLayoutManager(mContext, 3));
                    recycler_changxiao.setNestedScrollingEnabled(false);
                    recycler_changxiao.setAdapter(mChangxiaoAdapter);
                    mChangxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
                            intent.putExtra("id", mChangxiaoAdapter.getData().get(position).getPro_id());
                            startActivity(intent);
                        }
                    });

                    if (mCuxiaoAdapter == null) {
                        mCuxiaoAdapter = new CuxiaoAdapter(R.layout.home_cuxiao_item, result.getDatas().getSale());
                    }
                    recycler_cuxiao.setLayoutManager(new GridLayoutManager(mContext, 3));
                    recycler_cuxiao.setNestedScrollingEnabled(false);
                    recycler_cuxiao.setAdapter(mCuxiaoAdapter);
                    mCuxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
                            intent.putExtra("id", mCuxiaoAdapter.getData().get(position).getPro_id());
                            startActivity(intent);
                        }
                    });

                    if (mCommentAdapter == null) {
                        mCommentAdapter = new CommentAdapter(R.layout.home_comment_item, result.getDatas().getRecommend());
                    }
                    recycler_comment.setLayoutManager(new LinearLayoutManager(mContext));
                    recycler_comment.setNestedScrollingEnabled(false);
                    recycler_comment.setAdapter(mCommentAdapter);
                    mCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(mContext, GoodDetailActivity.class);
                            intent.putExtra("id", mCommentAdapter.getData().get(position).getPro_id());
                            startActivity(intent);
                        }
                    });
                    recycler_class.setLayoutManager(new GridLayoutManager(mContext, 2));
                    recycler_class.setNestedScrollingEnabled(false);
                    mMiddleAdapter = new MiddleAdapter(R.layout.home_class_item, result.getDatas().getPro_class());
                    recycler_class.setAdapter(mMiddleAdapter);
                    mMiddleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            EventMessage eventMessage2 = new EventMessage("class_id",
                                    mMiddleAdapter.getData().get(position).getClass_id());
                            EventBus.getDefault().postSticky(eventMessage2);
                        }
                    });
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
        tv_msg_number.setText("11");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_msg:
                if (!MyUtils.islogin(mContext)) {
                    Intent intent2 = new Intent(mContext, LoginActivity.class);
                    intent2.putExtra("gologin", "gologin");
                    startActivity(intent2);
                    return;
                }
                Intent intent2 = new Intent(mContext, MessageActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_allgoods:
                EventMessage eventMessage = new EventMessage("allgoods");
                EventBus.getDefault().postSticky(eventMessage);
                break;
            case R.id.tv_myorder:
                EventMessage eventMessage2 = new EventMessage("order");
                EventBus.getDefault().postSticky(eventMessage2);
                break;
            case R.id.tv_mytui:
                startActivity(new Intent(mContext, AlterationActivity.class));
                break;
            case R.id.tv_mycar:
                Intent intent3 = new Intent(mContext, MyShopCarActivity.class);
                startActivity(intent3);
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

    class CuxiaoAdapter extends BaseQuickAdapter<HomeBean.DatasEntity.SaleEntity, BaseViewHolder> {

        public CuxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DatasEntity.SaleEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DatasEntity.SaleEntity item) {
            helper.setText(R.id.tv_main_title, item.getMain_title())
                    .setText(R.id.tv_sub_title, item.getSub_title());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getPro_pic().get(0).getUrl())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    class ChangxiaoAdapter extends BaseQuickAdapter<HomeBean.DatasEntity.BestEntity, BaseViewHolder> {

        public ChangxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DatasEntity.BestEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DatasEntity.BestEntity item) {
            helper.setText(R.id.tv_main_title, item.getMain_title())
                    .setText(R.id.tv_sub_title, item.getSub_title());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            if (item.getPro_pic() != null && item.getPro_pic().size() > 0) {
                Glide.with(mContext).load(item.getPro_pic().get(0).getUrl())
                        .apply(options)
                        .into((ImageView) helper.getView(R.id.iv_tupian));
            }
        }
    }

    class CommentAdapter extends BaseQuickAdapter<HomeBean.DatasEntity.RecommendEntity, BaseViewHolder> {

        public CommentAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DatasEntity.RecommendEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DatasEntity.RecommendEntity item) {
            helper.setText(R.id.tv_main_title, item.getMain_title())
                    .setText(R.id.tv_sub_title, item.getSub_title())
                    .setText(R.id.tv_price, "￥" + item.getPro_price());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getPro_pic().get(0).getUrl())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    class MiddleAdapter extends BaseQuickAdapter<HomeBean.DatasEntity.ProClassEntity, BaseViewHolder> {

        public MiddleAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean.DatasEntity.ProClassEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean.DatasEntity.ProClassEntity item) {
            helper.setText(R.id.tv_name, item.getClass_name());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_circle)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getUrl())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }
}
