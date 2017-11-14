package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.order.bean.Walletbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Walletadapter extends BaseAdapter {
    private Context context;
    private List<Walletbean.DatasBean> list;

    public Walletadapter(Context context, List<Walletbean.DatasBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.avtivity_wallay_item, null);
            holder.wallet_img= (ImageView) convertView.findViewById(R.id.wallet_img);
            holder.wallet_text = (TextView) convertView .findViewById(R.id.wallet_text);
            holder.wallet_num = (TextView) convertView .findViewById(R.id.wallet_num);
            holder.wallet_time = (TextView) convertView .findViewById(R.id.wallet_time);
            holder.wallet_money = (TextView) convertView .findViewById(R.id.wallet_money);
            holder.wallet_yajin = (TextView) convertView .findViewById(R.id.wallet_yajin);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.wallet_text.setText(list.get(position).getPro_name());
        holder.wallet_num.setText("订单号："+list.get(position).getOrder_num()+"");
        holder.wallet_time.setText("起始时间："+list.get(position).getStart_time()+"至"+list.get(position).getEnd_time());
        holder.wallet_money.setText("余额："+"¥ "+list.get(position).getOrder_price()+"");
        holder.wallet_yajin.setText("我的押金:"+"¥ "+list.get(position).getPro_deposit()+"");
        //加载图片
        RequestOptions options = new RequestOptions()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getSingle_pic())
                .apply(options)
                .into(holder.wallet_img);
        return convertView;
    }

    class ViewHolder {
        public ImageView wallet_img;
        public TextView wallet_text;
        public TextView wallet_num;
        public TextView wallet_time;
        public TextView wallet_money;
        public TextView wallet_yajin;
    }
}
