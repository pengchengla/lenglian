package com.example.administrator.lenglian.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.LoginActivity;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.CacheDataManager;
import com.example.administrator.lenglian.utils.SpUtils;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private RelativeLayout set_zhanhao;
    private RelativeLayout set_update;
    private RelativeLayout set_clean;
    private RelativeLayout set_about;
    private Button btn_exit;
    private TextView clean_huan;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 0:
                    Toast.makeText(SetActivity.this,"清理完成",Toast.LENGTH_SHORT).show();
                    try {
                        clean_huan.setText(CacheDataManager.getTotalCacheSize(SetActivity.this));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

            }

        };

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        initView();
        //加载数据
        inindata();
    }
    private void inindata() {
        try {
            clean_huan.setText(CacheDataManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        set_zhanhao = (RelativeLayout) findViewById(R.id.set_zhanhao);
        set_update = (RelativeLayout) findViewById(R.id.set_update);
        set_clean = (RelativeLayout) findViewById(R.id.set_clean);
        set_about = (RelativeLayout) findViewById(R.id.set_about);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        clean_huan = (TextView) findViewById(R.id.clean_huan);
        btn_exit.setOnClickListener(this);
        set_zhanhao.setOnClickListener(this);
        set_clean.setOnClickListener(this);
        set_about.setOnClickListener(this);
        set_update.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                //退出登录
                SpUtils.putString(this,"user_id","");
                Intent inten=new Intent(SetActivity.this, LoginActivity.class);
                startActivity(inten);
                finish();
                break;
            case R.id.set_zhanhao:
                Intent intent=new Intent(this,AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.set_update:
                showDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                break;
            case R.id.set_clean:
                cleanDialog(Gravity.CENTER,R.style.Alpah_aniamtion);

                break;
            case R.id.set_about:
                  Intent intent1=new Intent(this,AboutweActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }
    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_phone)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText("     是否更新？   ");
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        tv_canel.setText("否");
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setText("是");
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //更新页面

              dialog.dismiss();
            }
        });
    }
    private void cleanDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_phone)
                //设置dialogpadding
                .setPaddingdp(10, 0, 10, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView tv_content = dialog.getView(R.id.tv_content);
        tv_content.setText("     是否清除缓存？   ");
        TextView tv_canel = dialog.getView(R.id.tv_canel);
        tv_canel.setText("否");
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                dialog.close();
            }
        });
        TextView tv_yes = dialog.getView(R.id.tv_yes);
        tv_yes.setText("是");
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新页面
                new Thread(new clearCache()).start();
                dialog.dismiss();
            }
        });
    }
     //创建内部类用于清除缓存
     class clearCache implements Runnable {
         @Override
         public void run() {
             try {
                 CacheDataManager.clearAllCache(SetActivity.this);
                 Thread.sleep(3000);
                 if (CacheDataManager.getTotalCacheSize(SetActivity.this).startsWith("0")) {
                     handler.sendEmptyMessage(0);
                 }

             } catch (Exception e) {

                 return;

             }

         }

     }

}
