package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.utils.BaseDialog;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_search;
    private TextView tv_back;
    private ImageView iv_delete;
    private RecyclerView recycler_lishi;
    private RecyclerView recycler_hot;
    private List<String> historyList = new ArrayList<>();
    private List<String> hotList = new ArrayList<>();
    private RecyclerHistoryAdapter mHistoryAdapter;
    private RecyclerHotAdapter mHotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);
        recycler_lishi = (RecyclerView) findViewById(R.id.recycler_lishi);
        recycler_hot = (RecyclerView) findViewById(R.id.recycler_hot);
        initData();
    }

    private void initData() {
        recycler_lishi.setLayoutManager(new GridLayoutManager(this, 4));
        recycler_lishi.setNestedScrollingEnabled(false);
        recycler_hot.setLayoutManager(new GridLayoutManager(this, 4));
        recycler_hot.setNestedScrollingEnabled(false);
        historyList.add("啦啦啦");
        historyList.add("啦啦啦");
        historyList.add("啦啦啦");
        historyList.add("啦啦啦");
        historyList.add("啦啦啦");
        historyList.add("啦啦啦");
        mHistoryAdapter = new RecyclerHistoryAdapter(R.layout.history_item, historyList);
        recycler_lishi.setAdapter(mHistoryAdapter);


        hotList.add("啦啦啦");
        hotList.add("啦啦啦");
        hotList.add("啦啦啦");
        hotList.add("啦啦啦");
        hotList.add("啦啦啦");
        hotList.add("啦啦啦");
        mHotAdapter = new RecyclerHotAdapter(R.layout.history_item, hotList);
        recycler_hot.setAdapter(mHotAdapter);
    }

    class RecyclerHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    class RecyclerHotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerHotAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_delete:
                showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
                break;
        }
    }

    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_history)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyList.clear();
                mHistoryAdapter.notifyDataSetChanged();
                dialog.close();
            }
        });
    }

}
