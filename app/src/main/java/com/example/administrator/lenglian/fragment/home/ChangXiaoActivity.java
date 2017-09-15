package com.example.administrator.lenglian.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.administrator.lenglian.bean.ChangXiaoBean;
import com.example.administrator.lenglian.fragment.good.GoodDetailActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class ChangXiaoActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private RecyclerView recycler_changxiao;
    private ChangxiaoAdapter mChangxiaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_xiao);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        recycler_changxiao = (RecyclerView) findViewById(R.id.recycler_changxiao);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("畅销界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("畅销界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    private void initData() {
        recycler_changxiao.setLayoutManager(new LinearLayoutManager(this));
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("pro_id", "");
        RetrofitManager.get(MyContants.BASEURL + "s=Product/listBest", arrayMap, new BaseObserver1<ChangXiaoBean>("") {
            @Override
            public void onSuccess(ChangXiaoBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mChangxiaoAdapter = new ChangxiaoAdapter(R.layout.changxiao_item,result.getDatas());
                    recycler_changxiao.setAdapter(mChangxiaoAdapter);
                    mChangxiaoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            startActivity(new Intent(ChangXiaoActivity.this, GoodDetailActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(ChangXiaoActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ChangxiaoAdapter extends BaseQuickAdapter<ChangXiaoBean.DatasEntity, BaseViewHolder> {

        public ChangxiaoAdapter(@LayoutRes int layoutResId, @Nullable List<ChangXiaoBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ChangXiaoBean.DatasEntity item) {
            helper.setText(R.id.tv_title,item.getMain_title())
                    .setText(R.id.tv_price,"￥"+item.getPro_price());
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
