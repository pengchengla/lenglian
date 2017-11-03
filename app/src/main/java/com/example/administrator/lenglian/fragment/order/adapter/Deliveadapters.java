package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recying_item,parent,false));

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
                //确认收货
                recycing(position);
                EventMessage eventMessage = new EventMessage("dingshouhuo");
                EventBus.getDefault().postSticky(eventMessage);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_back;
        public ImageView iv_tupian,no_tu;
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
    //确认收货
    private void recycing(final int position){
        //网络请求
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(context,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("order_id",list.get(position).getOrder_id());
        RetrofitManager.post(MyContants.BASEURL +"s=User/commitOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(context,"确认收货");
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
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(Dingadapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
