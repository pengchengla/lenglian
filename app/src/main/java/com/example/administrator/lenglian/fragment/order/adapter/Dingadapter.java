package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.blue.MyBlueActivity;
import com.example.administrator.lenglian.fragment.mine.ReturnActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.DingdanAdapter;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.order.activity.BaoxiuActivity;
import com.example.administrator.lenglian.fragment.order.activity.RenewActivity;
import com.example.administrator.lenglian.fragment.order.activity.ShopdetailActivity;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.PayUtil;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Dingadapter extends RecyclerView.Adapter<Dingadapter.MyViewholder> {

    private Context context;
    private List<Dingdanbean.DatasBean> list;
    private OnItemClickListener mOnItemClickListener = null;
    public Dingadapter(Context context, List<Dingdanbean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view=LayoutInflater.from(context).inflate(R.layout.ding_order,parent,false);
         MyViewholder myViewholder=new MyViewholder(view);

        return myViewholder;
    }

    @Override
    public void onBindViewHolder(final MyViewholder holder, final int position) {
        holder.setIsRecyclable(false);
        //加载数据
        holder.order_count.setText(list.get(position).getMain_title());
        holder.order_price.setText(list.get(position).getPro_price());
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
         photo
         */
        RequestOptions options = new RequestOptions()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getPro_pic().get(0).getUrl())
                .apply(options)
                .into(holder.order_tupian);

        /*
          状态判断
         */
        if ("5".equals(list.get(position).getOrder_status())) {

            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.VISIBLE);
            holder.liner_receving.setVisibility(View.GONE);

            //激活
            holder.order_activate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtils.showShort(context, "激活设备");
                    Intent intent=new Intent(context, MyBlueActivity.class);
                    intent.putExtra("mac",list.get(position).getMac());
                    intent.putExtra("order_id",list.get(position).getOrder_id());
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
            //报修

            holder.order_repairs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inten = new Intent(context, BaoxiuActivity.class);
                    inten.putExtra("order_id", list.get(position).getOrder_id());
                    context.startActivity(inten);
                }
            });
//            //续费
//            holder.order_renew.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, RenewActivity.class);
//                    intent.putExtra("order_id", list.get(position).getOrder_id());
//                    intent.putExtra("xiu_img", list.get(position).getPro_pic().get(0).getUrl());
//                    intent.putExtra("price", list.get(position).getPro_price());
//                    intent.putExtra("title", list.get(position).getMain_title());
//                    intent.putExtra("order_num",list.get(position).getOrder_num());
//                    context.startActivity(intent);
//                }
//            });
            //评价
//            final DingdanAdapter.ViewHolder finalHolder = holder;
//            final int positions = (int) holder.order_evaluation.getTag();
      //      KLog.d("tag", positions);
            if ("0".equals(list.get(position).getIs_comment())) {

                holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断评价是否评价
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    }


                });

            } else {
                holder.order_evaluation.setTextColor(context.getResources().getColor(R.color.font_black_6));

            }
        } else if ("7".equals(list.get(position).getOrder_status()) || "8".equals(list.get(position).getOrder_status()
        ) || "9".equals(list.get(position).getOrder_status()) || "10".equals(list.get(position).getOrder_status())
                || "11".equals(list.get(position).getOrder_status())) {
            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.VISIBLE);
            holder.liner_receving.setVisibility(View.GONE);
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
          //  final DingdanAdapter.ViewHolder finalHolder = holder;
//            final int positions = (int) holder.order_evaluation.getTag();
//            KLog.d("tag", positions);
            if ("0".equals(list.get(position).getIs_comment())) {

                holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断评价是否评价
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    }


                });
            }
//
//            } else {
//
//                holder.order_evaluation.setTextColor(context.getResources().getColor(R.color.font_black_6));
//
//            }
//            //续费
//            holder.order_renew.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, RenewActivity.class);
//                    intent.putExtra("order_id", list.get(position).getOrder_id());
//                    intent.putExtra("xiu_img", list.get(position).getPro_pic().get(0).getUrl());
//                    intent.putExtra("price", list.get(position).getPro_price());
//                    intent.putExtra("title", list.get(position).getMain_title());
//                    intent.putExtra("order_num",list.get(position).getOrder_num());
//                    context.startActivity(intent);
//                }
//            });

        }

        else if("12".equals(list.get(position).getOrder_status())){
            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.GONE);
            holder.liner_receving.setVisibility(View.GONE);

        }
        else if ("6".equals(list.get(position).getOrder_status())) {
            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.VISIBLE);
            holder.liner_receving.setVisibility(View.GONE);
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
                    intent.putExtra("order_num",list.get(position).getOrder_num());
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
            //报修

            holder.order_repairs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inten = new Intent(context, BaoxiuActivity.class);
                    inten.putExtra("order_id", list.get(position).getOrder_id());
                    context.startActivity(inten);
                }
            });
