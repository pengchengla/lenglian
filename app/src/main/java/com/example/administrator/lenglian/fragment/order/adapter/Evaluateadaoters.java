package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.blue.MyBlueActivity;
import com.example.administrator.lenglian.fragment.mine.ReturnActivity;
import com.example.administrator.lenglian.fragment.order.activity.RenewActivity;
import com.example.administrator.lenglian.fragment.order.activity.ShopdetailActivity;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Evaluateadaoters extends RecyclerView.Adapter<Evaluateadaoters.MyViewholder> {
    private Context context;
    private List<Dingdanbean.DatasBean> list;
    private Dingadapter.OnItemClickListener mOnItemClickListener = null;
    public Evaluateadaoters(Context context, List<Dingdanbean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
         MyViewholder myViewholder=new MyViewholder(LayoutInflater.from(context).inflate(R.layout.order_pingj,parent,false));

        return myViewholder;
    }

    @Override
    public void onBindViewHolder(final MyViewholder holder, final int position) {
          holder.setIsRecyclable(false);
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,layoutPosition);
                }
            });
        }
           /*
          加载图片
         */
        RequestOptions options = new RequestOptions()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getSingle_pic())
                .apply(options)
                .into(  holder.order_tupian);
        holder.order_count.setText(list.get(position).getMain_title());
        holder.order_price.setText(list.get(position).getPro_price());

        holder.order_renew.setFocusable(false);
        if ("5".equals(list.get(position).getOrder_status())) {
            //续费
            holder.order_renew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RenewActivity.class);
                    intent.putExtra("order_id", list.get(position).getOrder_id());
                    intent.putExtra("xiu_img", list.get(position).getPro_pic().get(0).getUrl());
                    intent.putExtra("price", list.get(position).getPro_price());
                    intent.putExtra("title", list.get(position).getMain_title());
                    intent.putExtra("order_num", list.get(position).getOrder_num());
                    context.startActivity(intent);
                }
            });


            //激活
            holder.order_activate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtils.showShort(context, "激活设备");
                    Intent intent = new Intent(context, MyBlueActivity.class);
                //    intent.putExtra("mac", list.get(position).getMac());
                    intent.putExtra("order_id", list.get(position).getOrder_id());
                    context.startActivity(intent);
                }
            });
            //退换

            holder.order_tuihuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReturnActivity.class);
                    intent.putExtra("order_id", list.get(position).getOrder_id());
                    context.startActivity(intent);
                }
            });
            //评价
            holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("0".equals(list.get(position).getIs_comment())) {
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    } else {
                        holder.order_evaluation.setBackgroundResource(R.drawable.shape_line);
                        holder.order_evaluation.setTextColor(Color.BLACK);
                    }

                }
            });
        }
        else if ("6".equals(list.get(position).getOrder_status())) {

            //激活
            holder.order_activate.setTextColor(context.getResources().getColor(R.color.font_black_6));
            holder.order_activate.setBackground(context.getResources().getDrawable(R.drawable.shape_line));
            //续费
            holder.order_renew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RenewActivity.class);
                    intent.putExtra("order_id", list.get(position).getOrder_id());
                    intent.putExtra("xiu_img", list.get(position).getPro_pic().get(0).getUrl());
                    intent.putExtra("price", list.get(position).getPro_price());
                    intent.putExtra("title", list.get(position).getMain_title());
                    intent.putExtra("order_num", list.get(position).getOrder_num());
                    context.startActivity(intent);
                }
            });
            //评价
            holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("0".equals(list.get(position).getIs_comment())) {
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    } else {
                        holder.order_evaluation.setBackgroundResource(R.drawable.shape_line);
                        holder.order_evaluation.setTextColor(Color.BLACK);
                    }

                }
            });
        }
        else if ("7".equals(list.get(position).getOrder_status()) || "8".equals(list.get(position).getOrder_status()
        ) || "9".equals(list.get(position).getOrder_status()) || "10".equals(list.get(position).getOrder_status())
                || "11".equals(list.get(position).getOrder_status())) {
            //续费
            holder.order_renew.setTextColor(context.getResources().getColor(R.color.font_black_6));
            //报修
            holder.order_repairs.setTextColor(context.getResources().getColor(R.color.font_black_6));
            //退换
            holder.order_tuihuan.setTextColor(context.getResources().getColor(R.color.font_black_6));
            //激活
            holder.order_activate.setTextColor(context.getResources().getColor(R.color.font_black_6));
            holder.order_activate.setBackground(context.getResources().getDrawable(R.drawable.shape_line));

            //评价
            holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("0".equals(list.get(position).getIs_comment())) {
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    } else {
                        holder.order_evaluation.setBackgroundResource(R.drawable.shape_line);
                        holder.order_evaluation.setTextColor(Color.BLACK);
                    }

                }
            });
        }
        else {
            //评价
            holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("0".equals(list.get(position).getIs_comment())) {
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    } else {
                        holder.order_evaluation.setBackgroundResource(R.drawable.shape_line);
                        holder.order_evaluation.setTextColor(Color.BLACK);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        public ImageView order_tupian;
        public TextView order_count;
        public TextView order_price;
        public TextView order_activate;
        public TextView order_renew;
        public TextView order_tuihuan;
        public TextView order_repairs;
        public TextView order_evaluation;
        public LinearLayout evaluate;
        public TextView order_pause;
        public TextView order_zhifi;
        public LinearLayout pay;
        public TextView recying_btn;
        public RelativeLayout receving;
        public MyViewholder(View convertView) {
            super(convertView);
           order_tupian = (ImageView)  convertView .findViewById(R.id.order_tupian);
           order_count = (TextView)  convertView .findViewById(R.id.order_count);
            order_price = (TextView)  convertView .findViewById(R.id.order_price);
           order_activate = (TextView)  convertView .findViewById(R.id.order_activate);
            order_renew = (TextView)  convertView .findViewById(R.id.order_renew);
            order_tuihuan = (TextView) convertView .findViewById(R.id.order_tuihuan);
           order_repairs = (TextView)  convertView .findViewById(R.id.order_repairs);
           order_evaluation = (TextView)  convertView .findViewById(R.id.order_evaluation);
            evaluate = (LinearLayout)  convertView .findViewById(R.id.evaluate);
           order_pause = (TextView)  convertView .findViewById(R.id.order_pause);
           order_zhifi = (TextView)  convertView .findViewById(R.id.order_zhifi);
           pay = (LinearLayout)  convertView .findViewById(R.id.pay);
          recying_btn = (TextView)  convertView .findViewById(R.id.recying_btn);
          receving = (RelativeLayout)  convertView .findViewById(R.id.receving);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(Dingadapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
