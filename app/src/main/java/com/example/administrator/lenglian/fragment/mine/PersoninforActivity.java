package com.example.administrator.lenglian.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyUtils;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Calendar;
import java.util.Date;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PersoninforActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private ImageView person_pho;
    private TextView person_name;
    private LinearLayout person_ming;
    private TextView person_nick;
    private LinearLayout person_nickname;
    private TextView textView3;
    private TextView person_exselect;
    private LinearLayout person_sex;
    private TextView person_data;
    private LinearLayout person_birth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_personal);
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        person_pho = (ImageView) findViewById(R.id.person_pho);
        person_name = (TextView) findViewById(R.id.person_name);
        person_ming = (LinearLayout) findViewById(R.id.person_ming);
        person_nick = (TextView) findViewById(R.id.person_nick);
        person_nickname = (LinearLayout) findViewById(R.id.person_nickname);
        textView3 = (TextView) findViewById(R.id.textView3);
        person_exselect = (TextView) findViewById(R.id.person_exselect);
        person_sex = (LinearLayout) findViewById(R.id.person_sex);
        person_data = (TextView) findViewById(R.id.person_data);
        person_birth = (LinearLayout) findViewById(R.id.person_birth);
        //设置监听
        person_pho.setOnClickListener(this);
        person_ming.setOnClickListener(this);
        person_nickname.setOnClickListener(this);
        person_sex.setOnClickListener(this);
        person_birth.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.person_pho:
                showphoto();

                break;
            case R.id. person_ming:
                break;
            case R.id. person_nickname:
                break;
            case R.id.person_birth:
                showBirthdayDialog();
                break;
            case R.id.person_sex:
                showGenderDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
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
                person_exselect.setText("男");
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person_exselect.setText("女");
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
    //显示时间
    private void showBirthdayDialog() {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                person_data .setText(MyUtils.dateToString(date, "MEDIUM"));
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
      //头像选取
    private void showphoto(){
        NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("上传头像")
                .withMessage("选择你上传的方式")
                .withButton1Text("拍照")
                .withButton2Text("相册选取")
                .withEffect(Effectstype.SlideBottom)
                .withDuration(700)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn2", Toast.LENGTH_SHORT).show();
                    }
                })

                .show();
    }
}
