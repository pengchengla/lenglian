package com.example.administrator.lenglian.fragment.good;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.base.UMShareActivity;
import com.example.administrator.lenglian.bean.CommentBean;
import com.example.administrator.lenglian.bean.EditCollectBean;
import com.example.administrator.lenglian.bean.GoodDetailBean;
import com.example.administrator.lenglian.bean.PingjiaBean;
import com.example.administrator.lenglian.db.LitePalHelper;
import com.example.administrator.lenglian.db.ShopCarBean;
import com.example.administrator.lenglian.listener.SnappingStepperValueChangeListener;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BannerUtils;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.view.MyRatingBar;
import com.example.administrator.lenglian.view.SnappingStepper;
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
    private TextView tv_title, tv_share, tv_price, tv_pingjia_count,
            tv_yuanjia, tv_collect, tv_lijizulin, tv_jiaruzulin;
    private CanshuAdapter mCanshuAdapter;
    private List<String> picList = new ArrayList<>();
    private List<String> canshunameList = new ArrayList<>();
    private List<String> canshuvalueList = new ArrayList<>();
    private PingjiaAdapter mPingjiaAdapter;
    private CommentAdapter mCommentAdapter;
    private ImageView iv_collect;
    private LinearLayout ll_collect;
    private String mId;
    private ImageView iv_isSale;
    private GoodDetailBean.DatasEntity mDatas;
    private int duration = 1;

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
        tv_pingjia_count = (TextView) view.findViewById(R.id.tv_pingjia_count);
        tv_share.setOnClickListener(this);
        iv_collect = (ImageView) view.findViewById(R.id.iv_collect);
        tv_collect = (TextView) view.findViewById(R.id.tv_collect);
        ll_collect = (LinearLayout) view.findViewById(R.id.ll_collect);
        ll_collect.setOnClickListener(this);
        tv_lijizulin = (TextView) view.findViewById(R.id.tv_lijizulin);
        tv_lijizulin.setOnClickListener(this);
        tv_jiaruzulin = (TextView) view.findViewById(R.id.tv_jiaruzulin);
        tv_jiaruzulin.setOnClickListener(this);
        iv_isSale = (ImageView) view.findViewById(R.id.iv_isSale);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
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
        recycler_canshu.setLayoutManager(new GridLayoutManager(mContext, 2));
        recycler_canshu.setNestedScrollingEnabled(false);
        recycler_pingjia.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_pingjia.setNestedScrollingEnabled(false);
        recycler_tuijian.setLayoutManager(new GridLayoutManager(mContext, 3));
        recycler_tuijian.setNestedScrollingEnabled(false);
        if (canshunameList.size() == 0) {
            canshunameList.add("颜色");
            canshunameList.add("制冷方式");
            canshunameList.add("控温方式");
            canshunameList.add("日耗电量");
        }
        mId = getActivity().getIntent().getStringExtra("id");
        //        Toast.makeText(mContext, " "+mId, Toast.LENGTH_SHORT).show();
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("pro_id", mId);
        arrayMap.put("user_id", SpUtils.getString(mContext, "user_id", ""));
        RetrofitManager.get(MyContants.BASEURL + "s=Product/profileProduct", arrayMap, new BaseObserver1<GoodDetailBean>("") {
            @Override
            public void onSuccess(GoodDetailBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mDatas = result.getDatas();
                    tv_title.setText(mDatas.getMain_title());
                    if (mDatas.getSale_price().equals("0")) {
                        iv_isSale.setVisibility(View.GONE);
                        tv_price.setText("￥" + mDatas.getPro_price() + "/月");
                        tv_yuanjia.setVisibility(View.GONE);
                    } else {
                        iv_isSale.setVisibility(View.VISIBLE);
                        tv_price.setText("￥" + mDatas.getSale_price() + "/月");
                        tv_yuanjia.setVisibility(View.VISIBLE);
                        SpannableString spannableString = new SpannableString("原价:￥" + mDatas.getPro_price());
                        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        tv_yuanjia.setText(spannableString);
                    }
                    if (canshuvalueList.size() == 0) {
                        canshuvalueList.add(mDatas.getPro_color());
                        canshuvalueList.add(mDatas.getCool_type());
                        canshuvalueList.add(mDatas.getControl_type());
                        canshuvalueList.add(mDatas.getPro_power());
                    }
                    mCanshuAdapter = new CanshuAdapter(R.layout.item_canshu, canshuvalueList);
                    recycler_canshu.setAdapter(mCanshuAdapter);

                    if (picList.size() == 0) {
                        for (int i = 0; i < mDatas.getPro_pic().size(); i++) {
                            picList.add(mDatas.getPro_pic().get(i).getUrl());
                        }
                    }
                    BannerUtils.startBanner(banner, picList);
                    if (mDatas.getCollect() == 0) {
                        isCollected = false;
                    } else {
                        isCollected = true;
                    }
                    collect_id = mDatas.getCollect_id();
                    refreshCollect();
                } else {

                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });

        initComment();
        initPingjia();
    }

    private void initPingjia() {
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("pro_id", mId);
        arrayMap2.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listComment", arrayMap2, new BaseObserver1<PingjiaBean>("") {
            @Override
            public void onSuccess(PingjiaBean result, String tag) {
                if (result.getCode() == 200) {
                    if (result.getDatas().size() > 99) {
                        tv_pingjia_count.setText("99+");
                    } else {
                        tv_pingjia_count.setText(result.getDatas().size() + "");
                    }
                    mPingjiaAdapter = new PingjiaAdapter(R.layout.item_pingjia, result.getDatas());
                    recycler_pingjia.setAdapter(mPingjiaAdapter);
                } else {
                    //101是没有数据
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void initComment() {
        ArrayMap arrayMap1 = new ArrayMap();
        arrayMap1.put("pro_id", mId);
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listRecommend", arrayMap1, new BaseObserver1<CommentBean>("") {
            @Override
            public void onSuccess(CommentBean result, String tag) {
                if (result.getCode() == 200) {
                    mCommentAdapter = new CommentAdapter(R.layout.comment_item, result.getDatas());
                    recycler_tuijian.setAdapter(mCommentAdapter);
                    mCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            startActivity(new Intent(mContext, GoodDetailActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void refreshCollect() {
        if (isCollected) {
            iv_collect.setImageResource(R.drawable.icon_collect);
            tv_collect.setText("已收藏");
        } else if (!isCollected) {
            iv_collect.setImageResource(R.drawable.icon_uncollect);
            tv_collect.setText("收藏");
        }
    }


    class CanshuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CanshuAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_name, canshunameList.get(helper.getAdapterPosition()))
                    .setText(R.id.tv_value, item);
        }
    }

    class PingjiaAdapter extends BaseQuickAdapter<PingjiaBean.DatasEntity, BaseViewHolder> {

        public PingjiaAdapter(@LayoutRes int layoutResId, @Nullable List<PingjiaBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PingjiaBean.DatasEntity item) {
            RecyclerView recycler_photo = helper.getView(R.id.recycler_ping_photo);
            recycler_photo.setLayoutManager(new GridLayoutManager(mContext, 3));
            PingPhotoAdapter pingPhotoAdapter = new PingPhotoAdapter(R.layout.item_photo, item.getPic());
            recycler_photo.setAdapter(pingPhotoAdapter);
            MyRatingBar ratingBar = helper.getView(R.id.ratingbar);
            ratingBar.setClickable(false);//设置可否点击
            ratingBar.setStar(Float.parseFloat(item.getPro_score()));//设置显示的星星个数
            ratingBar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星
            helper.setText(R.id.tv_content, item.getContent())
                    .setText(R.id.tv_time, item.getCommit_time())
                    .setText(R.id.tv_add_content, item.getAdd_content())
                    .setVisible(R.id.tv_add_content, TextUtils.isEmpty(item.getAdd_content()) ? false : true);

            TextView tv_phone = helper.getView(R.id.tv_phone);
            String phone = item.getMobile();
            String phone1 = phone.substring(0, 3);
            String phone2 = phone.substring(7, 11);
            tv_phone.setText(phone1 + "****" + phone2);
        }
    }

    class PingPhotoAdapter extends BaseQuickAdapter<PingjiaBean.DatasEntity.PicEntity, BaseViewHolder> {

        public PingPhotoAdapter(@LayoutRes int layoutResId, @Nullable List<PingjiaBean.DatasEntity.PicEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PingjiaBean.DatasEntity.PicEntity item) {
            //            Toast.makeText(mContext, "图片地址"+item.getUrl(), Toast.LENGTH_SHORT).show();
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getUrl())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }

    }

    class CommentAdapter extends BaseQuickAdapter<CommentBean.DatasEntity, BaseViewHolder> {

        public CommentAdapter(@LayoutRes int layoutResId, @Nullable List<CommentBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CommentBean.DatasEntity item) {
            helper.setText(R.id.tv_name, item.getMain_title());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getSingle_pic())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    private boolean isCollected;
    private String collect_id;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_share:
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }
                UMShareActivity.shareWebUrl("",mDatas.getMain_title(),mDatas.getPro_pic().get(0).getUrl()
                ,mDatas.getSub_title(),mActivity);
                break;
            case R.id.ll_collect:
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }
                if (isCollected) {
                    collect(true);
                } else {
                    collect(false);
                }
                break;
            case R.id.tv_lijizulin:
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }
                showCarDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion, 1);
                break;
            case R.id.tv_jiaruzulin:
                if (!MyUtils.islogin(mContext)) {
                    MyUtils.showloginDialog(mContext,Gravity.CENTER, R.style.Alpah_aniamtion);
                    return;
                }
                showCarDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion, 2);
                break;
        }
    }

    private void collect(boolean yijingshoucang) {
        ArrayMap arrayMap2 = new ArrayMap();
        if (yijingshoucang) {
            arrayMap2.put("token", MyUtils.getToken());
            arrayMap2.put("collect_id", collect_id);
        } else {
            arrayMap2.put("pro_id", mId);
            arrayMap2.put("token", MyUtils.getToken());
            arrayMap2.put("user_id", SpUtils.getString(mContext, "user_id", ""));
        }
        RetrofitManager.get(MyContants.BASEURL + "s=User/editCollect", arrayMap2, new BaseObserver1<EditCollectBean>("") {
            @Override
            public void onSuccess(EditCollectBean result, String tag) {
                if (result.getCode() == 200) {
                    isCollected = !isCollected;
                    collect_id = result.getDatas().getCollect_id();
                    refreshCollect();
                } else {
                    //101是没有数据
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
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
        final SnappingStepper stepper = dialog.getView(R.id.stepper);
        SnappingStepper ssp = dialog.getView(R.id.stepper);
        ssp.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                duration = value;
            }
        });
        dialog.getView(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    Intent intent = new Intent(mContext, QueRenOrderActivity.class);
                    intent.putExtra("id", mId);
                    intent.putExtra("duration", stepper.getValue() + "");
                    intent.putExtra("imgUrl", mDatas.getPro_pic().get(0).getUrl());
                    intent.putExtra("name", mDatas.getMain_title());
                    intent.putExtra("price", mDatas.getSale_price().equals("0") ?
                            mDatas.getPro_price() : mDatas.getSale_price());
                    intent.putExtra("yajin",mDatas.getPro_deposit());
                    intent.putExtra("peisongfei",mDatas.getExpress_money());
                    startActivity(intent);
                } else if (type == 2) {
                    insertCar();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void insertCar() {
        if (LitePalHelper.isInserted(mDatas.getPro_id())) {
            ShopCarBean shopCarBean = LitePalHelper.
                    getInsertedOneBean(mDatas.getPro_id());
            if (shopCarBean != null) {
                LitePalHelper.add(shopCarBean);
            }
        } else {
            LitePalHelper.add(new ShopCarBean(mDatas.getPro_id(),
                    mDatas.getMain_title(),
                    mDatas.getSale_price().equals("0") ? mDatas.getPro_price() : mDatas.getSale_price(),
                    mDatas.getPro_pic().get(0).getUrl(),
                    duration,mDatas.getPro_deposit(),mDatas.getExpress_money()
                    ));
        }
    }
}
