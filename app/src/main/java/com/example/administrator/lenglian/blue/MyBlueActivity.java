package com.example.administrator.lenglian.blue;

import android.Manifest;
import android.app.Activity;
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
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.lenglian.R;
import com.example.administrator.lenglian.bean.EasyBean;
import com.example.administrator.lenglian.network.BaseObserver1;
import com.example.administrator.lenglian.network.RetrofitManager;
import com.example.administrator.lenglian.utils.MyContants;
import com.example.administrator.lenglian.utils.MyUtils;
import com.example.administrator.lenglian.utils.SpUtils;
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


public class MyBlueActivity extends Activity {
    //让当前这个activity继承自Activity，而不是AppCompatActivity，否则会报主题的错误。
    private String mac = "FF:FF:50:00:00:91";
    private String order_id;
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
    private String mCurrent_time;
    private String mEnd_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blue);
        tv = (TextView) findViewById(R.id.tv);
        mac = getIntent().getStringExtra("mac");
        order_id = getIntent().getStringExtra("order_id");
        //        Toast.makeText(this, " " + mac, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(mac) || TextUtils.isEmpty(order_id)) {
            Toast.makeText(this, "设备Mac地址不存在", Toast.LENGTH_SHORT).show();
            finish();
        }
        initData();
    }

    private void initData() {
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("order_id", order_id);
        arrayMap.put("token", MyUtils.getToken());
        RetrofitManager.get(MyContants.BASEURL + "s=User/getTime", arrayMap, new BaseObserver1<BlueTimeBean>("") {
            @Override
            public void onSuccess(BlueTimeBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    mCurrent_time = result.getDatas().getCurrent_time();
                    mEnd_time = result.getDatas().getEnd_time();
                    checkBluetoothPermission();
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(MyBlueActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
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
            String code = HexUtil.encodeHexStr(value);
            String sub = code.substring(0, 4);
            if (!TextUtils.isEmpty(sub) && sub.equals("3333")) {
                tv.setText("设备激活成功");
                SystemClock.sleep(1000);
                notifyServer();//通知服务器激活成功
            } else {
                tv.setText("设备激活失败");
            }
            //            Toast.makeText(MyBlueActivity.this, "notify成功" + HexUtil.encodeHexStr(value), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(BleException exception) {
            if (exception == null) {
                return;
            }
            tv.setText("设备激活失败");
            //            Toast.makeText(MyBlueActivity.this, "notify失败" + exception.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private void notifyServer() {
        ArrayMap arrayMap = new ArrayMap<String, String>();
        arrayMap.put("user_id", SpUtils.getString(this, "user_id", ""));
        arrayMap.put("order_id", order_id);
        arrayMap.put("token", MyUtils.getToken());
        arrayMap.put("current_time", mCurrent_time);
        arrayMap.put("end_time", mEnd_time);
        RetrofitManager.get(MyContants.BASEURL + "s=User/alivePro", arrayMap, new BaseObserver1<EasyBean>("") {
            @Override
            public void onSuccess(EasyBean result, String tag) {

                //                Toast.makeText(RegisterActivity.this, result.getSuccess(), Toast.LENGTH_SHORT).show();
                if (result.getCode() == 200) {
                    MyBlueActivity.this.finish();
                }
            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(MyBlueActivity.this, "请检查网络或重试" + code, Toast.LENGTH_SHORT).show();
            }
        });
    }

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
            //            byte[] bytes = HexUtil.decodeHex("04041710261640313234".toCharArray());//设置工作时间
            //                        byte[] bytes = HexUtil.decodeHex("fefe1710251510313233".toCharArray());//同步时间
            byte[] bytes = HexUtil.decodeHex("0203313233".toCharArray());//设置密码
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
    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            writeTongbuData("0000ffe3-0000-1000-8000-00805f9b34fb");
        }
    };
    private Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            writeTimeData("0000ffe4-0000-1000-8000-00805f9b34fb");
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

    private void send2(byte[] data) {
        if (dataInfoQueue != null) {
            dataInfoQueue.clear();
            dataInfoQueue = splitPacketFor20Byte(data);
            if (dataInfoQueue.peek() != null) {
                handler.postDelayed(runnable2, 100);
            }
        }
    }

    private void send3(byte[] data) {
        if (dataInfoQueue != null) {
            dataInfoQueue.clear();
            dataInfoQueue = splitPacketFor20Byte(data);
            if (dataInfoQueue.peek() != null) {
                handler.postDelayed(runnable3, 100);
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
                //                Toast.makeText(MyBlueActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                tv.setText("设备连接成功");
                writePswData("0000ffe2-0000-1000-8000-00805f9b34fb");//设置密码
            }

            @Override
            public void onConnectFailure(BleException exception) {
                //                Toast.makeText(MyBlueActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                tv.setText("设备连接失败");
            }

            @Override
            public void onDisconnect() {
                tv.setText("设备连接断开");
                //                Toast.makeText(MyBlueActivity.this, "连接断开", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void writePswData(String gattCharacteristic) {
        if (dataInfoQueue != null && !dataInfoQueue.isEmpty()) {
            if (dataInfoQueue.peek() != null) {
                ViseBluetooth.getInstance().writeCharacteristic(getGattCharacteristic(gattCharacteristic),
                        dataInfoQueue.poll(), new ICharacteristicCallback() {
                            @Override
                            public void onSuccess(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                                //                                Toast.makeText(MyBlueActivity.this, "write成功", Toast.LENGTH_SHORT).show();
                                tv.setText("设备激活中...");
                                byte[] bytes = HexUtil.decodeHex(("fefe" + mCurrent_time + "313233").toCharArray());//同步时间
                                send2(bytes);
                            }

                            @Override
                            public void onFailure(BleException exception) {
                                tv.setText("设备激活中...");
                                byte[] bytes = HexUtil.decodeHex(("fefe" + mCurrent_time + "313233").toCharArray());//同步时间
                                send2(bytes);
                                //                                Toast.makeText(MyBlueActivity.this, "write失败" + exception.getDescription(), Toast.LENGTH_SHORT).show();
                            }
                        });
                if (dataInfoQueue.peek() != null) {
                    handler.postDelayed(runnable, 100);
                }
            }
        }
        SystemClock.sleep(100);
    }

    private void writeTongbuData(String gattCharacteristic) {
        if (dataInfoQueue != null && !dataInfoQueue.isEmpty()) {
            if (dataInfoQueue.peek() != null) {
                ViseBluetooth.getInstance().writeCharacteristic(getGattCharacteristic(gattCharacteristic),
                        dataInfoQueue.poll(), new ICharacteristicCallback() {
                            @Override
                            public void onSuccess(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                                //                                Toast.makeText(MyBlueActivity.this, "write成功", Toast.LENGTH_SHORT).show();
                                byte[] bytes = HexUtil.decodeHex(("0404" + mEnd_time + "313233").toCharArray());//设置工作时间
                                send3(bytes);
                            }

                            @Override
                            public void onFailure(BleException exception) {
                                //                                Toast.makeText(MyBlueActivity.this, "write失败" + exception.getDescription(), Toast.LENGTH_SHORT).show();
                            }
                        });
                if (dataInfoQueue.peek() != null) {
                    handler.postDelayed(runnable, 100);
                }
            }
        }
        SystemClock.sleep(100);
    }

    private void writeTimeData(String gattCharacteristic) {
        if (dataInfoQueue != null && !dataInfoQueue.isEmpty()) {
            if (dataInfoQueue.peek() != null) {
                ViseBluetooth.getInstance().writeCharacteristic(getGattCharacteristic(gattCharacteristic),
                        dataInfoQueue.poll(), new ICharacteristicCallback() {
                            @Override
                            public void onSuccess(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                                //                                Toast.makeText(MyBlueActivity.this, "write成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(BleException exception) {
                                //                                Toast.makeText(MyBlueActivity.this, "write失败" + exception.getDescription(), Toast.LENGTH_SHORT).show();
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
            //            tv.setText(builder.toString());
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
            //            tv.setText(builder.toString());
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
                //                byte[] bytes = HexUtil.decodeHex("04041710261640313234".toCharArray());//设置工作时间
                //                                byte[] bytes = HexUtil.decodeHex("fefe1710251510313233".toCharArray());//同步时间
                byte[] bytes = HexUtil.decodeHex("0203313233".toCharArray());//设置密码
                send(bytes);
            }
        } else if (resultCode == RESULT_CANCELED) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
