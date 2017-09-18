package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
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
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.CuXiaoBean;
import com.example.administrator.lenglian.fragment.good.GoodDetailActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class CuXiaoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private RecyclerView recycler_cuxiao;
    private CuXiaoAdapter mCuXiaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cu_xiao);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        recycler_cuxiao = (RecyclerView) findViewById(R.id.recycler_cuxiao);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("促销界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("促销界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    private void initData() {
        recycler_cuxiao.setLayoutManager(new LinearLayoutManager(this));
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("pro_id", "");
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listSale", arrayMap, new BaseObserver1<CuXiaoBean>("") {
            @Override
            public void onSuccess(CuXiaoBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mCuXiaoAdapter = new CuXiaoAdapter(R.layout.cuxiao_item, result.getDatas());
                    recycler_cuxiao.setAdapter(mCuXiaoAdapter);
                    mCuXiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(CuXiaoActivity.this, GoodDetailActivity.class);
                            intent.putExtra("id", mCuXiaoAdapter.getData().get(position).getPro_id());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(CuXiaoActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CuXiaoAdapter extends BaseQuickAdapter<CuXiaoBean.DatasEntity, BaseViewHolder> {

        public CuXiaoAdapter(@LayoutRes int layoutResId, @Nullable List<CuXiaoBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CuXiaoBean.DatasEntity item) {
            SpannableString spannableString = new SpannableString("原价:￥"+item.getPro_price());
            StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
            spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            helper.setText(R.id.tv_price_pro, spannableString)
            .setText(R.id.tv_title,item.getMain_title())
            .setText(R.id.tv_price_now,"￥"+item.getSale_price());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getPro_pic())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }
}
