package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EditCollectBean;
import com.example.administrator.lenglian.fragment.mine.bean.CollectListBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class CollectionActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView collect_bianji;
    private RelativeLayout collect_title;
    private RecyclerView recycler_collect;
    private RelativeLayout rl_select_delete;
    private CheckBox cb_checkAll;
    private Button btn_delete;
    private boolean isEditing;
    private CollectionAdapter mCollectionadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_collect);
        initView();
        inindata();
    }

    private void inindata() {
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("token", MyUtils.getToken());
        arrayMap2.put("user_id", SpUtils.getString(CollectionActivity.this, "user_id", ""));
        RetrofitManager.get(MyContants.BASEURL + "s=User/listCollect", arrayMap2, new BaseObserver1<CollectListBean>("") {
            @Override
            public void onSuccess(CollectListBean result, String tag) {
                if (result.getCode() == 200) {
                    mCollectionadapter = new CollectionAdapter(R.layout.collect_item, result.getDatas());
                    recycler_collect.setAdapter(mCollectionadapter);
                } else {
                    //101是没有数据
                    Toast.makeText(CollectionActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
        mCollectionadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mCollectionadapter.getData().get(position)
                        .setChecked(!mCollectionadapter.getData().get(position).isChecked());
                mCollectionadapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        collect_bianji = (TextView) findViewById(R.id.collect_bianji);
        collect_title = (RelativeLayout) findViewById(R.id.collect_title);
        recycler_collect = (RecyclerView) findViewById(R.id.list_collect);
        recycler_collect.setLayoutManager(new LinearLayoutManager(this));
        rl_select_delete = (RelativeLayout) findViewById(R.id.select_delte);
        cb_checkAll = (CheckBox) findViewById(R.id.collect_check);
        btn_delete = (Button) findViewById(R.id.collect_btn);
        tv_back.setOnClickListener(this);
        collect_bianji.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        cb_checkAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.collect_bianji:
                isEditing = !isEditing;
                if (isEditing) {
                    collect_bianji.setText("完成");
                    rl_select_delete.setVisibility(View.VISIBLE);
                } else {
                    collect_bianji.setText("编辑");
                    rl_select_delete.setVisibility(View.GONE);
                }
                mCollectionadapter.notifyDataSetChanged();
                break;
            case R.id.collect_btn://删除
                deleteCollect();
                break;
            case R.id.collect_check://全选

                break;
        }
    }

    private void deleteCollect() {
        if (!hasItemChecked()) {
            Toast.makeText(this, "请选中后再删除", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayMap arrayMap2 = new ArrayMap();
        arrayMap2.put("token", MyUtils.getToken());
        arrayMap2.put("collect_id", getAllDeleteCollectIds());
        RetrofitManager.get(MyContants.BASEURL + "s=User/editCollect", arrayMap2, new BaseObserver1<EditCollectBean>("") {
            @Override
            public void onSuccess(EditCollectBean result, String tag) {
                if (result.getCode() == 200) {

                } else {
                    //101是没有数据
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private boolean hasItemChecked() {
        for (int i = 0; i < mCollectionadapter.getData().size(); i++) {
            if (mCollectionadapter.getData().get(i).isChecked()) {
                return true;
            }
        }
        return false;
    }

    private String getAllDeleteCollectIds() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mCollectionadapter.getData().size(); i++) {
            if (mCollectionadapter.getData().get(i).isChecked()) {
                builder.append(mCollectionadapter.getData().get(i).getCollect_id() + ",");
            }
        }
        String substring = builder.substring(builder.toString().length() - 1);
        if (substring.equals(",")) {//如果最后一个字符是逗号就去掉最后的那个逗号
            String substring1 = builder.substring(0, builder.toString().length() - 1);
            return substring1;
        } else {
            return builder.toString();
        }
    }

    class CollectionAdapter extends BaseQuickAdapter<CollectListBean.DatasEntity, BaseViewHolder> {
        public CollectionAdapter(@LayoutRes int layoutResId, @Nullable List<CollectListBean.DatasEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, CollectListBean.DatasEntity item) {
            if (isEditing) {
                helper.setVisible(R.id.collect_check, true);
            } else if (!isEditing) {
                helper.setVisible(R.id.collect_check, false);
            }
            helper.setText(R.id.collect_count, item.getMain_title())
                    .setChecked(R.id.collect_check, item.isChecked());
        }
    }
}
