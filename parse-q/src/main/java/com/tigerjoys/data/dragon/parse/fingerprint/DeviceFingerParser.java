package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.device.DeviceFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class DeviceFingerParser extends BaseQObjectParser {

    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof DeviceFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            DeviceFinger deviceFinger = (DeviceFinger)fingerprint;

            UUID conversationID = deviceFinger.getConversationID();
            Integer start = deviceFinger.getStart();
            Integer end = deviceFinger.getEnd();
            Integer error = deviceFinger.getError();

            String device = deviceFinger.getDevice();
            String product = deviceFinger.getProduct();
            String cpuAbi2 = deviceFinger.getCpuAbi2();
            String tags = deviceFinger.getTags();
            String display = deviceFinger.getDisplay();
            String board = deviceFinger.getBoard();
            String devicefingerprint = deviceFinger.getFingerprint();
            String id = deviceFinger.getId();
            String manufacturer = deviceFinger.getManufacturer();
            String user = deviceFinger.getUser();
            String bootloader = deviceFinger.getBootloader();
            String hardware = deviceFinger.getHardware();
            String host = deviceFinger.getHost();
            Long time = deviceFinger.getTime();
            String serial = deviceFinger.getSerial();
            String releaseVersion = deviceFinger.getReleaseVersion();
            String sdkVersion = deviceFinger.getSdkVersion();
            String incrementalVersion = deviceFinger.getIncrementalVersion();
            String codenameVersion = deviceFinger.getCodenameVersion();

            StringJoiner deviceFingerJsonString = new StringJoiner(",", "{", "}");
            deviceFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            deviceFingerJsonString.add("\"start\":" + start.toString());
            deviceFingerJsonString.add("\"end\":" + end.toString());
            deviceFingerJsonString.add("\"error\":" + error.toString());
            deviceFingerJsonString.add("\"device\":\"" + device + "\"");
            deviceFingerJsonString.add("\"product\":\"" + product + "\"");
            deviceFingerJsonString.add("\"cpuAbi2\":\"" + cpuAbi2 + "\"");
            deviceFingerJsonString.add("\"tags\":\"" + tags + "\"");
            deviceFingerJsonString.add("\"display\":\"" + display + "\"");
            deviceFingerJsonString.add("\"board\":\"" + board + "\"");
            deviceFingerJsonString.add("\"fingerprint\":\"" + devicefingerprint + "\"");
            deviceFingerJsonString.add("\"id\":\"" + id + "\"");
            deviceFingerJsonString.add("\"manufacturer\":\"" + manufacturer + "\"");
            deviceFingerJsonString.add("\"user\":\"" + user + "\"");
            deviceFingerJsonString.add("\"bootloader\":\"" + bootloader + "\"");
            deviceFingerJsonString.add("\"hardware\":\"" + hardware + "\"");
            deviceFingerJsonString.add("\"host\":\"" + host + "\"");
            deviceFingerJsonString.add("\"time\":" + time.toString());
            deviceFingerJsonString.add("\"serial\":\"" + serial + "\"");
            deviceFingerJsonString.add("\"releaseVersion\":\"" + releaseVersion + "\"");
            deviceFingerJsonString.add("\"sdkVersion\":\"" + sdkVersion + "\"");
            deviceFingerJsonString.add("\"incrementalVersion\":\"" + incrementalVersion + "\"");
            deviceFingerJsonString.add("\"codenameVersion\":\"" + codenameVersion + "\"");

            return super.PrefixClassName(fingerprint, deviceFingerJsonString.toString());
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
        return DeviceFinger.class.getName();
    }

}
