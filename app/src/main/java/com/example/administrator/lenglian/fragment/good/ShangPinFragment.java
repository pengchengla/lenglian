package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.utils.BannerUtils;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.view.MyRatingBar;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class ShangPinFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView recycler_canshu, recycler_pingjia, recycler_tuijian;
    private Banner banner;
    private TextView tv_title, tv_share, tv_price, tv_yuanjia, tv_collect, tv_lijizulin, tv_jiaruzulin;
    private CanshuAdapter mCanshuAdapter;
    private List<String> picList = new ArrayList<>();
    private PingjiaAdapter mPingjiaAdapter;
    private CommentAdapter mCommentAdapter;
    private ImageView iv_collect;
    private LinearLayout ll_collect;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shangpin, null);
        recycler_canshu = (RecyclerView) view.findViewById(R.id.recycler_canshu);
        recycler_pingjia = (RecyclerView) view.findViewById(R.id.recycler_pingjia);
        recycler_tuijian = (RecyclerView) view.findViewById(R.id.recycler_tuijian);
        banner = (Banner) view.findViewById(R.id.banner);
        tv_share = (TextView) view.findViewById(R.id.tv_share);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_yuanjia = (TextView) view.findViewById(R.id.tv_yuanjia);
        tv_share.setOnClickListener(this);
        iv_collect = (ImageView) view.findViewById(R.id.iv_collect);
        tv_collect = (TextView) view.findViewById(R.id.tv_collect);
        ll_collect = (LinearLayout) view.findViewById(R.id.ll_collect);
        ll_collect.setOnClickListener(this);
        tv_lijizulin = (TextView) view.findViewById(R.id.tv_lijizulin);
        tv_lijizulin.setOnClickListener(this);
        tv_jiaruzulin = (TextView) view.findViewById(R.id.tv_jiaruzulin);
        tv_jiaruzulin.setOnClickListener(this);
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("商品详情商品页"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("商品详情商品页");
    }

    @Override
    protected void initData() {
        picList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
        picList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
        picList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4267222417,1017407570&fm=200&gp=0.jpg");
        BannerUtils.startBanner(banner, picList);
        SpannableString spannableString = new SpannableString("原价:￥2999");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_yuanjia.setText(spannableString);
        recycler_canshu.setLayoutManager(new GridLayoutManager(mContext, 2));
        recycler_canshu.setNestedScrollingEnabled(false);
        recycler_pingjia.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_pingjia.setNestedScrollingEnabled(false);
        recycler_tuijian.setLayoutManager(new GridLayoutManager(mContext, 3));
        recycler_tuijian.setNestedScrollingEnabled(false);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mCanshuAdapter = new CanshuAdapter(R.layout.item_canshu, list);
        recycler_canshu.setAdapter(mCanshuAdapter);

        mPingjiaAdapter = new PingjiaAdapter(R.layout.item_pingjia, list);
        recycler_pingjia.setAdapter(mPingjiaAdapter);

        mCommentAdapter = new CommentAdapter(R.layout.comment_item, list);
        recycler_tuijian.setAdapter(mCommentAdapter);
        mCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mContext,GoodDetailActivity.class));
            }
        });
    }

    class CanshuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CanshuAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    class PingjiaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PingjiaAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            RecyclerView recycler_photo = helper.getView(R.id.recycler_ping_photo);
            recycler_photo.setLayoutManager(new GridLayoutManager(mContext, 3));
            PingPhotoAdapter pingPhotoAdapter = new PingPhotoAdapter(R.layout.item_photo, picList);
            recycler_photo.setAdapter(pingPhotoAdapter);
            MyRatingBar ratingBar = helper.getView(R.id.ratingbar);
            ratingBar.setClickable(false);//设置可否点击
            ratingBar.setStar(3.0f);//设置显示的星星个数
            ratingBar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星


            TextView tv_phone = helper.getView(R.id.tv_phone);
            String phone="15811337458";
            String phone1 = phone.substring(0, 3);
            String phone2 = phone.substring(7, 11);
            tv_phone.setText(phone1+"****"+phone2);
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

    class CommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CommentAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
        }
    }

    private boolean isCollected;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share:
                break;
            case R.id.ll_collect:
                if (isCollected) {
                    iv_collect.setImageResource(R.drawable.icon_uncollect);
                    tv_collect.setText("收藏");
                    isCollected = false;
                } else if (!isCollected) {
                    iv_collect.setImageResource(R.drawable.icon_collect);
                    tv_collect.setText("已收藏");
                    isCollected = true;
                }
                break;
            case R.id.tv_lijizulin:
                showCarDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion, 1);
                break;
            case R.id.tv_jiaruzulin:
                showCarDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion, 2);
                break;
        }
    }

    private void showCarDialog(int grary, int animationStyle, final int type) {
        BaseDialog.Builder builder = new BaseDialog.Builder(mContext);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_car)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.getView(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    startActivity(new Intent(mContext,QueRenOrderActivity.class));
                } else if (type == 2) {
                    Toast.makeText(mContext, "已成功加入租赁车", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
