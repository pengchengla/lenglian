package com.example.administrator.lenglian.fragment.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.mine.AddaddressActivity;
import com.example.administrator.lenglian.fragment.mine.AddressActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Addressbean;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class Addressadapter extends BaseAdapter {
    private Context context;
//    private List<Indexbean> list;
    private List<Addressbean.DatasBean>list;
   boolean aBoolean;
    public Addressadapter(Context context, List<Addressbean.DatasBean>list) {
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
            holder.change_zhuangtai = (RelativeLayout) convertView.findViewById(R.id.change_zhuangtai);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ad_name.setText(list.get(position).getReceive_name());
        holder.ad_phone.setText(list.get(position).getMobile());
        holder.ad_adress.setText(list.get(position).getAddress_detail());
        String is_default = list.get(position).getIs_default();
        if(is_default.equals("1")){
            holder.ad_text.setText("默认地址");
            holder.ad_text.setTextColor(Color.RED);
            holder.ad_imgmo.setImageResource(R.drawable.icon_single_checked);

        }
        else {
            holder.ad_text.setText("设为默认");
            holder.ad_text.setTextColor(Color.BLACK);
            holder.ad_imgmo.setImageResource(R.drawable.icon_single_unchecked);
        }

         holder.ad_bianji.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent it=new Intent(context,AddaddressActivity.class);
                   it.putExtra("boolean",true);
                 /*
                   传递数据----------------------
                  */
           //      it.putExtra("ad_name",list.get(position))


                 context.startActivity(it);
             }
         });
        holder.ad_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteaadres(position);
             list.remove(position);

                notifyDataSetChanged();
            }
        });
        final ViewHolder finalHolder = holder;
        holder.change_zhuangtai.setOnClickListener(new View.OnClickListener() {

            private String express_id;

            @Override
            public void onClick(View v) {
                aBoolean=!aBoolean;
                //网络请求
                Map<String,String> map=new HashMap<String, String>();
                map.put("user_id",SpUtils.getString(context,"user_id",""));
                map.put("token",MyUtils.getToken());
                express_id = list.get(position).getExpress_id();
                map.put("express_id", express_id);
                if(aBoolean){
                    map.put("is_default","1");
                    finalHolder.ad_imgmo.setImageResource(R.drawable.icon_single_checked);
                    finalHolder.ad_text.setText("默认地址");
                    finalHolder.ad_text.setTextColor(Color.RED);
                }
                else {
                    map.put("is_default","2");
                    finalHolder.ad_imgmo.setImageResource(R.drawable.icon_single_unchecked);
                    finalHolder.ad_text.setText("设为默认");
                    finalHolder.ad_text.setTextColor(Color.BLACK);

                }
                 RetrofitManager.get(MyContants.BASEURL + "s=User/editExpress", map, new BaseObserver1<Resultbean>("") {
                     @Override
                     public void onSuccess(Resultbean result, String tag) {
                         if(result.getCode()==200) {
                             ToastUtils.showShort(context, "修改成功");
                             Map<String,String> map=new HashMap<>();
                             map.put("user_id", SpUtils.getString(context,"user_id",""));//传过来的--------------
                             map.put("token", MyUtils.getToken());
                             RetrofitManager.get(MyContants.BASEURL +"s=User/listExpress",map, new BaseObserver1<Addressbean>("") {
                                 @Override
                                 public void onSuccess(Addressbean result, String tag) {
                                     int code = result.getCode();
                                     if (code==200){
                                         ToastUtils.showShort(context,result.getSuccess());
                                        list = result.getDatas();
                                         notifyDataSetChanged();



                                     }
                                 }

                                 @Override
                                 public void onFailed(int code) {
                                     ToastUtils.showShort(context,"网络失败,请检查网络");
                                 }
                             });



                         }
                     }

                     @Override
                     public void onFailed(int code) {

                     }
                 });
            }
        });
        return convertView;
    }

    private void deleteaadres( int position){
        Map<String,String> map=new HashMap<>();
           map.put("user_id", SpUtils.getString(context,"user_id",""));
           map.put("token", MyUtils.getToken());
           map.put("express_id",list.get(position).getExpress_id());
           map.put("is_del","1");

        RetrofitManager.get(MyContants.BASEURL + "s=User/editExpress", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
              if(result.getCode()==200)  {
                  ToastUtils.showShort(context,"删除成功");
              }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }
    class ViewHolder {

        public TextView ad_name;
        public TextView ad_phone;
        public TextView ad_adress;
        public ImageView ad_imgmo;
        public TextView ad_text;
        public TextView ad_bianji;
        public TextView ad_delete;
        private RelativeLayout change_zhuangtai;


    }
      //接口回调

}
