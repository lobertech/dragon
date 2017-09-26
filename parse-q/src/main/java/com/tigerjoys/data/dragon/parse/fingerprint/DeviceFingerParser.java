package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.device.DeviceFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
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

            String deviceFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"device\":" + "\"" + device + "\"" +
                    "," + "\"product\":" + "\"" + product + "\"" +
                    "," + "\"cpuAbi2\":" + "\"" + cpuAbi2 + "\"" +
                    "," + "\"tags\":" + "\"" + tags + "\"" +
                    "," + "\"display\":" + "\"" + display + "\"" +
                    "," + "\"board\":" + "\"" + board + "\"" +
                    "," + "\"fingerprint\":" + "\"" + devicefingerprint + "\"" +
                    "," + "\"id\":" + "\"" + id + "\"" +
                    "," + "\"manufacturer\":" + "\"" + manufacturer + "\"" +
                    "," + "\"user\":" + "\"" + user + "\"" +
                    "," + "\"bootloader\":" + "\"" + bootloader + "\"" +
                    "," + "\"hardware\":" + "\"" + hardware + "\"" +
                    "," + "\"host\":" + "\"" + host + "\"" +
                    "," + "\"time\":" + time.toString() +
                    "," + "\"serial\":" + "\"" + serial + "\"" +
                    "," + "\"releaseVersion\":" + "\"" + releaseVersion + "\"" +
                    "," + "\"sdkVersion\":" + "\"" + sdkVersion + "\"" +
                    "," + "\"incrementalVersion\":" + "\"" + incrementalVersion + "\"" +
                    "," + "\"codenameVersion\":" + "\"" + codenameVersion + "\"" +
                    "}";

            return super.PrefixClassName(fingerprint, deviceFingerJsonString);
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
