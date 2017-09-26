package com.example.administrator.lenglian.fragment.mine;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.ViewUtils;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.activity.MainActivity;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.fragment.good.QueRenOrderActivity;
import com.example.administrator.lenglian.fragment.mine.bean.Indexbean;
import com.example.administrator.lenglian.fragment.mine.bean.Personbean;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.mine.bean.Upphotobean;
import com.example.administrator.lenglian.fragment.mine.util.NiftyDialogBuilder;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SoftKeyboardTool;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.PhotoUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.utils.pictureutils.UploadUtil;
import com.example.administrator.lenglian.view.CircleImageView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.socks.library.KLog;


import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class PersoninforActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_back;
    private CircleImageView person_pho;
    private TextView person_name;
    private LinearLayout person_ming;
    private TextView person_nick;
    private LinearLayout person_nickname;
    private TextView textView3;
    private TextView person_exselect;
    private LinearLayout person_sex;
    private TextView person_data;
    private LinearLayout person_birth;
    private EditText ed;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private Handler handler=new Handler();
    private Upphotobean upphotobean;
    private GoogleApiClient client;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_personal);
        initView();
        inindata();//下拉个人信息
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }
//修改地址
    private void ininaddress() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("sex",   person_data.getText().toString());
        String token = MyUtils.getToken();
        System.out.print(token);
        KLog.d(token + "-----------------------");
        RetrofitManager.post(MyContants.BASEURL + "s=User/editProfile", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(PersoninforActivity.this,"修改成功");
                }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(PersoninforActivity.this,"修改失败");
            }
        });
    }

    //修改性别
    private void ininsex() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        if( person_exselect.getText().toString().equals("男")){
            map.put("sex",   "1");
        }
        else {
            map.put("sex",   "2");
        }

        String token = MyUtils.getToken();
        System.out.print(token);
        KLog.d(token + "-----------------------");
        RetrofitManager.post(MyContants.BASEURL + "s=User/editProfile", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(PersoninforActivity.this,"修改成功");
                }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(PersoninforActivity.this,"修改失败");
            }
        });

    }
    //修改昵称
    private void ininnick() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("nick_name",  person_nick.getText().toString());
        String token = MyUtils.getToken();
        System.out.print(token);
        SpUtils.putString(this,"nick_name",person_nick.getText().toString());

        KLog.d(token + "-----------------------");
        RetrofitManager.post(MyContants.BASEURL +"s=User/editProfile", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(PersoninforActivity.this,"修改成功");
                }

            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(PersoninforActivity.this,"修改失败");
            }
        });

    }

    //修改名字
    private void ininamend() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("nick_name",  person_name.getText().toString());
        String token = MyUtils.getToken();
        System.out.print(token);
        KLog.d(token + "-----------------------");
        RetrofitManager.post(MyContants.BASEURL + "s=User/editProfile", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(PersoninforActivity.this,"修改成功");
                }
            }

            @Override
            public void onFailed(int code) {
                ToastUtils.showShort(PersoninforActivity.this,"修改失败");
            }
        });


    }

    private void inindata() {
          //加载网络请求
        Map<String,String> map=new HashMap<>();
        map.put("user_id",SpUtils.getString(this,"user_id",SpUtils.getString(this,"user_id","")));
        map.put("token",MyUtils.getToken());
        String token = MyUtils.getToken();
        System.out.print(token);
        RetrofitManager.get(MyContants.BASEURL +"s=User/viewProfile", map, new BaseObserver1<Personbean>("") {
            @Override
            public void onSuccess(Personbean result, String tag) {
                if(result.getCode()==200) {
                    ToastUtils.showShort(PersoninforActivity.this, "修改成功");

                    ToastUtils.show(PersoninforActivity.this, result.getCode() + "成功", 1);
                    List<Personbean.DatasBean> datas = result.getDatas();
                    person_name.setText(datas.get(0).getUser_name());
                    person_nick.setText(datas.get(0).getNick_name());
                    if (datas.get(0).getSex().equals("1")) {
                        person_exselect.setText("男");
                    } else {
                        person_exselect.setText("女");
                    }

                    person_data.setText(datas.get(0).getBirth());
                    //修改头像
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .error(R.drawable.default_square)
                            .priority(Priority.NORMAL)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                    Glide.with(PersoninforActivity.this).load(datas.get(0).getHead())
                            .apply(options)
                            .into(person_pho);
                    //sp存头像
                    SpUtils.putString(PersoninforActivity.this,"photo",datas.get(0).getHead());
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        person_pho = (CircleImageView) findViewById(R.id.person_pho);
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
                showphoto(R.style.Alpah_aniamtion,Gravity.CENTER_VERTICAL);


                break;
            case R.id. person_ming:

                showCarDialog( R.style.Alpah_aniamtion,1);

                break;
            case R.id. person_nickname:
                showCarDialog( R.style.Alpah_aniamtion,2);

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
                //修改性别
                ininsex();
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.tv_woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person_exselect.setText("女");
                //修改性别
                ininsex();
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
                ininaddress();
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
        //修改地址

    }
      //头像选取
    private void showphoto(int animationStyle,int gr){
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.photo_dialg)
                //设置dialogpadding
                .setPaddingdp(40, 40, 40, 40)
                //设置显示位置
                .setGravity(gr)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.getView(R.id.photo_xiangce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册选取
                autoObtainStoragePermission();
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.photo_cream).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机选取
                autoObtainCameraPermission();
                dialog.dismiss();
            }


        });
        dialog.show();
    }
     //调用相机
    private void autoObtainCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ToastUtils.showShort(this, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                imageUri = Uri.fromFile(fileUri);
                if (Build.VERSION.SDK_INT >= 23)
                    imageUri = FileProvider.getUriForFile(PersoninforActivity.this, "com.xykj.customview.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.showShort(this, "设备没有SD卡！");
            }
        }
    }
    private void showCarDialog( int animationStyle,final int type) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.diag)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(580)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)

                //设置监听事件
                .builder();
        ed = (EditText) dialog.getView(R.id.person_nicheng).findViewById(R.id.person_nicheng);
        SoftKeyboardTool.showSoftKeyboard(ed);
        dialog.getView(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){
                 person_name.setText(ed.getText().toString());
                    ininamend();}
                else  if(type==2){
                    person_nick.setText(ed.getText().toString());
                    //修改昵称
                    ininnick();
                }
                SoftKeyboardTool.closeKeyboard(ed);
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardTool.closeKeyboard(ed);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE: {//调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= 23)
                            imageUri = FileProvider.getUriForFile(PersoninforActivity.this, "com.zz.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                        PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
                    } else {
                        ToastUtils.showShort(this, "设备没有SD卡！");
                    }
                } else {

                    ToastUtils.showShort(this, "请允许打开相机！！");
                }
                break;


            }
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
                } else {

                    ToastUtils.showShort(this, "请允许打操作SDCard！！");
                }
                break;
        }
    }

    private static final int output_X = 480;
    private static final int output_Y = 480;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    KLog.a(cropImageUri);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);

                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        KLog.a(cropImageUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));

                        if (Build.VERSION.SDK_INT >=23)
                            newUri = FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        ToastUtils.showShort(this, "设备没有SD卡！");
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    KLog.a(cropImageUri);
                     //将URl上传到服务器
                     photowork();
                    ToastUtils.showShort(PersoninforActivity.this,cropImageUri.toString());
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
            }
        }
    }

    private void photowork() {
        final Thread thred=new Thread(new Runnable() {


            private String s;

            @Override
            public void run() {
                Map<String,File> files=new HashMap<>();
                files.put("sfile",fileCropUri);
                s = UploadUtil.uploadFile(files, MyContants.BASEURL + "s=Upload/upload");
                Gson gson=new Gson();
                upphotobean = gson.fromJson(s, Upphotobean.class);
                List<Upphotobean.DatasBean> datas = upphotobean.getDatas();
                Map<String,String> map=new HashMap<>();
                map.put("user_id",SpUtils.getString(PersoninforActivity.this,"user_id",""));
                map.put("token",MyUtils.getToken());
                map.put("head",datas.get(0).getUrl());
              RetrofitManager.post(MyContants.BASEURL + "s=User/editProfile", map, new BaseObserver1<Resultbean>("") {
                  @Override
                  public void onSuccess(Resultbean result, String tag) {
                      if(result.getCode()==200){
                          ToastUtils.showShort(PersoninforActivity.this, "成功");
                      }

                  }

                  @Override
                  public void onFailed(int code) {

                  }
              });

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        KLog.a("Tag", s);
                        ToastUtils.showShort(PersoninforActivity.this, s);
                    }
                });

            }
        });
        thred.start();


    }

    private void showImages(Bitmap bitmap) {
      //设置图片到页面
        person_pho.setImageBitmap(bitmap);
    }
    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }

    }
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
