package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.battery.BatteryFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class BatteryFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof BatteryFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            BatteryFinger batteryFinger = (BatteryFinger)fingerprint;

            UUID conversationID = batteryFinger.getConversationID();
            Integer start = batteryFinger.getStart();
            Integer end = batteryFinger.getEnd();
            Integer error = batteryFinger.getError();

            Integer level = batteryFinger.getLevel();


            StringJoiner batteryFingerJsonString = new StringJoiner(",", "{", "}");
            batteryFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            batteryFingerJsonString.add("\"start\":" + start.toString());
            batteryFingerJsonString.add("\"end\":" + end.toString());
            batteryFingerJsonString.add("\"error\":" + error.toString());
            batteryFingerJsonString.add("\"level\":" + level.toString());

            return super.PrefixClassName(fingerprint, batteryFingerJsonString.toString());
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
        return BatteryFinger.class.getName();
    }

}
