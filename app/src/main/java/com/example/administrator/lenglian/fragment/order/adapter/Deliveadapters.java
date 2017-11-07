package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Deliveadapters extends RecyclerView.Adapter<Deliveadapters.MyViewHolder> {
    private Context context;
    private List<Dingdanbean.DatasBean> list;
    private Dingadapter.OnItemClickListener mOnItemClickListener = null;

    public Deliveadapters(Context context, List<Dingdanbean.DatasBean> list) {
        this.context = context;
        this.list = list;
        initLocation();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recying_item, parent, false));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
 /*
          加载图片
         */
        RequestOptions options = new RequestOptions()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getPro_pic().get(0).getUrl())
                .apply(options)
                .into(holder.iv_tupian);
        holder.orderlist_count.setText(list.get(position).getMain_title());
        holder.textView.setText(list.get(position).getPro_price());
        holder.reying_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(Gravity.CENTER,R.style.Alpah_aniamtion,position);

                EventMessage eventMessage = new EventMessage("dingshouhuo");
                EventBus.getDefault().postSticky(eventMessage);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_back;
        public ImageView iv_tupian, no_tu;
        public TextView orderlist_count;
        public TextView textView;
        public TextView reying_btn;

        public MyViewHolder(View convertView) {
            super(convertView);
            iv_tupian = (ImageView) convertView.findViewById(R.id.receving_tupian);
            orderlist_count = (TextView) convertView.findViewById(R.id.receving_count);
            textView = (TextView) convertView.findViewById(R.id.receving_price);
            reying_btn = (TextView) convertView.findViewById(R.id.recying_btn);
        }
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    //获取纬度
                    mLatitude = amapLocation.getLatitude();
                    //获取经度
                    mLongitude = amapLocation.getLongitude();
                    //                    Toast.makeText(MyBlueActivity.this, "纬度："+latitude+"  经度："+longitude, Toast.LENGTH_SHORT).show();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    //                    Log.e("AmapError", "location Error, ErrCode:"
                    //                            + amapLocation.getErrorCode() + ", errInfo:"
                    //                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private double mLatitude;
    private double mLongitude;

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    //确认收货
    private void recycing(final int position) {
        //网络请求
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(context, "user_id", ""));
        map.put("token", MyUtils.getToken());
        map.put("order_id", list.get(position).getOrder_id());
        map.put("longitude", mLongitude + "");
        map.put("latitude", mLatitude + "");
        RetrofitManager.post(MyContants.BASEURL + "s=User/commitOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if (result.getCode() == 200) {
                    ToastUtils.showShort(context, "确认收货");
                    notifyDataSetChanged();
                    //发送eventbus通知刷新界面数据修改状态
                    EventMessage eventMessage = new EventMessage("shouhuo");
                    EventBus.getDefault().postSticky(eventMessage);
                }

            }

            @Override
            public void onFailed(int code) {

            }
        });

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(Dingadapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    private void showDialog(int grary, int animationStyle, final int position) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_phone)
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
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText("   是否确认收货？   ");
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
                //关闭dialog
                dialog.close();
                //确认收货
                recycing(position);
            }
        });
    }
}
