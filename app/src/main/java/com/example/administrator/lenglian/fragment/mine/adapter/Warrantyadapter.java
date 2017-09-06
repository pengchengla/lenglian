package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Warrantyadapter extends BaseAdapter {
      private Context context;

    public Warrantyadapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            //填充布局
            convertView= View.inflate(context,R.layout.warranty_item,null);
            holder.warranty_count= (TextView) convertView.findViewById(R.id.waranty_count);
            holder.warranty_img= (ImageView) convertView.findViewById(R.id.iv_tupian);
            holder.warranty_money= (TextView) convertView.findViewById(R.id.warranty_price);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }

        return convertView;
    }
       class ViewHolder{
           ImageView warranty_img;
           TextView warranty_count,warranty_money;

       }
}
