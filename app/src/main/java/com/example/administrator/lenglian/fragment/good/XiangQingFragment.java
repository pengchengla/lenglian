package com.example.administrator.lenglian.fragment.good;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseFragment;
import com.squareup.picasso.Picasso;
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
        initTuwen();
        initRule();
        initShouhou();
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
        tv_baozhuang.setText("啦啦啦啦啦啦");
        tv_fuwu.setText("啦啦啦啦啦啦");
        tv_jiage.setText("啦啦啦啦啦啦");
        tv_nengxiao.setText("啦啦啦啦啦啦");
    }

    private void initRule() {
        tv_color.setText("啦啦啦啦啦");
        tv_zhileng.setText("啦啦啦啦啦");
        tv_kongwen.setText("啦啦啦啦啦");
        tv_dianliang.setText("啦啦啦啦啦");
        tv_xinghao.setText("啦啦啦啦啦");
        tv_goodname.setText("啦啦啦啦啦");
        tv_pinpai.setText("啦啦啦啦啦");
        tv_type.setText("啦啦啦啦啦");
    }

    class PhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public PhotoAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            //史上最强bug，现在还没弄明白为什么glide不行Picasso就行，到底是不是库出的问题
            Picasso.with(mContext).load(R.drawable.changxiaotupian).into((ImageView) helper.getView(R.id.iv_photo));
//            Glide.with(mContext).load(R.drawable.changxiaotupian).into((ImageView) helper.getView(R.id.iv_photo));
        }
    }
}
