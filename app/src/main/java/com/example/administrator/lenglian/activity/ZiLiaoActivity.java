package com.example.administrator.lenglian.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.fragment.mine.bean.Upphotobean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.PhotoUtils;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.utils.pictureutils.UploadUtil;
import com.example.administrator.lenglian.utils.provice.AddressUtils;
import com.google.gson.Gson;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.administrator.lenglian.fragment.mine.PersoninforActivity.hasSdcard;

public class ZiLiaoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_head;
    private EditText edt_name, edt_nicheng, edt_detail_address;
    private TextView tv_xingbie, tv_birthday, tv_address, tv_tiaoguo, tv_yes;
    private String mUserid;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private Handler handler = new Handler();
    private Upphotobean upphotobean;
    private String imgUrl;

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
        mUserid = getIntent().getStringExtra("userid");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("注册完善资料界面");
        //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("注册完善资料界面");
        super.onPause();
        // （仅有Activity的应用中SDK自动调用，不需要单独写）
        // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_xingbie:
                showGenderDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
                break;
            case R.id.iv_head:
                showphoto(R.style.Alpah_aniamtion, Gravity.CENTER_VERTICAL);
                break;
            case R.id.tv_birthday:
                showBirthdayDialog();
                break;
            case R.id.tv_address:
                showAddressDialog();
                break;
            case R.id.tv_yes:
                startActivity(new Intent(this, MainActivity.class));
                goMain();
                break;
            case R.id.tv_tiaoguo:
                SpUtils.putString(ZiLiaoActivity.this, "user_id", mUserid);
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    //头像选取
    private void showphoto(int animationStyle, int gr) {
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

    private void autoObtainStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
        }
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
                if (Build.VERSION.SDK_INT >= 24)
                    imageUri = FileProvider.getUriForFile(ZiLiaoActivity.this, "com.xykj.customview.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
                PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
            } else {
                ToastUtils.showShort(this, "设备没有SD卡！");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE: {//调用系统相机申请拍照权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasSdcard()) {
                        imageUri = Uri.fromFile(fileUri);
                        if (Build.VERSION.SDK_INT >= 24)
                            imageUri = FileProvider.getUriForFile(ZiLiaoActivity.this,
                                    "com.xykj.customview.fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
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

                        if (Build.VERSION.SDK_INT >= 24)
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
                    ToastUtils.showShort(ZiLiaoActivity.this, cropImageUri.toString());
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
            }
        }
    }

    private void showImages(Bitmap bitmap) {
        //设置图片到页面
        iv_head.setImageBitmap(bitmap);
    }

    private void photowork() {
        final Thread thred = new Thread(new Runnable() {
            private String s;

            @Override
            public void run() {
                Map<String, File> files = new HashMap<>();
                files.put("sfile", fileCropUri);
                s = UploadUtil.uploadFile(files, MyContants.BASEURL + "s=Upload/upload");
                Gson gson = new Gson();
                upphotobean = gson.fromJson(s, Upphotobean.class);
                List<Upphotobean.DatasBean> datas = upphotobean.getDatas();
                imgUrl = datas.get(0).getUrl();
                goMain();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        KLog.a("Tag", s);
                        ToastUtils.showShort(ZiLiaoActivity.this, s);
                    }
                });

            }
        });
        thred.start();
    }


    private void goMain() {
        if (TextUtils.isEmpty(edt_nicheng.getText().toString())) {
            Toast.makeText(this, "请填写昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tv_xingbie.getText().toString())) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edt_nicheng.getText().toString())) {
            Toast.makeText(this, "请选择出生日期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tv_address.getText().toString())) {
            Toast.makeText(this, "请选择区域地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edt_detail_address.getText().toString())) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("user_id", mUserid);
        arrayMap.put("token", MyUtils.getToken());
        arrayMap.put("nick_name", edt_nicheng.getText().toString());
        arrayMap.put("sex", tv_xingbie.getText().toString().equals("男") ? "1" : "2");
        arrayMap.put("birth", tv_birthday.getText().toString());
        arrayMap.put("user_address", tv_address.getText().toString());
        arrayMap.put("head", imgUrl);
        arrayMap.put("address_detail", edt_detail_address.getText().toString());
        RetrofitManager.get(MyContants.BASEURL + "s=User/editProfile", arrayMap, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {
                if (result.getCode() == 200) {
                    Intent intent1 = new Intent(ZiLiaoActivity.this, MainActivity.class);
                    startActivity(intent1);
                    SpUtils.putString(ZiLiaoActivity.this, "user_id", mUserid);
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(ZiLiaoActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddressDialog() {
        new AddressUtils().ShowAddressDialog(this, tv_address);
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
