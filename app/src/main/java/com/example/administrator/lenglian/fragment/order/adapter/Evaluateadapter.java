package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.ReturnActivity;
import com.example.administrator.lenglian.fragment.mine.adapter.DingdanAdapter;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.order.activity.BaoxiuActivity;
import com.example.administrator.lenglian.fragment.order.activity.RenewActivity;
import com.example.administrator.lenglian.fragment.order.activity.ShopdetailActivity;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Evaluateadapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Dingdanbean.DatasBean> list;

    public Evaluateadapter(Context context, List<Dingdanbean.DatasBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
     ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //填充布局
            convertView = LayoutInflater.from(context).inflate(R.layout.order_pingj, null);
            holder.order_tupian = (ImageView)  convertView .findViewById(R.id.order_tupian);
            holder.order_count = (TextView)  convertView .findViewById(R.id.order_count);
            holder.order_price = (TextView)  convertView .findViewById(R.id.order_price);
            holder.order_activate = (TextView)  convertView .findViewById(R.id.order_activate);
            holder.order_renew = (TextView)  convertView .findViewById(R.id.order_renew);
            holder.order_tuihuan = (TextView) convertView .findViewById(R.id.order_tuihuan);
            holder.order_repairs = (TextView)  convertView .findViewById(R.id.order_repairs);
            holder.order_evaluation = (TextView)  convertView .findViewById(R.id.order_evaluation);
            holder.evaluate = (LinearLayout)  convertView .findViewById(R.id.evaluate);
            holder.order_pause = (TextView)  convertView .findViewById(R.id.order_pause);
            holder.order_zhifi = (TextView)  convertView .findViewById(R.id.order_zhifi);
            holder.pay = (LinearLayout)  convertView .findViewById(R.id.pay);
            holder.recying_btn = (TextView)  convertView .findViewById(R.id.recying_btn);
            holder.receving = (RelativeLayout)  convertView .findViewById(R.id.receving);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*
          加载图片
         */
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getSingle_pic())
                .apply(options)
                .into(  holder.order_tupian);
        holder.order_count.setText(list.get(position).getMain_title());
        holder.order_price.setText(list.get(position).getPro_price());

        holder.order_renew.setFocusable(false);
        //续费
        holder.order_renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RenewActivity.class);
                context.startActivity(intent);
            }
        });
        holder.order_tuihuan.setOnClickListener(this);
        holder.order_repairs.setOnClickListener(this);
      //评价
        final ViewHolder finalHolder = holder;
        holder.order_evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if("0".equals(list.get(position).getIs_comment())){
                      Intent inte=new Intent(context,ShopdetailActivity.class);
                      inte.putExtra("pro_id",list.get(position).getPro_id());
                      inte.putExtra("order_id",list.get(position).getOrder_id());
                      context.startActivity(inte);
                  }
                else {
                      finalHolder.order_evaluation.setBackgroundResource(R.drawable.shape_line);
                      finalHolder.order_evaluation.setTextColor(Color.BLACK);
                  }

            }
        });

        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_tuihuan:
                Intent intent=new Intent(context,ReturnActivity.class);
                context.startActivity(intent);
                break;
            case R.id.order_repairs:
                Intent inten=new Intent(context,BaoxiuActivity.class);
                context.startActivity(inten);
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
        public RelativeLayout receving;

    }
}
