package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.headset.HeadsetFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class HeadsetFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof HeadsetFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            HeadsetFinger headsetFinger = (HeadsetFinger)fingerprint;

            UUID conversationID = headsetFinger.getConversationID();
            Integer start = headsetFinger.getStart();
            Integer end = headsetFinger.getEnd();
            Integer error = headsetFinger.getError();

            Boolean state = headsetFinger.getState();

            StringJoiner headsetFingerJsonString = new StringJoiner(",", "{", "}");
            headsetFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            headsetFingerJsonString.add("\"start\":" + start.toString());
            headsetFingerJsonString.add("\"end\":" + end.toString());
            headsetFingerJsonString.add("\"error\":" + error.toString());
            headsetFingerJsonString.add("\"state\":" + state.toString());

            return super.PrefixClassName(fingerprint, headsetFingerJsonString.toString());
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
        return HeadsetFinger.class.getName();
    }

}
