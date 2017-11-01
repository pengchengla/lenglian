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

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.order.bean.Dingdanbean;
import com.example.administrator.lenglian.fragment.order.bean.Zhifubean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.PayUtil;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Payadapter extends BaseAdapter {
    private Context context;
    private List<Zhifubean.DatasBean> list;

    public Payadapter(Context context,List<Zhifubean.DatasBean> list) {
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
            holder.order_pause = (TextView)  convertView.findViewById(R.id.order_pau);
            holder.order_zhifi = (TextView)  convertView.findViewById(R.id.order_zhi);
            convertView.setTag(holder);
            holder.order_pause.setTag(position);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

           //加载数据

            holder.order_pause.setFocusable(false);
            holder.order_zhifi.setFocusable(false);
         if(list.get(position).getPro_pic().get(0).getUrl().equals(holder.iv_tupian.getTag())){

         }
        else {
           /*

            */
             if (list.get(position).getPro_pic().get(0).getUrl() != null&&list.get(position).getPro_pic().get(0).getUrl().length()>0) {


                 RequestOptions options = new RequestOptions()
                         .error(R.drawable.default_square)
                         .priority(Priority.NORMAL)
                         .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                 Glide.with(context).load(list.get(position).getPro_pic().get(0).getUrl())
                         .apply(options)
                         .into(holder.iv_tupian);
             }
         }
            holder.orderlist_count.setText(list.get(position).getMain_title());
        holder.textView.setText(list.get(position).getPro_price());


        final ViewHolder finalHolder = holder;
        holder.order_pause.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     //取消
                    int i=(int) finalHolder.order_pause.getTag();
                     pausevoid(i);
                     EventMessage eventMessage = new EventMessage("qu");
                     EventBus.getDefault().postSticky(eventMessage);


                 }
             });
             holder.order_zhifi.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     PayUtil payUtil=new PayUtil(context,list.get(position).getOrder_num(),1);

                     payUtil.showGenderDialog(Gravity.BOTTOM,R.style.Bottom_Top_aniamtion,context);

                 }
             });



        return convertView;
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


      public void pausevoid(final int position){
          /*
       向服务器传送取消的订单
      */
          Map<String,String> map=new HashMap<>();
          map.put("order_id",list.get(position).getOrder_id());
          map.put("token", MyUtils.getToken());
          RetrofitManager.get(MyContants.BASEURL + "s=Order/cancelOrder", map, new BaseObserver1<Resultbean>("") {
              @Override
              public void onSuccess(Resultbean result, String tag) {
                  if(result.getCode()==200)  {
                      ToastUtils.showShort(context,"订单已取消");
                      EventMessage eventMessage = new EventMessage("quxiao");
                      EventBus.getDefault().postSticky(eventMessage);
                      notifyDataSetChanged();
                  }
              }

              @Override
              public void onFailed(int code) {

              }
          });

      }

}
