package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.bean.Cardbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Addcardadapter extends BaseAdapter {
    private Context context;
    private List<Cardbean> list;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, null);
            holder.card_image = (ImageView) convertView.findViewById(R.id.card_image);
            holder.card_name = (TextView)  convertView.findViewById(R.id.card_name);
            holder.card_num = (TextView)  convertView.findViewById(R.id.card_num);
            holder.card_remove = (TextView)  convertView.findViewById(R.id.card_remove);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView card_image;
        public TextView card_name;
        public TextView card_num;
        public TextView card_remove;


    }
}
