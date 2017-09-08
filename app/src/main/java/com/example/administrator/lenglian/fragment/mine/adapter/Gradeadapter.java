package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.mine.bean.photobean;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Gradeadapter extends BaseAdapter {
    private Context context;
    private List<photobean> list;

    public Gradeadapter(Context context, List<photobean> list) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.grade_item,null);
           holder.image= (ImageView) convertView.findViewById(R.id.image_grade);
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
      }
}
