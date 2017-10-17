package com.example.administrator.lenglian.utils;

import android.content.Context;

import com.clj.fastble.BleManager;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BlueUtils {

    private final BleManager mBleManager;

    public BlueUtils(Context context) {
        mBleManager = new BleManager(context);
    }

    public boolean isSupportBlue() {
        return mBleManager.isSupportBle();
    }

    public void startBlue() {
        mBleManager.enableBluetooth();
    }

    public void closeBlue() {
        mBleManager.disableBluetooth();
    }

    public boolean isBlueStart() {
        return mBleManager.isBlueEnable();
    }

    public boolean isInScanning() {
        return mBleManager.isInScanning();
    }

    public boolean isConnectingOrConnected() {
        return mBleManager.isConnectingOrConnected();
    }

    public boolean isConnected() {
        return mBleManager.isConnected();
    }

    public boolean isServiceDiscovered() {
        return mBleManager.isServiceDiscovered();
    }
}
