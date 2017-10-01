package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.bluetooth.BluetoothFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class BluetoothFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof BluetoothFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            BluetoothFinger bluetoothFinger = (BluetoothFinger)fingerprint;

            UUID conversationID = bluetoothFinger.getConversationID();
            Integer start = bluetoothFinger.getStart();
            Integer end = bluetoothFinger.getEnd();
            Integer error = bluetoothFinger.getError();

            Boolean enabled = bluetoothFinger.getEnabled();
            String name = bluetoothFinger.getName();
            String mac = bluetoothFinger.getMac();
            List<String> devices = bluetoothFinger.getDevices();

            StringJoiner devicesJsonArray = new StringJoiner(",", "[", "]");
            for (String device : devices) {
                devicesJsonArray.add("\"" + device + "\"");
            }

            StringJoiner bluetoothFingerJsonString = new StringJoiner(",", "{", "}");
            bluetoothFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            bluetoothFingerJsonString.add("\"start\":" + start.toString());
            bluetoothFingerJsonString.add("\"end\":" + end.toString());
            bluetoothFingerJsonString.add("\"error\":" + error.toString());
            bluetoothFingerJsonString.add("\"enabled\":" + enabled.toString());
            bluetoothFingerJsonString.add("\"name\":\"" + name + "\"");
            bluetoothFingerJsonString.add("\"mac\":\"" + mac + "\"");
            bluetoothFingerJsonString.add("\"devices\":" + devicesJsonArray.toString());

            return super.PrefixClassName(fingerprint, bluetoothFingerJsonString.toString());
        }
        return null;
    }

    /**
     * 获取解析对象的类型名称
     *
     * @return
     */
    @Override
    public String getObjectClassName() {
        return BluetoothFinger.class.getName();
    }

}
