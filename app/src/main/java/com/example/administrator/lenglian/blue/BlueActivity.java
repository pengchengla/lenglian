package com.example.administrator.lenglian.blue;

import android.Manifest;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.fastble.BleManager;
import com.clj.fastble.conn.BleCharacterCallback;
import com.clj.fastble.data.ScanResult;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.utils.BaseDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlueActivity extends AppCompatActivity {
    private BleManager mBleManager;
    private String mac = "FF:FF:50:00:00:91";
    private BluetoothService mBluetoothService;
    private ImageView img_loading;
    private Animation operatingAnim;
    private TextView mTv_content, txt;
    private BaseDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);
//        img_loading = (ImageView) findViewById(R.id.img_loading);
        txt = (TextView) findViewById(R.id.txt);
        operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        operatingAnim.setInterpolator(new LinearInterpolator());
        mBleManager = new BleManager(this);
        boolean supportBle = mBleManager.isSupportBle();
        if (!supportBle) {
            Toast.makeText(this, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            return;
        }
        checkPermissions();
    }

    private void showDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        //设置dialogpadding
        //设置显示位置
        //设置动画
        //设置dialog的宽高
        //设置触摸dialog外围是否关闭
        //设置监听事件
        mDialog = builder.setViewId(R.layout.dialog_blue)
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
        mDialog.show();
        mTv_content = mDialog.getView(R.id.tv_content);
        TextView tv_canel = mDialog.getView(R.id.tv_canel);
        tv_canel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                mDialog.close();
            }
        });
        TextView tv_yes = mDialog.getView(R.id.tv_yes);
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void refreshState(String content) {
        //        mTv_content.setText(content);
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    private void checkPermissions() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, 12);
        }
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode,
                                                 @NonNull String[] permissions,
                                                 @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 12:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            onPermissionGranted(permissions[i]);
                        }
                    }
                }
                break;
        }
    }

    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (mBluetoothService == null) {
                    bindService();
                } else {
                    mBluetoothService.scanAndConnect5(mac);
                }
                break;
        }
    }

    private void bindService() {
        //        showDialog(Gravity.CENTER, R.style.Alpah_aniamtion);
        Intent bindIntent = new Intent(this, BluetoothService.class);
        this.bindService(bindIntent, mFhrSCon, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        this.unbindService(mFhrSCon);
    }

    private ServiceConnection mFhrSCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBluetoothService = ((BluetoothService.BluetoothBinder) service).getService();
            mBluetoothService.setScanCallback(callback);
            mBluetoothService.scanAndConnect5(mac);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBluetoothService = null;
        }
    };

    private BluetoothService.Callback callback = new BluetoothService.Callback() {
        @Override
        public void onStartScan() {
            //            img_loading.startAnimation(operatingAnim);
            refreshState("开始扫描设备");
        }

        @Override
        public void onScanning(ScanResult result) {
            refreshState("扫描设备中...");
        }

        @Override
        public void onScanComplete() {
            //            img_loading.clearAnimation();
            refreshState("扫描设备完成");
        }

        @Override
        public void onConnecting() {
            refreshState("连接设备中...");
        }

        @Override
        public void onConnectFail() {
            //            img_loading.clearAnimation();
            refreshState("连接设备失败");
            Toast.makeText(BlueActivity.this, "连接失败", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onDisConnected() {
            //            img_loading.clearAnimation();
            refreshState("连接断开");
            Toast.makeText(BlueActivity.this, "连接断开", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServicesDiscovered() {
            refreshState("连接设备成功");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(1000);
                    writeData();
                }
            });
        }
    };
    UUID service_uuid = null;
    UUID character_uuid = null;

    private void writeData() {
        refreshData("ffe2");//设置密码
        //        String psw = string2HexString("123");
        //        Toast.makeText(this, "密码" + psw, Toast.LENGTH_SHORT).show();
        //        String hex = "0x0203" + psw;
        //        String hex = "0x02030x01";
        String hex = "0203313233";
        mBluetoothService.write(
                //                characteristic.getService().getUuid().toString(),
                //                characteristic.getUuid().toString(),
                service_uuid.toString(), character_uuid.toString(),
                hex,
                new BleCharacterCallback() {

                    @Override
                    public void onSuccess(final BluetoothGattCharacteristic characteristic) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt.append(String.valueOf(HexUtil.encodeHex(characteristic.getValue())));
                                txt.append("\n");
                                int offset = txt.getLineCount() * txt.getLineHeight();
                                if (offset > txt.getHeight()) {
                                    txt.scrollTo(0, offset - txt.getHeight());
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(final BleException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt.append(exception.toString());
                                txt.append("\n");
                                int offset = txt.getLineCount() * txt.getLineHeight();
                                if (offset > txt.getHeight()) {
                                    txt.scrollTo(0, offset - txt.getHeight());
                                }
                                txt.append(service_uuid.toString() + "\n" + character_uuid.toString());
                            }
                        });
                    }

                    @Override
                    public void onInitiatedResult(boolean result) {

                    }

                });
    }

    private void refreshData(String uuid) {
        BluetoothGatt gatt = mBluetoothService.getGatt();
        List<BluetoothGattService> services = gatt.getServices();
        BluetoothGattService service = null;
        for (int i = 0; i < services.size(); i++) {
            service_uuid = services.get(i).getUuid();
            if (service_uuid.toString().contains("ffe0")) {
                service = services.get(i);
                break;
            }
        }
        BluetoothGattCharacteristic characteristic = null;
        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
        for (int i = 0; i < characteristics.size(); i++) {
            character_uuid = characteristics.get(i).getUuid();
            if (character_uuid.toString().contains(uuid)) {
                characteristic = characteristics.get(i);
                break;
            }
        }
    }

    /**
     * @param strPart 字符串
     * @return 16进制字符串
     * @throws
     * @Title:string2HexString
     * @Description:字符串转16进制字符串
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothService != null)
            unbindService();
    }
}