//            //评价
//            final DingdanAdapter.ViewHolder finalHolder = holder;
//            final int positions = (int) holder.order_evaluation.getTag();
//            KLog.d("tag", positions);
            if ("0".equals(list.get(position).getIs_comment())) {

                holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断评价是否评价
                        Intent inte = new Intent(context, ShopdetailActivity.class);
                        inte.putExtra("pro_id", list.get(position).getPro_id());
                        inte.putExtra("order_id", list.get(position).getOrder_id());
                        inte.putExtra("pin_img", list.get(position).getPro_pic().get(0).getUrl());
                        context.startActivity(inte);
                    }


                });

            } else {

                holder.order_evaluation.setTextColor(context.getResources().getColor(R.color.font_black_6));

            }
        }
        //收货
        else if ("2".equals(list.get(position).getOrder_status()) || "3".equals(list.get(position).getOrder_status()) ||
                "4".equals(list.get(position).getOrder_status())
                ) {
            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.GONE);
            holder.liner_receving.setVisibility(View.VISIBLE);
            //收货按钮
            holder.receving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recycing(position);
                }
            });
        } else if ("1".equals(list.get(position).getOrder_status())) {

            holder.evaluate.setVisibility(View.GONE);
            holder.pay.setVisibility(View.VISIBLE);
            holder.liner_receving.setVisibility(View.GONE);
            //支付
            holder.order_zhifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PayUtil payUtil = new PayUtil(context, list.get(position).getOrder_num(),1);
                    payUtil.showGenderDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion, context);
                }
            });
            //取消订单

            holder.order_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pausevoid(position);

                }
            });
        }


    }

    /*
       收货
     */
    private void recycing(final int position) {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(context, "user_id", ""));
        map.put("token", MyUtils.getToken());
        map.put("order_id", list.get(position).getOrder_id());
        RetrofitManager.get(MyContants.BASEURL + "s=User/commitOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if (result.getCode() == 200) {
                    ToastUtils.showShort(context, "确认收货");
                    notifyDataSetChanged();
                    //发送eventbus刷新数据
                    EventMessage eventMessage = new EventMessage("dingshouhuo");
                    EventBus.getDefault().postSticky(eventMessage);

                }
            }

            @Override
            public void onFailed(int code) {

            }
        });

    }
    /*
      取消订单
     */

    public void pausevoid(final int position) {
          /*
       向服务器传送取消的订单
      */
        Map<String, String> map = new HashMap<>();
        map.put("order_id", list.get(position).getOrder_id());
        map.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=Order/cancelOrder", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if (result.getCode() == 200) {
                    ToastUtils.showShort(context, "订单已取消");
                    // list.remove(position);

                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
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
        public TextView receving;
        public RelativeLayout liner_receving;
        public MyViewholder(View convertView) {
            super(convertView);
            order_tupian = (ImageView) convertView.findViewById(R.id.order_tupian);
            order_count = (TextView) convertView.findViewById(R.id.order_count);
            order_price = (TextView) convertView.findViewById(R.id.order_price);
            order_activate = (TextView) convertView.findViewById(R.id.order_activate);
            order_renew = (TextView) convertView.findViewById(R.id.order_renew);
            order_tuihuan = (TextView) convertView.findViewById(R.id.order_tuihuan);
            order_repairs = (TextView) convertView.findViewById(R.id.order_repairs);
            order_evaluation = (TextView) convertView.findViewById(R.id.order_evaluation);
            evaluate = (LinearLayout) convertView.findViewById(R.id.evaluate);
            order_pause = (TextView) convertView.findViewById(R.id.order_pause);
            order_zhifi = (TextView) convertView.findViewById(R.id.order_zhifi);
            pay = (LinearLayout) convertView.findViewById(R.id.pay);
            recying_btn = (TextView) convertView.findViewById(R.id.recying_btn);
            receving = (TextView) convertView.findViewById(R.id.receving);
            liner_receving= (RelativeLayout) convertView.findViewById(R.id.liner_receving);

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
