package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.net.NetFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.StringJoiner;
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

            StringJoiner netFingerJsonString = new StringJoiner(",", "{", "}");
            netFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            netFingerJsonString.add("\"start\":" + start.toString());
            netFingerJsonString.add("\"end\":" + end.toString());
            netFingerJsonString.add("\"error\":" + error.toString());
            netFingerJsonString.add("\"type\":\"" + type + "\"");
            netFingerJsonString.add("\"bssid\":\"" + bssid + "\"");
            netFingerJsonString.add("\"ssid\":\"" + ssid + "\"");
            netFingerJsonString.add("\"mac\":\"" + mac + "\"");
            netFingerJsonString.add("\"ip\":\"" + ip + "\"");
            netFingerJsonString.add("\"rssi\":" + rssi.toString());
            netFingerJsonString.add("\"networkId\":" + networkId.toString());
            netFingerJsonString.add("\"linkSpeed\":" + linkSpeed.toString());

            return super.PrefixClassName(fingerprint, netFingerJsonString.toString());
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
