package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.bean.MsgBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private RecyclerView recycler_msg;
    private MsgAdapter mMsgAdapter;
    private LinearLayout kong;
    private List<MsgBean.DatasEntity> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        recycler_msg = (RecyclerView) findViewById(R.id.recycler_msg);
        recycler_msg.setLayoutManager(new LinearLayoutManager(this));
        kong = (LinearLayout) findViewById(R.id.kong);
        initData();
    }

    private void initData() {
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("user_id", SpUtils.getString(MessageActivity.this, "user_id", ""));
        arrayMap.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=User/listNews", arrayMap, new BaseObserver1<MsgBean>("") {
            @Override
            public void onSuccess(MsgBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mDatas = result.getDatas();
                    if (mDatas == null || mDatas.size() == 0) {
                        recycler_msg.setVisibility(View.GONE);
                    } else {
                        recycler_msg.setVisibility(View.VISIBLE);
                        mMsgAdapter = new MsgAdapter(R.layout.item_msg, mDatas);
                        recycler_msg.setAdapter(mMsgAdapter);
                    }
                } else {
                    //                    Toast.makeText(MessageActivity.this, "暂时没有消息", Toast.LENGTH_SHORT).show();
                    recycler_msg.setVisibility(View.GONE);
                    kong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(MessageActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("消息界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("消息界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
        }
    }

    class MsgAdapter extends BaseQuickAdapter<MsgBean.DatasEntity, BaseViewHolder> {

        public MsgAdapter(@LayoutRes int layoutResId, @Nullable List<MsgBean.DatasEntity> data) {
            super(layoutResId, data);

        }

        @Override
        protected void convert(final BaseViewHolder helper, final MsgBean.DatasEntity item) {
            String msgType = "";
            if (item.getNews_type().equals("1")) {
                msgType = "订单通知";
            } else if (item.getNews_type().equals("2")) {
                msgType = "报修通知";
            } else {
                msgType = "亲.想你了";
            }
            helper.setText(R.id.tv_msg_type, msgType)
                    .setText(R.id.tv_date, item.getSend_time())
                    .setText(R.id.tv_msg_content, item.getNews());
            helper.getView(R.id.right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayMap arrayMap = new ArrayMap<String, String>();
                    arrayMap.put("user_id", SpUtils.getString(MessageActivity.this, "user_id", ""));
                    arrayMap.put("token", MyUtils.getToken());
                    arrayMap.put("news_id", item.getNews_id());
                    RetrofitManager.get(MyContants.BASEURL + "s=User/setRead", arrayMap, new BaseObserver1<EasyBean>("") {
                        @Override
                        public void onSuccess(EasyBean result, String tag) {

                            //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                            if (result.getCode() == 200) {
                                Toast.makeText(MessageActivity.this, "删除消息成功", Toast.LENGTH_SHORT).show();
                                initData();
                                //                                mDatas.remove(helper.getAdapterPosition());
                                //                                mMsgAdapter.setNewData(mDatas);
                                //                                recycler_msg.setAdapter(mMsgAdapter);
                            } else {
                                Toast.makeText(MessageActivity.this, "删除消息失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailed(int code) {
                            Toast.makeText(MessageActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
