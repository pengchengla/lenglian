package com.example.administrator.lenglian.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.utils.provice.AddressUtils;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyUtils;

import java.util.Calendar;
import java.util.Date;

public class ZiLiaoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_head;
    private EditText edt_name, edt_nicheng, edt_detail_address;
    private TextView tv_xingbie, tv_birthday, tv_address, tv_tiaoguo, tv_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_liao);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_head.setOnClickListener(this);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_nicheng = (EditText) findViewById(R.id.edt_nicheng);
        tv_xingbie = (TextView) findViewById(R.id.tv_xingbie);
        tv_xingbie.setOnClickListener(this);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_birthday.setOnClickListener(this);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_address.setOnClickListener(this);
        tv_tiaoguo = (TextView) findViewById(R.id.tv_tiaoguo);
        tv_tiaoguo.setOnClickListener(this);
        tv_yes = (TextView) findViewById(R.id.tv_yes);
        tv_yes.setOnClickListener(this);
        edt_detail_address = (EditText) findViewById(R.id.edt_detail_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xingbie:
                showGenderDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.iv_head:
                break;
            case R.id.tv_birthday:
                showBirthdayDialog();
                break;
            case R.id.tv_address:
                showAddressDialog();
                break;
            case R.id.tv_yes:
                break;
            case R.id.tv_tiaoguo:
                break;
        }
    }

    private void showAddressDialog() {
        new AddressUtils().ShowAddressDialog(this,tv_address);
    }

    private void showBirthdayDialog() {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_birthday.setText(MyUtils.dateToString(date, "MEDIUM"));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
        //注：根据需求来决定是否使用该方法（一般是精确到秒的情况），
        // 此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，
        // 导致选中时间与当前时间不匹配的问题。
    }

    private void showGenderDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_gender)
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
        dialog.getView(R.id.tv_man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_xingbie.setText("男");
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_xingbie.setText("女");
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
