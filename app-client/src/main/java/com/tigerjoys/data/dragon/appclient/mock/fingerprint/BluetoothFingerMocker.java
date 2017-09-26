package com.tigerjoys.data.dragon.appclient.mock.fingerprint;

import com.tigerjoys.data.dragon.appclient.mock.FingerPrintMocker;
import com.tokyohot.shibuya.finger.origin.bluetooth.BluetoothFinger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BluetoothFingerMocker extends FingerPrintMocker {

    /**
     * 模拟对象数据
     */
    @Override
    public Serializable mock() {
        List<String> devices = new ArrayList<>();
        devices.add("iphone");
        devices.add("mac");
        devices.add("airpod");

        BluetoothFinger bluetoothFinger = new BluetoothFinger();
        bluetoothFinger.setConversationID(getConversation().getID());
        bluetoothFinger.setStart(2);
        bluetoothFinger.setEnd(2);
        bluetoothFinger.setError(2);
        bluetoothFinger.setEnabled(true);
        bluetoothFinger.setName("testBluetoothName");
        bluetoothFinger.setMac("mac");
        bluetoothFinger.setDevices(devices);

        return bluetoothFinger;
    }

}
