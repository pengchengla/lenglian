package com.example.administrator.lenglian.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.load.data.ExifOrientationStream;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.fragment.order.adapter.Diagpaydapter;
import com.example.administrator.lenglian.fragment.order.bean.Bank;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PayUtil {

    private static Bank bank;

    //支付
    public static void showGenderDialog(int grary, int animationStyle, final Context context) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_pay)
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
//        //支付宝
//        dialog.getView(R.id.zhifu_zhifubao).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
//        //微信
//        dialog.getView(R.id.zhifu_weixin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
        //Listview bank
       ListView viewById = (ListView) dialog.getView(R.id.pay_listview).findViewById(R.id.pay_listview);
         final List<Bank> list=new ArrayList<>();
         Bank bank1=new Bank();
        bank1.setName("支付宝支付");
        list.add(bank1);
        Bank bank2=new Bank();
        bank2.setName("微信支付");
        list.add(bank2);
        for (int i = 0; i <10; i++) {
            bank = new Bank();
            bank.setName("中国银行"+i);
           list.add(bank);

        }
       Diagpaydapter diagpaydapter= new Diagpaydapter(list,context);
        viewById.setAdapter(diagpaydapter);//添加适配器
        diagpaydapter.notifyDataSetChanged();
        viewById.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                  if(list.get(position).getName().equals("支付宝支付")){

                      ToastUtils.showShort(context,list.get(position).getName());
                  }
                else if("微信支付".equals(list.get(position).getName())){
                      ToastUtils.showShort(context,list.get(position).getName());

                  }
                  //银行卡支付
                else {
                      ToastUtils.showShort(context,list.get(position).getName());
                  }
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
}
