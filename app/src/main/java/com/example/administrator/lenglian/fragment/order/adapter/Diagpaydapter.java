package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.order.bean.Bank;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Diagpaydapter extends BaseAdapter {
     private List<Bank> list;
    private Context context;

    public Diagpaydapter(List<Bank> list, Context context) {
        this.list = list;
        this.context = context;
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
        Myviewholder myviewholder;
        if(convertView==null){
            myviewholder=new Myviewholder();
            //填充数据
            convertView= LayoutInflater.from(context).inflate(R.layout.payitem_detail,null);
            //获取id
           myviewholder.name= (TextView) convertView.findViewById(R.id.zhifu_bank);
            convertView.setTag(myviewholder);

        }
        else {
        myviewholder= (Myviewholder) convertView.getTag();
        }
          //加载数据
        myviewholder.name.setText(list.get(position).getName());
        return convertView;
    }

    class Myviewholder{
        TextView name;
    }
}
