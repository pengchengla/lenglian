package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.MaintenanceActivity;
import com.example.administrator.lenglian.fragment.mine.ZhijiaActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Baoxiubean;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.socks.library.KLog;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Warrantyadapter extends BaseAdapter {
      private Context context;
     private List<Baoxiubean.DatasBean> list;
    private int tag;

    public Warrantyadapter(Context context,List<Baoxiubean.DatasBean> list) {
        this.context = context;
        this.list=list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            //填充布局
            convertView= LayoutInflater.from(context).inflate(R.layout.warranty_item,null);
            holder.warranty_count= (TextView) convertView.findViewById(R.id.warranty_counts);
            holder.warranty_img= (ImageView) convertView.findViewById(R.id.iv_tupian);
            holder.warranty_money= (TextView) convertView.findViewById(R.id.warranty_price);
            holder.warranty_pinjia=(TextView) convertView.findViewById(R.id.warranty_pinjia);
            convertView.setTag(holder);
            holder.warranty_pinjia.setTag(position);
        }
        else {
            holder= (ViewHolder) convertView.getTag();

        }

         holder.warranty_count.setText(list.get(position).getMain_title());
        if("0".equals(list.get(position).getIs_comment())){
           holder.warranty_pinjia.setText("评价");
        }
        else if("1".equals(list.get(position).getIs_comment())){
            holder.warranty_pinjia.setText("追加评价");
        }
        else if("2".equals(list.get(position).getIs_comment())){
            holder.warranty_pinjia.setText("追加评价");
           // holder.warranty_pinjia.setTextColor(context.getResources().getColor(R.color.font_black_6));
        }

        //加载图片
        RequestOptions options = new RequestOptions()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
       Glide.with(context).load(list.get(position).getPro_pic().get(0).getUrl())
               .apply(options)
               .into(holder.warranty_img);

        holder.warranty_money.setText(list.get(position).getPro_price());

        //评价
        final ViewHolder finalHolder = holder;
        tag = (int) finalHolder.warranty_pinjia.getTag();
        holder.warranty_pinjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //状态判断
                /*
                 0待评价
                 */
                String is_comment = list.get(tag).getIs_comment();
                KLog.a(is_comment);
                if("0".equals(list.get(position).getIs_comment())){

                     //带数据穿送过去
                     Intent it=new Intent(context,MaintenanceActivity.class);
                     it.putExtra("pro_id",list.get(position).getPro_id());//商品id
                     it.putExtra("repair_id",list.get(position).getRepair_id());//订单id
                     it.putExtra("weixiu_img",list.get(position).getPro_pic().get(0).getUrl());
                     context.startActivity(it);
                 }
                 /*
                   追加评价
                  */
                else if("1".equals(list.get(position).getIs_comment())){

                      Intent intent=new Intent(context, ZhijiaActivity.class);
                        intent.putExtra("comment_id",list.get(position).getComment_id());
                     intent.putExtra("repair_id",list.get(position).getRepair_id());
                       context.startActivity(intent);

                 }
                else if("2".equals(list.get(position).getIs_comment())){
                     ToastUtils.showShort(context,"你已追加评论不能在评论");
                 }

            }
        });
        return convertView;
    }
       class ViewHolder{
           ImageView warranty_img;
           TextView warranty_count,warranty_money,warranty_pinjia;

       }
}
