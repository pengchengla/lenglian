package com.example.administrator.lenglian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
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
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.SearchBean;
import com.example.administrator.lenglian.bean.SearchHistoryEntity;
import com.example.administrator.lenglian.fragment.good.GoodDetailActivity;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.view.CustomProgressDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_search;
    private TextView tv_back;
    private ImageView iv_delete;
    private RecyclerView recycler_lishi;
    private RecyclerView recycler_hot;
    private RecyclerView recycler_result;
    private List<String> hotList = new ArrayList<>();
    private RecyclerHistoryAdapter mHistoryAdapter;
    private RecyclerHotAdapter mHotAdapter;
    private ResultAdapter mResultAdapter;
    private LinearLayout ll_lishiandhot, ll_root_view;
    private List<SearchHistoryEntity> mHistoryList = new ArrayList<>();
    private int position;
    private CustomProgressDialog mDialog;

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
        recycler_result = (RecyclerView) findViewById(R.id.recycler_result);
        ll_lishiandhot = (LinearLayout) findViewById(R.id.ll_lishiandhot);
        ll_root_view = (LinearLayout) findViewById(R.id.ll_root_view);
        mDialog = new CustomProgressDialog(SearchActivity.this, R.style.myprogressdialog);
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("搜索界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("搜索界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    //判断本地数据中有没有存在搜索过的数据，查重
    private boolean isHasSelectData(String content) {
        if (mHistoryList == null || mHistoryList.size() == 0) {
            return false;
        }
        for (int i = 0; i < mHistoryList.size(); i++) {
            if (mHistoryList.get(i).getContent().equals(content)) {
                position = i;
                return true;
            }
        }
        return false;
    }

    private void doSavehistory(String content) {

        if (isHasSelectData(content)) {//查重
            mHistoryList.remove(position);
        }
        //后来搜索的文字放在集合中的第一个位置
        mHistoryList.add(0, new SearchHistoryEntity(content));

        if (mHistoryList.size() == 10) {//实现本地历史搜索记录最多不超过十个
            mHistoryList.remove(9);
        }
        //将这个mHistoryListData保存到sp中，其实sp中保存的就是这个mHistoryListData集合
        saveHistory();
    }

    /**
     * 保存历史查询记录
     */
    private void saveHistory() {
        SpUtils.putString(this, "history",
                new Gson().toJson(mHistoryList));//将java对象转换成json字符串进行保存
    }

    /**
     * 获取历史查询记录
     *
     * @return
     */
    private List<SearchHistoryEntity> getHistory() {
        String historyJson = SpUtils.getString(this, "history", "");
        if (historyJson != null && !historyJson.equals("")) {//必须要加上后面的判断，因为获取的字符串默认值就是空字符串
            //将json字符串转换成list集合
            return new Gson().fromJson(historyJson, new TypeToken<List<SearchHistoryEntity>>() {
            }.getType());
        }
        return new ArrayList<SearchHistoryEntity>();
    }

    private void initListener() {
        //本来下面这几行代码是解决afterTextChanged反应太快，但是并不管用
//        int inputType= InputType.TYPE_CLASS_TEXT |
//                InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
//        et_search.setInputType(inputType);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_search.getText().toString())) {
                    doSearch(et_search.getText().toString());
                } else {
                    ll_root_view.setBackgroundResource(R.color.white);
                    ll_lishiandhot.setVisibility(View.VISIBLE);
                    recycler_result.setVisibility(View.GONE);
                    mHistoryList = getHistory();//从本地取出来
                    if (mHistoryList != null && mHistoryList.size() > 0) {
                        iv_delete.setVisibility(View.VISIBLE);
                        if (mHistoryAdapter != null) {
                            mHistoryAdapter.notifyDataSetChanged();//刷新一下界面
                        } else {
                            mHistoryAdapter = new RecyclerHistoryAdapter(R.layout.history_item, mHistoryList);
                            recycler_lishi.setAdapter(mHistoryAdapter);
                        }
                    }
                }
            }
        });
    }

    private void doSearch(final String content) {
        mDialog.show();
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("key", content);
        arrayMap.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=Search/search", arrayMap, new BaseObserver1<SearchBean>("") {
            @Override
            public void onSuccess(SearchBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    ll_root_view.setBackgroundResource(R.color.huise);
                    ll_lishiandhot.setVisibility(View.GONE);
                    recycler_result.setVisibility(View.VISIBLE);
                    mResultAdapter = new ResultAdapter(R.layout.item_search_result, result.getDatas());
                    recycler_result.setAdapter(mResultAdapter);
                    doSavehistory(content);
                    mResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(SearchActivity.this, GoodDetailActivity.class);
                            intent.putExtra("id", mResultAdapter.getData().get(position).getPro_id());
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(SearchActivity.this, "没有找到该商品", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(SearchActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
    }

    private void initData() {
        recycler_lishi.setLayoutManager(new GridLayoutManager(this, 4));
        recycler_lishi.setNestedScrollingEnabled(false);
        recycler_hot.setLayoutManager(new GridLayoutManager(this, 4));
        recycler_hot.setNestedScrollingEnabled(false);
        recycler_result.setLayoutManager(new GridLayoutManager(this, 2));
        recycler_result.setNestedScrollingEnabled(false);
        mHistoryList = getHistory();//从本地取出来
        if (mHistoryList != null && mHistoryList.size() > 0) {
            mHistoryAdapter = new RecyclerHistoryAdapter(R.layout.history_item, mHistoryList);
            recycler_lishi.setAdapter(mHistoryAdapter);
            mHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    doSearch(mHistoryAdapter.getData().get(position).getContent());
                }
            });
        } else {
            iv_delete.setVisibility(View.GONE);
        }

        hotList.add("雪糕");
        hotList.add("雪糕");
        hotList.add("雪糕");
        hotList.add("雪糕");
        hotList.add("雪糕");
        hotList.add("雪糕");
        hotList.add("雪糕");
        hotList.add("雪糕");
        mHotAdapter = new RecyclerHotAdapter(R.layout.history_item, hotList);
        recycler_hot.setAdapter(mHotAdapter);
        mHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(SearchActivity.this, GoodDetailActivity.class));
            }
        });
    }

    class RecyclerHistoryAdapter extends BaseQuickAdapter<SearchHistoryEntity, BaseViewHolder> {

        public RecyclerHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<SearchHistoryEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchHistoryEntity item) {
            helper.setText(R.id.tv_history, item.getContent());
        }
    }

    class RecyclerHotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RecyclerHotAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_history, item);
        }
    }

    class ResultAdapter extends BaseQuickAdapter<SearchBean.DatasEntity, BaseViewHolder> {

        public ResultAdapter(@LayoutRes int layoutResId, @Nullable List<SearchBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchBean.DatasEntity item) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(mContext).load(item.getSingle_pic())
                    .apply(options)
                    .into((ImageView) helper.getView(R.id.iv_tupian));
            helper.setText(R.id.tv_title, item.getMain_title())
                    .setText(R.id.tv_price, "￥" + item.getPro_price());
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
                mHistoryList.clear();
                saveHistory();
                if (mHistoryAdapter != null) {
                    mHistoryAdapter.getData().clear();//如果不加这句的话第二次删除就会不生效，真不知道为什么
                    mHistoryAdapter.notifyDataSetChanged();
                }
                iv_delete.setVisibility(View.GONE);
                dialog.close();
            }
        });
    }

}
