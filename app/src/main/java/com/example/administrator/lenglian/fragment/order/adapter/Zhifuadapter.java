package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.example.administrator.lenglian.fragment.order.bean.Zhifubean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.PayUtil;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Zhifuadapter extends RecyclerView.Adapter<Zhifuadapter.MyViewholder> {
    private Context context;
    private List<Zhifubean.DatasBean> list;
    private Dingadapter.OnItemClickListener mOnItemClickListener = null;

    public Zhifuadapter(Context context, List<Zhifubean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

         MyViewholder myViewholder=new MyViewholder(LayoutInflater.from(context).inflate(R.layout.order_pay,parent,false));

        return myViewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, final int position) {
        if (list.get(position).getPro_pic().get(0).getUrl() != null&&list.get(position).getPro_pic().get(0).getUrl().length()>0) {


            RequestOptions options = new RequestOptions()
                    .error(R.drawable.default_square)
                    .priority(Priority.NORMAL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(context).load(list.get(position).getPro_pic().get(0).getUrl())
                    .apply(options)
                    .into(holder.iv_tupian);
        }
        holder.orderlist_count.setText(list.get(position).getMain_title());
        holder.textView.setText(list.get(position).getPro_price());


        //final Payadapter.ViewHolder finalHolder = holder;
        holder.order_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
             //   int i=(int) finalHolder.order_pause.getTag();
                pausevoid(position);
                EventMessage eventMessage = new EventMessage("qu");
                EventBus.getDefault().postSticky(eventMessage);


            }
        });
        holder.order_zhifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayUtil payUtil=new PayUtil(context,list.get(position).getOrder_num(),1);

                payUtil.showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,context);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        public TextView tv_back;
        public ImageView iv_tupian;
        public TextView orderlist_count;
        public TextView textView;
        public TextView order_pause;
        public TextView order_zhifi;
        public MyViewholder(View convertView) {
            super(convertView);
            iv_tupian = (ImageView)  convertView.findViewById(R.id.pay_tupian);
            orderlist_count = (TextView)  convertView.findViewById(R.id.paylist_count);
            textView = (TextView)  convertView.findViewById(R.id.pay_price);
            order_pause = (TextView)  convertView.findViewById(R.id.order_pau);
            order_zhifi = (TextView)  convertView.findViewById(R.id.order_zhi);
        }
    }
    public void pausevoid(final int position){
          /*
       向服务器传送取消的订单
      */
        Map<String,String> map=new HashMap<>();
        map.put("order_id",list.get(position).getOrder_id());
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=Order/cancelOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200)  {
                    ToastUtils.showShort(context,"订单已取消");
                    EventMessage eventMessage = new EventMessage("quxiao");
                    EventBus.getDefault().postSticky(eventMessage);
                    notifyDataSetChanged();
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
