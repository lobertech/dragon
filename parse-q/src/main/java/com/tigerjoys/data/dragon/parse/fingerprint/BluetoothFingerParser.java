package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.bluetooth.BluetoothFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
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

            String devicesJsonArray = "[";
            for (String device : devices) {
                devicesJsonArray += "\"" + device + "\"" + ",";
            }
            devicesJsonArray = devicesJsonArray.substring(0, devicesJsonArray.length() - 1);
            devicesJsonArray += "]";

            String bluetoothFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"enabled\":" + enabled.toString() +
                    "," + "\"name\":" + "\"" + name + "\"" +
                    "," + "\"mac\":" + "\"" + mac + "\"" +
                    "," + "\"devices\":" + devicesJsonArray +
                    "}";

            return super.PrefixClassName(fingerprint, bluetoothFingerJsonString);
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
