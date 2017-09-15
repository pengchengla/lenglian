package com.example.administrator.lenglian.fragment.good;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.PingjiaBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.view.MyRatingBar;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PingJiaFragment extends BaseFragment {
    private RecyclerView recycler_pingjia;
    private PingjiaAdapter mPingjiaAdapter;
    private String mId;
    private TextView tv_pingjia_count;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_pingjia, null);
        recycler_pingjia = (RecyclerView) view.findViewById(R.id.recycler_pingjia);
        tv_pingjia_count = (TextView) view.findViewById(R.id.tv_pingjia_count);
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
        recycler_pingjia.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_pingjia.setNestedScrollingEnabled(false);
        mId = getActivity().getIntent().getStringExtra("id");
//        Toast.makeText(mContext, " "+mId, Toast.LENGTH_SHORT).show();
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
                }else {
                    //101是没有数据
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
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
}
