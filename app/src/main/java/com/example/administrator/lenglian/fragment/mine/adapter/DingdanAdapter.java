package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.order.activity.BaoxiuActivity;
import com.example.administrator.lenglian.fragment.order.activity.RenewActivity;
import com.example.administrator.lenglian.fragment.mine.ReturnActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.order.activity.ShopdetailActivity;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class DingdanAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<Indexbean> list;

    public DingdanAdapter(Context context, List<Indexbean> list) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //填充布局
            convertView = LayoutInflater.from(context).inflate(R.layout.ding_order, null);
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
        holder.order_count.setText(list.get(position).getCount());
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
        holder.order_evaluation.setOnClickListener(this);


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
            case R.id.order_evaluation:
                Intent inte=new Intent(context,ShopdetailActivity.class);
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
        public RelativeLayout receving;

    }
}
