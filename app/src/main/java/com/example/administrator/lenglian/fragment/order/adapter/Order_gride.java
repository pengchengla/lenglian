package com.example.administrator.lenglian.fragment.order.adapter;

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
import com.example.administrator.lenglian.MyApplication;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.adapter.Gradeadapter;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;
import com.example.administrator.lenglian.fragment.order.bean.Xiangqingbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Order_gride extends BaseAdapter {

    private Context context;
    List<Xiangqingbean.DatasBean.RentAgainBean> list;
    // private List<Evaluatedetailbean.DatasBean.PicBean> list;
    public  Order_gride(Context context,   List<Xiangqingbean.DatasBean.RentAgainBean> list) {
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
        /*
                  加载图片
                   */
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(MyApplication.getApplication()).load(list.get(position).getSingle_pic())
                .apply(options)
                .into(holder.image);
        //加载数据
        holder.order_gradetext.setText(list.get(position).getPro_name());
        return convertView;
    }
    class  ViewHolder{
        ImageView image;
        TextView order_gradetext;
    }
}
