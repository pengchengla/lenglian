package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.order.bean.Userbean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Useradapter extends BaseAdapter {
      private Context context;

    List<Userbean.DatasBean> datas;

    public Useradapter(Context context, List<Userbean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.activity_user,null);
            holder=new ViewHolder();
            holder.user_text= (TextView) convertView.findViewById(R.id.user_text);
            convertView.setTag(holder);

        }
          else {
            holder= (ViewHolder) convertView.getTag();
        }
         holder.user_text.setText(datas.get(position).getTitle());
        return convertView;
    }

      class ViewHolder{
          TextView user_text;
      }
}
