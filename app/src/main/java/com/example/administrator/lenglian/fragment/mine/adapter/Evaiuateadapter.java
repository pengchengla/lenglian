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
import com.example.administrator.lenglian.fragment.mine.bean.Evaluatebean;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Evaiuateadapter extends BaseAdapter {
    private Context context;
    //private List<Indexbean> list;
    List<Evaluatebean.DatasBean> list;
    public Evaiuateadapter(Context context,  List<Evaluatebean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
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
       ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            //填充布局
            convertView= LayoutInflater.from(context).inflate(R.layout.alteration_item,null);
            holder.alteration_count= (TextView) convertView.findViewById(R.id.alteration_count);
            holder.alteration_img= (ImageView) convertView.findViewById(R.id.alteration_tupian);
            holder.alteration_money= (TextView) convertView.findViewById(R.id.alteration_price);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        //添加数据
        holder.alteration_count.setText(list.get(position).getMain_title());
        /*
          加载图片
         */
        RequestOptions options=new RequestOptions()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context).load(list.get(position).getPro_pic())
                .apply(options)
                .into( holder.alteration_img);
        holder.alteration_money.setText(list.get(position).getPro_price());

        return convertView;
    }
    class ViewHolder{
        ImageView alteration_img;
        TextView alteration_count,alteration_money;

    }
}
