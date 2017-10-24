package com.example.administrator.lenglian.fragment.good;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.example.administrator.lenglian.bean.GoodDetailBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.SpUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class XiangQingFragment extends BaseFragment {
    private RadioGroup rgp;
    private LinearLayout ll_shouhou;
    private LinearLayout ll_canshu, ll_tuwen;
    private RecyclerView recycler_photo, recycler_shouhou, recycler_canshu;
    private PhotoAdapter mPhotoAdapter;
    private String mId;
    private GoodDetailBean.DatasEntity mDatas;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_xiangqing, null);
        rgp = (RadioGroup) view.findViewById(R.id.rgp);
        ll_canshu = (LinearLayout) view.findViewById(R.id.ll_canshu);
        ll_shouhou = (LinearLayout) view.findViewById(R.id.ll_shouhou);
        ll_tuwen = (LinearLayout) view.findViewById(R.id.ll_tuwen);
        recycler_photo = (RecyclerView) view.findViewById(R.id.recycler_photo);
        recycler_shouhou = (RecyclerView) view.findViewById(R.id.recycler_shouhou);
        recycler_canshu = (RecyclerView) view.findViewById(R.id.recycler_canshu);
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("商品详情参数页"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("商品详情参数页");
    }

    @Override
    protected void initData() {
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_tuwen) {
                    ll_tuwen.setVisibility(View.VISIBLE);
                    ll_shouhou.setVisibility(View.GONE);
                    ll_canshu.setVisibility(View.GONE);
                } else if (checkedId == R.id.rb_rule) {
                    ll_canshu.setVisibility(View.VISIBLE);
                    ll_tuwen.setVisibility(View.GONE);
                    ll_shouhou.setVisibility(View.GONE);
                } else if (checkedId == R.id.rb_shouhou) {
                    ll_shouhou.setVisibility(View.VISIBLE);
                    ll_tuwen.setVisibility(View.GONE);
                    ll_canshu.setVisibility(View.GONE);
                }
            }
        });
        rgp.check(R.id.rb_tuwen);
        mId = getActivity().getIntent().getStringExtra("id");
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("pro_id", mId);
        arrayMap.put("user_id", SpUtils.getString(mContext, "user_id", ""));
        RetrofitManager.get(MyContants.BASEURL + "s=Product/profileProduct", arrayMap, new BaseObserver1<GoodDetailBean>("") {
            @Override
            public void onSuccess(GoodDetailBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mDatas = result.getDatas();
                    initTuwen();
                    initRule();
                    initShouhou();
                } else {

                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(mContext, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTuwen() {
        recycler_photo.setLayoutManager(new LinearLayoutManager(mContext));
        mPhotoAdapter = new PhotoAdapter(R.layout.good_detail_item_photo, mDatas.getProfile_pic());
        recycler_photo.setAdapter(mPhotoAdapter);
    }

    private void initShouhou() {
        recycler_shouhou.setLayoutManager(new LinearLayoutManager(mContext));
        ShouHouAdapter shouHouAdapter = new ShouHouAdapter(R.layout.item_shouhou, mDatas.getIntroduce());
        recycler_shouhou.setAdapter(shouHouAdapter);
    }

    private void initRule() {
        recycler_canshu.setLayoutManager(new LinearLayoutManager(mContext));
        CanshuAdapter canshuAdapter = new CanshuAdapter(R.layout.item_guige, mDatas.getTech());
        recycler_canshu.setAdapter(canshuAdapter);
    }

    class PhotoAdapter extends BaseQuickAdapter<GoodDetailBean.DatasEntity.ProfilePicEntity, BaseViewHolder> {

        public PhotoAdapter(@LayoutRes int layoutResId, @Nullable List<GoodDetailBean.DatasEntity.ProfilePicEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodDetailBean.DatasEntity.ProfilePicEntity item) {
            //如果在布局中设置图片的高度是自适应的话，图片就加载不出来，除非给个固定的高度，这是为什么呢？
            RequestOptions options = new RequestOptions()
//                    .centerCrop()
                    .error(R.drawable.default_banner)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getUrl())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_photo));
        }
    }

    class ShouHouAdapter extends BaseQuickAdapter<GoodDetailBean.DatasEntity.IntroduceEntity, BaseViewHolder> {

        public ShouHouAdapter(@LayoutRes int layoutResId, @Nullable List<GoodDetailBean.DatasEntity.IntroduceEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodDetailBean.DatasEntity.IntroduceEntity item) {
            helper.setText(R.id.tv_title, item.getKey())
                    .setText(R.id.tv_content, item.getValue());
        }
    }

    class CanshuAdapter extends BaseQuickAdapter<GoodDetailBean.DatasEntity.TechEntity, BaseViewHolder> {

        public CanshuAdapter(@LayoutRes int layoutResId, @Nullable List<GoodDetailBean.DatasEntity.TechEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GoodDetailBean.DatasEntity.TechEntity item) {
            helper.setText(R.id.tv_title, item.getKey())
                    .setText(R.id.tv_content, item.getValue());
        }
    }
}
