package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.sim.SimFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.UUID;

@RequestScoped
public class SimFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof SimFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            SimFinger simFinger = (SimFinger)fingerprint;

            UUID conversationID = simFinger.getConversationID();
            Integer start = simFinger.getStart();
            Integer end = simFinger.getEnd();
            Integer error = simFinger.getError();

            String id = simFinger.getId();
            String phoneNumber = simFinger.getPhoneNumber();
            String imei = simFinger.getImei();
            String imsi = simFinger.getImsi();
            String softwareVersion = simFinger.getSoftwareVersion();
            String voiceMailNumber = simFinger.getVoiceMailNumber();
            String operatorsName = simFinger.getOperatorsName();

            String simFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"id\":" + "\"" + id + "\"" +
                    "," + "\"phoneNumber\":" + "\"" + phoneNumber + "\"" +
                    "," + "\"imei\":" + "\"" + imei + "\"" +
                    "," + "\"imsi\":" + "\"" + imsi + "\"" +
                    "," + "\"softwareVersion\":" + "\"" + softwareVersion + "\"" +
                    "," + "\"voiceMailNumber\":" + "\"" + voiceMailNumber + "\"" +
                    "," + "\"operatorsName\":" + "\"" + operatorsName + "\"" +
                    "}";

            return super.PrefixClassName(fingerprint, simFingerJsonString);
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
        return SimFinger.class.getName();
    }

}
