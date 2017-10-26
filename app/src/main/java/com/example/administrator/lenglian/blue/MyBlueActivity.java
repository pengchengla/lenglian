package com.example.administrator.lenglian.blue;

import android.Manifest;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.vise.baseble.ViseBluetooth;
import com.vise.baseble.callback.IConnectCallback;
import com.vise.baseble.callback.data.ICharacteristicCallback;
import com.vise.baseble.exception.BleException;
import com.vise.baseble.utils.BleUtil;
import com.vise.baseble.utils.HexUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class MyBlueActivity extends AppCompatActivity {
    private String mac = "FF:FF:50:00:00:91";
    private String serviceUUID;
    private String characteristicUUID;
    //设备特征值集合
    private List<List<BluetoothGattCharacteristic>> mGattCharacteristics = new ArrayList<>();
    private static final String LIST_NAME = "NAME";
    private static final String LIST_UUID = "UUID";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 100;
    private TextView tv;
    private BluetoothGattCharacteristic mCharacteristic;
    private BluetoothGattCharacteristic notifyCharacteristic;
    //发送队列，提供一种简单的处理方式，实际项目场景需要根据需求优化
    private Queue<byte[]> dataInfoQueue = new LinkedList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blue);
        tv = (TextView) findViewById(R.id.tv);
        //蓝牙信息初始化，全局唯一，必须在应用初始化时调用
        ViseBluetooth.getInstance().init(getApplicationContext());
        checkBluetoothPermission();
    }

    private void notifyData() {
        getNotifyGattCharacteristic("0000ffe5-0000-1000-8000-00805f9b34fb");
        ViseBluetooth.getInstance().enableCharacteristicNotification(notifyCharacteristic, mICharacteristicCallback, false);
    }

    private ICharacteristicCallback mICharacteristicCallback = new ICharacteristicCallback() {
        @Override
        public void onSuccess(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic == null || bluetoothGattCharacteristic.getValue() == null) {
                return;
            }
            byte[] value = bluetoothGattCharacteristic.getValue();
            Toast.makeText(MyBlueActivity.this, "notify成功" + HexUtil.encodeHexStr(value), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(BleException exception) {
            if (exception == null) {
                return;
            }
            Toast.makeText(MyBlueActivity.this, "notify失败" + exception.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 数据分包
     *
     * @param data
     * @return
     */
    private Queue<byte[]> splitPacketFor20Byte(byte[] data) {
        Queue<byte[]> dataInfoQueue = new LinkedList<>();
        if (data != null) {
            int index = 0;
            do {
                byte[] surplusData = new byte[data.length - index];
                byte[] currentData;
                System.arraycopy(data, index, surplusData, 0, data.length - index);
                if (surplusData.length <= 20) {
                    currentData = new byte[surplusData.length];
                    System.arraycopy(surplusData, 0, currentData, 0, surplusData.length);
                    index += surplusData.length;
                } else {
                    currentData = new byte[20];
                    System.arraycopy(data, index, currentData, 0, 20);
                    index += 20;
                }
                dataInfoQueue.offer(currentData);
            } while (index < data.length);
        }
        return dataInfoQueue;
    }

    /**
     * 检查蓝牙权限
     */
    private void checkBluetoothPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //校验是否已具有模糊定位权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
            } else {
                //具有权限
                scanBluetooth();
            }
        } else {
            //系统不高于6.0直接执行
            scanBluetooth();
        }
    }

    private void scanBluetooth() {
        if (BleUtil.isBleEnable(this)) {
            byte[] bytes = HexUtil.decodeHex("04041710261640313234".toCharArray());//设置工作时间
            //                        byte[] bytes = HexUtil.decodeHex("fefe1710251510313233".toCharArray());//同步时间
            //            byte[] bytes = HexUtil.decodeHex("0203313234".toCharArray());//设置密码
            send(bytes);
        } else {
            BleUtil.enableBluetooth(this, 1);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            connectAndWrite();
        }
    };

    private void send(byte[] data) {
        if (dataInfoQueue != null) {
            dataInfoQueue.clear();
            dataInfoQueue = splitPacketFor20Byte(data);
            if (dataInfoQueue.peek() != null) {
                handler.postDelayed(runnable, 100);
            }
        }
    }

    private void connectAndWrite() {
         /*
        * 连接指定Mac地址的设备，该方式使用前不需要进行扫描，该方式直接将扫描和连接放到一起，
        * 在扫描到指定设备后自动进行连接，使用方式如下：
        * */

        ViseBluetooth.getInstance().connectByMac(mac, true, new IConnectCallback() {
            @Override
            public void onConnectSuccess(BluetoothGatt gatt, int status) {
                Toast.makeText(MyBlueActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                if (dataInfoQueue != null && !dataInfoQueue.isEmpty()) {
                    if (dataInfoQueue.peek() != null) {
                        ViseBluetooth.getInstance().writeCharacteristic(getGattCharacteristic("0000ffe4-0000-1000-8000-00805f9b34fb"),
                                dataInfoQueue.poll(), new ICharacteristicCallback() {
                                    @Override
                                    public void onSuccess(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                                        Toast.makeText(MyBlueActivity.this, "write成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(BleException exception) {
                                        Toast.makeText(MyBlueActivity.this, "write失败" + exception.getDescription(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                        if (dataInfoQueue.peek() != null) {
                            handler.postDelayed(runnable, 100);
                        }
                    }
                }
                SystemClock.sleep(100);
                notifyData();
            }

            @Override
            public void onConnectFailure(BleException exception) {
                Toast.makeText(MyBlueActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDisconnect() {
                Toast.makeText(MyBlueActivity.this, "连接断开", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private BluetoothGattCharacteristic getGattCharacteristic(String characteristic_uuid) {
        List<BluetoothGattService> services = ViseBluetooth.getInstance().getBluetoothGatt().getServices();
        if (services == null)
            return null;
        String uuid;
        BluetoothGattService service = null;
        mCharacteristic = null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < services.size(); i++) {
            uuid = services.get(i).getUuid().toString();
            builder.append(uuid + "\n");
            tv.setText(builder.toString());
            if (uuid.equalsIgnoreCase("0000ffe0-0000-1000-8000-00805f9b34fb")) {
                service = services.get(i);
                final List<BluetoothGattCharacteristic> gattCharacteristics = service.getCharacteristics();
                for (int i1 = 0; i1 < gattCharacteristics.size(); i1++) {
                    uuid = gattCharacteristics.get(i1).getUuid().toString();
                    if (uuid.equalsIgnoreCase(characteristic_uuid)) {
                        mCharacteristic = gattCharacteristics.get(i1);
                        return mCharacteristic;
                    }
                }
            } else {

            }
        }
        return null;
    }

    private void getNotifyGattCharacteristic(String characteristic_uuid) {
        List<BluetoothGattService> services = ViseBluetooth.getInstance().getBluetoothGatt().getServices();
        if (services == null)
            return;
        String uuid;
        BluetoothGattService service = null;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < services.size(); i++) {
            uuid = services.get(i).getUuid().toString();
            builder.append(uuid + "\n");
            tv.setText(builder.toString());
            if (uuid.equalsIgnoreCase("0000ffe0-0000-1000-8000-00805f9b34fb")) {
                service = services.get(i);
                final List<BluetoothGattCharacteristic> gattCharacteristics = service.getCharacteristics();
                for (int i1 = 0; i1 < gattCharacteristics.size(); i1++) {
                    uuid = gattCharacteristics.get(i1).getUuid().toString();
                    if (uuid.equalsIgnoreCase(characteristic_uuid)) {
                        notifyCharacteristic = gattCharacteristics.get(i1);
                        return;
                    }
                }
            } else {

            }
        }
        return;
    }

    /**
     * 打开或关闭蓝牙后的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                byte[] bytes = HexUtil.decodeHex("04041710261640313234".toCharArray());//设置工作时间
                //                                byte[] bytes = HexUtil.decodeHex("fefe1710251510313233".toCharArray());//同步时间
                //                byte[] bytes = HexUtil.decodeHex("0203313234".toCharArray());//设置密码
                send(bytes);
            }
        } else if (resultCode == RESULT_CANCELED) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
