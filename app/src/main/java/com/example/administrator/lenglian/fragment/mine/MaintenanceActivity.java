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
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.base.BaseActivity;
import com.example.administrator.lenglian.bean.EventMessage;
import com.example.administrator.lenglian.fragment.mine.bean.Resultbean;
import com.example.administrator.lenglian.fragment.mine.bean.Upphotobean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.BaseDialog;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyGradeview;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
import com.example.administrator.lenglian.utils.pictureutils.GridViewAdapter;
import com.example.administrator.lenglian.utils.pictureutils.MainConstant;
import com.example.administrator.lenglian.utils.pictureutils.PictureSelectorConfig;
import com.example.administrator.lenglian.utils.pictureutils.ToastUtils;
import com.example.administrator.lenglian.utils.pictureutils.UploadUtil;
import com.example.administrator.lenglian.view.MyRatingBar;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.luck.picture.lib.config.PictureConfig.REQUEST_CAMERA;

/**
 * date : ${Date}
 * author:衣鹏宇(ypu)
 */

public class MaintenanceActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_back;
    private ImageView waranty_img;
    private MyRatingBar ratingbar;
    private EditText warantu_edtext;
    private MyRatingBar furatingbar;
    private TextView tijiao;
    private MyGradeview myGradeview;
    private String pro_id;
    private String repair_id;
    private Context mContext;
    private GridViewAdapter   mGridViewAddImgAdapter;
    private File file;
    private ArrayList<String> mPicList = new ArrayList<>();
    ArrayList<String>list=new ArrayList<>();//上传的图片凭证的数据源
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);



        }
    };
    private String weixiu_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waranty_evaluate);
        mContext=this;
        initView();
        //网络请求
        //初始化grideview
        ingrideview();


    }

    private void network() {
          //网络请求
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(mContext,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("pro_id",pro_id);
        map.put("repair_id",repair_id);
        map.put("comment_type","3");
        map.put("content",warantu_edtext.getText().toString());
        map.put("service_score",furatingbar.getSetbar()+"");
        map.put("repair_score",ratingbar.getSetbar()+"");
         /*
         图片------------------------------
         */
        String photo="";
        for (int i = 0; i <list.size() ; i++) {
            photo+= list.get(i)+",";
        }
        String substring = photo.substring(0, photo.length() - 1);
        map.put("pic_url",substring);
        /*
           维修打分
            int setbar = shop_ratingbar.getSetbar();
        Map<String,String> map=new HashMap<>();
        map.put("user_id", SpUtils.getString(this,"user_id",""));
        map.put("token", MyUtils.getToken());
        map.put("pro_id",   pro_id);             //商品id
        map.put("pro_score", setbar+"");//商品评分
        map.put("express_score",peisongatingbar.getSetbar()+"");
        map.put("service_score",shopfuratingbar.getSetbar()+"");
        map.put("content",warantu_edtext.getText().toString());
        map.put("order_id",oder_id);
        map.put("comment_type","1");

         */
      //  map.put("service_score")
        RetrofitManager.post(MyContants.BASEURL + "s=User/commitComment", map, new BaseObserver1<Resultbean>("") {
            @Override
            public void onSuccess(Resultbean result, String tag) {
                if(result.getCode()==200){
                    ToastUtils.showShort(MaintenanceActivity.this,"提交成功");
                    //发送eventbus刷新数据
                    EventMessage eventMessage = new EventMessage("tijiao");
                    EventBus.getDefault().postSticky(eventMessage);
                    finish();
                }
            }

            @Override
            public void onFailed(int code) {

            }
        });


    }

    private void ingrideview() {

        mGridViewAddImgAdapter = new GridViewAdapter(mContext, mPicList);
        myGradeview.setAdapter(mGridViewAddImgAdapter);
        myGradeview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == MainConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加5张图片
                        //    viewPluImg(position);
                    } else {
                        showphoto(R.style.Alpah_aniamtion, Gravity.CENTER_VERTICAL);
                        //添加凭证图片
                        //   selectPic(MainConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                    }
                } else {
                    //  viewPluImg(position);
                }
            }
        });

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
                selectPic(MainConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                dialog.dismiss();
            }
        });
        dialog.getView(R.id.photo_cream).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机选取
                applyWritePermission();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    /**
     * 打开相册或者照相机选择凭证图片，最多5张
     *
     * @param maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
    }
    public void applyWritePermission() {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }
    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }
    String s;
    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组中

                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (resultCode == RESULT_OK) {

        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片选择结果回调
                refreshAdapter(PictureSelector.obtainMultipleResult(data));
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                break;
            case REQUEST_CAMERA:
                Log.e("TAG", "---------" + FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", file));
                String absolutePath = file.getAbsolutePath();
                mPicList.add(absolutePath); //把图片添加到将要上传的图片数组中
                mGridViewAddImgAdapter.notifyDataSetChanged();
                break;
            // }
        }
        if (requestCode == MainConstant.REQUEST_CODE_MAIN && resultCode == MainConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(MainConstant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }
    }


    public static String convertIconToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        waranty_img = (ImageView) findViewById(R.id.waranty_img);
        ratingbar = (MyRatingBar) findViewById(R.id.ratingbar);
        warantu_edtext = (EditText) findViewById(R.id.warantu_edtext);
        furatingbar = (MyRatingBar) findViewById(R.id.furatingbar);
        tijiao = (TextView) findViewById(R.id.tijiao);
        myGradeview = (MyGradeview) findViewById(R.id.pin_grade);
        ratingbar.setClickable(true);//设置可否点击
        ratingbar.setStar(0f);//设置显示的星星个数
        ratingbar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星
        furatingbar.setClickable(true);
        furatingbar.setStar(0f);//设置显示的星星个数
        furatingbar.setStepSize(MyRatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星
        tv_back.setOnClickListener(this);
        tijiao.setOnClickListener(this);
        warantu_edtext.setOnClickListener(this);
        //得到数据
        Intent intent = getIntent();
        pro_id = intent.getStringExtra("pro_id");
        repair_id = intent.getStringExtra("repair_id");
        weixiu_img = intent.getStringExtra("weixiu_img");
        //加载图片
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.default_square)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(this).load(weixiu_img)
                .apply(options)
                .into(waranty_img);
    }

    private void submit() {
        // validate
        String edtext = warantu_edtext.getText().toString().trim();
        if (TextUtils.isEmpty(edtext)) {
            Toast.makeText(this, "请您输入对我们商品的评价  ", Toast.LENGTH_SHORT).show();
            return;
        }
        int setbar = ratingbar.getSetbar();
        if(setbar==0){
            ToastUtils.showShort(this,"请对我们的维修打分");
           return;
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tijiao:
                Thread thred=new Thread(new Runnable() {

                    private File f;

                    @Override
                    public void run() {
                        Map<String,File> files=new HashMap<>();
                        for (int i = 0; i <mPicList.size() ; i++) {
                            f = new File(mPicList.get(i));
                            files.put("sfile", f);
                            s = UploadUtil.uploadFile(files, MyContants.BASEURL + "s=Upload/upload");
                            Gson gson=new Gson();
                            Upphotobean upphotobean = gson.fromJson(s, Upphotobean.class);
                            if(upphotobean.getCode()==200) {
                                List<Upphotobean.DatasBean> datas = upphotobean.getDatas();

                                list.add(datas.get(0).getUrl());
                                KLog.a("aaa",list.toString());

                            }

                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                KLog.a("Tag",s);
                                //网络请求
                                network();
                            }
                        });

                    }
                });
                thred.start();



                break;
            case R.id. warantu_edtext:
                warantu_edtext.setCursorVisible(true);
                break;
        }
    }
}
