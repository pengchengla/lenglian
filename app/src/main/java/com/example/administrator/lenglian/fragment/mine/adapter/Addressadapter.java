package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.AddaddressActivity;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Addressadapter extends BaseAdapter {
    private Context context;
    private List<Indexbean> list;

    public Addressadapter(Context context,List<Indexbean> list) {
        this.context = context;
        this.list=list;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //填充布局
            convertView = LayoutInflater.from(context).inflate(R.layout.address_item, null);
            holder.ad_name= (TextView)  convertView.findViewById(R.id.ad_name);
            holder.ad_phone = (TextView)  convertView.findViewById(R.id.ad_phone);
            holder.ad_adress = (TextView)  convertView.findViewById(R.id.ad_adress);
            holder.ad_imgmo = (ImageView)  convertView.findViewById(R.id.ad_imgmo);
            holder.ad_text = (TextView)    convertView.findViewById(R.id.ad_text);
            holder.ad_bianji = (TextView)  convertView.findViewById(R.id.ad_bianji);
            holder.ad_delete = (TextView) convertView.findViewById(R.id.ad_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ad_name.setText(list.get(position).getCount());
         holder.ad_bianji.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent it=new Intent(context,AddaddressActivity.class);
                 context.startActivity(it);
             }
         });
        holder.ad_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    class ViewHolder {

        public TextView ad_name;
        public TextView ad_phone;
        public TextView ad_adress;
        public ImageView ad_imgmo;
        public TextView ad_text;
        public TextView ad_bianji;
        public TextView ad_delete;


    }
      //接口回调

}
