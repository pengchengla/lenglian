package com.example.administrator.lenglian.fragment.good;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class XiangQingFragment extends BaseFragment {
    private RadioGroup rgp;
    private NestedScrollView ll_shouhou;
    private LinearLayout ll_canshu, ll_tuwen;
    private TextView tv_color, tv_zhileng, tv_kongwen, tv_dianliang, tv_goodname, tv_pinpai, tv_xinghao, tv_type;
    private TextView tv_baozhuang, tv_fuwu, tv_jiage, tv_nengxiao;
    private RecyclerView recycler_photo;
    private PhotoAdapter mPhotoAdapter;
    private String mId;
    private GoodDetailBean.DatasEntity mDatas;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_xiangqing, null);
        rgp = (RadioGroup) view.findViewById(R.id.rgp);
        ll_canshu = (LinearLayout) view.findViewById(R.id.ll_canshu);
        ll_shouhou = (NestedScrollView) view.findViewById(R.id.ll_shouhou);
        ll_tuwen = (LinearLayout) view.findViewById(R.id.ll_tuwen);
        tv_color = (TextView) view.findViewById(R.id.tv_color);
        tv_zhileng = (TextView) view.findViewById(R.id.tv_zhileng);
        tv_kongwen = (TextView) view.findViewById(R.id.tv_kongwen);
        tv_dianliang = (TextView) view.findViewById(R.id.tv_dianliang);
        tv_goodname = (TextView) view.findViewById(R.id.tv_goodname);
        tv_pinpai = (TextView) view.findViewById(R.id.tv_pinpai);
        tv_xinghao = (TextView) view.findViewById(R.id.tv_xinghao);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        tv_baozhuang = (TextView) view.findViewById(R.id.tv_baozhuang);
        tv_fuwu = (TextView) view.findViewById(R.id.tv_fuwu);
        tv_jiage = (TextView) view.findViewById(R.id.tv_jiage);
        tv_nengxiao = (TextView) view.findViewById(R.id.tv_nengxiao);
        recycler_photo = (RecyclerView) view.findViewById(R.id.recycler_photo);
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
        arrayMap.put("user_id", SpUtils.getString(mContext,"user_id",""));
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
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        mPhotoAdapter = new PhotoAdapter(R.layout.good_detail_item_photo, list);
        recycler_photo.setAdapter(mPhotoAdapter);
    }

    private void initShouhou() {
        tv_baozhuang.setText(mDatas.getPro_list());
        tv_fuwu.setText(mDatas.getPro_rent());
        tv_jiage.setText(mDatas.getPrice_introduce());
        tv_nengxiao.setText(mDatas.getPower_introduce());
    }

    private void initRule() {
        tv_color.setText(mDatas.getPro_color());
        tv_zhileng.setText(mDatas.getCool_type());
        tv_kongwen.setText(mDatas.getControl_type());
        tv_dianliang.setText(mDatas.getPro_power());
        tv_xinghao.setText(mDatas.getPro_model());
        tv_goodname.setText(mDatas.getPro_name());
        tv_pinpai.setText(mDatas.getPro_brand());
        tv_type.setText(mDatas.getClass_name());
    }

    class PhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PhotoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            //如果在布局中设置图片的高度是自适应的话，图片就加载不出来，除非给个固定的高度，这是为什么呢？
            Glide.with(mContext).load(R.drawable.changxiaotupian)
                    .into((ImageView) helper.getView(R.id.iv_photo));
        }
    }
}
