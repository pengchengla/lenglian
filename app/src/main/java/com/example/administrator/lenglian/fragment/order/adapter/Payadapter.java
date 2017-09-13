package com.example.administrator.lenglian.fragment.order.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.utils.BaseDialog;

import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Payadapter extends BaseAdapter {
    private Context context;
    private List<Indexbean> list;

    public Payadapter(Context context, List<Indexbean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //填充布局
            convertView = LayoutInflater.from(context).inflate(R.layout.order_pay, null);
            holder.iv_tupian = (ImageView)  convertView.findViewById(R.id.pay_tupian);
            holder.orderlist_count = (TextView)  convertView.findViewById(R.id.paylist_count);
            holder.textView = (TextView)  convertView.findViewById(R.id.pay_price);
            holder.order_pause = (TextView)  convertView.findViewById(R.id.order_pause);
            holder.order_zhifi = (TextView)  convertView.findViewById(R.id.order_zhifi);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.orderlist_count.setText(list.get(position).getCount());
             holder.order_pause.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     //取消
                     list.remove(position);
                     notifyDataSetChanged();
                 }
             });
             holder.order_zhifi.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion);
                 }
             });
        }


        return convertView;
    }
    //支付
    private void showGenderDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.order_diagzhifu)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        //支付宝
        dialog.getView(R.id.zhifu_zhifubao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        //微信
        dialog.getView(R.id.zhifu_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        //银行
        dialog.getView(R.id.zhifu_bank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        //银行2
        dialog.getView(R.id.zhifu_bank2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        dialog.getView(R.id.zhifu_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    class ViewHolder {
        public View rootView;
        public TextView tv_back;
        public ImageView iv_tupian;
        public TextView orderlist_count;
        public TextView textView;
        public TextView order_pause;
        public TextView order_zhifi;

    }
}
