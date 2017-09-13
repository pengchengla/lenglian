package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.utils.BaseDialog;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Deliveryadapter extends BaseAdapter {
    private Context context;
    private List<Indexbean> list;

    public Deliveryadapter(Context context, List<Indexbean> list) {
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
             holder=new ViewHolder();
            //填充布局
            convertView = LayoutInflater.from(context).inflate(R.layout.recying_item, null);
            holder.iv_tupian = (ImageView)  convertView.findViewById(R.id.receving_tupian);
            holder.orderlist_count = (TextView)  convertView.findViewById(R.id.receving_count);
            holder.textView = (TextView)  convertView.findViewById(R.id.receving_price);
            holder.reying_btn= (TextView) convertView.findViewById(R.id.recying_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.orderlist_count.setText(list.get(position).getCount());
             holder.reying_btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     //确认收货
                 }
             });

        }


        return convertView;
    }


    class ViewHolder {
        public TextView tv_back;
        public ImageView iv_tupian;
        public TextView orderlist_count;
        public TextView textView;
        public TextView reying_btn;

    }
}
