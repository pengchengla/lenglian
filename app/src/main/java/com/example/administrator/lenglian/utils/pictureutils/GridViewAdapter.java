package com.example.administrator.lenglian.utils.pictureutils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.lenglian.R;

import java.util.List;




public class GridViewAdapter extends android.widget.BaseAdapter {
    
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public GridViewAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView 
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > MainConstant.MAX_SELECT_PIC_NUM) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.grid_item, parent,false);
        ImageView iv = (ImageView) convertView.findViewById(R.id.pic_iv);
        ImageView delete_pho= (ImageView) convertView.findViewById(R.id.delete_pho);
        delete_pho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.size()>0) {
                    mList.remove(position); //从数据源移除删除的图片
                    notifyDataSetChanged();
                }
            }
        });
        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
            Glide.with(mContext).load(picUrl).into(iv);
        } else {
            delete_pho.setVisibility(View.GONE);
            iv.setImageResource(R.drawable.photoshangchuan);//最后一个显示加号图片
        }
        return convertView;
    }

}  
