package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.ReturnActivity;
import com.example.administrator.lenglian.fragment.order.activity.BaoxiuActivity;
import com.example.administrator.lenglian.fragment.order.activity.RenewActivity;
import com.example.administrator.lenglian.fragment.order.activity.ShopdetailActivity;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;
import com.example.administrator.lenglian.utils.PayUtil;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DingdanAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Dingdanbean.DatasBean> list;

    public DingdanAdapter(Context context, List<Dingdanbean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //填充布局
            convertView = LayoutInflater.from(context).inflate(R.layout.ding_order, null);
            holder.order_tupian = (ImageView) convertView.findViewById(R.id.order_tupian);
            holder.order_count = (TextView) convertView.findViewById(R.id.order_count);
            holder.order_price = (TextView) convertView.findViewById(R.id.order_price);
            holder.order_activate = (TextView) convertView.findViewById(R.id.order_activate);
            holder.order_renew = (TextView) convertView.findViewById(R.id.order_renew);
            holder.order_tuihuan = (TextView) convertView.findViewById(R.id.order_tuihuan);
            holder.order_repairs = (TextView) convertView.findViewById(R.id.order_repairs);
            holder.order_evaluation = (TextView) convertView.findViewById(R.id.order_evaluation);
            holder.evaluate = (LinearLayout) convertView.findViewById(R.id.evaluate);
            holder.order_pause = (TextView) convertView.findViewById(R.id.order_pause);
            holder.order_zhifi = (TextView) convertView.findViewById(R.id.order_zhifi);
            holder.pay = (LinearLayout) convertView.findViewById(R.id.pay);
            holder.recying_btn = (TextView) convertView.findViewById(R.id.recying_btn);
            holder.receving = (TextView) convertView.findViewById(R.id.receving);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //加载数据
        holder.order_evaluation.setFocusable(false);
        holder.order_zhifi.setFocusable(false);
        holder.order_pause.setFocusable(false);
        holder.order_tuihuan.setFocusable(false);
        holder.order_repairs.setFocusable(false);
        holder.order_renew.setFocusable(false);
        holder.order_count.setText(list.get(position).getMain_title());
        holder.order_price.setText(list.get(position).getPro_price());
        /*
         photo
         */
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getPro_pic().get(0).getUrl())
                .apply(options)
                .into(holder.order_tupian);
        ToastUtils.showShort(context, list.get(position).getOrder_status().toString());
        /*
          状态判断
         */
        if ("5".equals(list.get(position).getOrder_status())) {

            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.VISIBLE);
            holder.receving.setVisibility(View.GONE);

            //激活
            holder.order_activate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showShort(context, "激活设备");
                }
            });
            if ("6".equals(list.get(position).getOrder_status()) || "7".equals(list.get(position).getOrder_status()) || "8".equals(list.get(position).getOrder_status())) {
                //续费
                holder.order_renew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RenewActivity.class);
                        intent.putExtra("order_id", list.get(position).getOrder_id());
                        context.startActivity(intent);
                    }
                });

            } else {
                //退换

                holder.order_tuihuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ReturnActivity.class);
                        intent.putExtra("order_id", list.get(position).getOrder_id());
                        context.startActivity(intent);
                    }
                });
                //续费
                holder.order_renew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RenewActivity.class);
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

            }
        }
        //收货
        else if ("2".equals(list.get(position).getOrder_status()) || "3".equals(list.get(position).getOrder_status()) || "4".equals(list.get(position).getOrder_status())
                || "9".equals(list.get(position).getOrder_status()) || "10".equals(list.get(position).getOrder_status())
                || "7".equals(list.get(position).getOrder_status()) || "8".equals(list.get(position).getOrder_status())) {
            holder.pay.setVisibility(View.GONE);
            holder.evaluate.setVisibility(View.GONE);
            holder.receving.setVisibility(View.VISIBLE);
        } else if ("1".equals(list.get(position).getOrder_status())) {

            holder.evaluate.setVisibility(View.GONE);
            holder.pay.setVisibility(View.VISIBLE);
            holder.receving.setVisibility(View.GONE);
            //支付
            holder.order_zhifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PayUtil.showGenderDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion, context);
                }
            });
            //取消订单

            holder.order_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        /*
          监听--------------------------
         */


        holder.order_evaluation.setOnClickListener(this);


        return convertView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //评价
            case R.id.order_evaluation:
                Intent inte = new Intent(context, ShopdetailActivity.class);
                context.startActivity(inte);
                break;
        }
    }

    class ViewHolder {
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

    }
}
