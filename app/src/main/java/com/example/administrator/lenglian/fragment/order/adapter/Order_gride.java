package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Order_gride extends BaseAdapter {

    private Context context;
    private List<photobean> list;
    // private List<Evaluatedetailbean.DatasBean.PicBean> list;
    public  Order_gride(Context context, List<photobean> list) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.order_gradeitem,null);
            holder.image= (ImageView) convertView.findViewById(R.id.gride_imgage);
            holder.order_gradetext= (TextView) convertView.findViewById(R.id.gride_text);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        //  Toast.makeText(context,list.get(position).getPhoto()+"",Toast.LENGTH_SHORT).show();
        holder.image.setImageResource(list.get(position).getPhoto());
        return convertView;
    }
    class  ViewHolder{
        ImageView image;
        TextView order_gradetext;
    }
}
