package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.net.NetFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.UUID;

@RequestScoped
public class NetFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof NetFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            NetFinger netFinger = (NetFinger)fingerprint;

            UUID conversationID = netFinger.getConversationID();
            Integer start = netFinger.getStart();
            Integer end = netFinger.getEnd();
            Integer error = netFinger.getError();

            String type = netFinger.getType();
            String bssid = netFinger.getBssid();
            String ssid = netFinger.getSsid();
            String mac = netFinger.getMac();
            String ip = netFinger.getIp();
            Integer rssi = netFinger.getRssi();
            Integer networkId = netFinger.getNetworkId();
            Integer linkSpeed = netFinger.getLinkSpeed();

            String netFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"type\":" + "\"" + type + "\"" +
                    "," + "\"bssid\":" + "\"" + bssid + "\"" +
                    "," + "\"ssid\":" + "\"" + ssid + "\"" +
                    "," + "\"mac\":" + "\"" + mac + "\"" +
                    "," + "\"ip\":" + "\"" + ip + "\"" +
                    "," + "\"rssi\":" + rssi.toString() +
                    "," + "\"networkId\":" + networkId.toString() +
                    "," + "\"linkSpeed\":" + linkSpeed.toString() +
                    "}";

            return super.PrefixClassName(fingerprint, netFingerJsonString);
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
        return NetFinger.class.getName();
    }

}
