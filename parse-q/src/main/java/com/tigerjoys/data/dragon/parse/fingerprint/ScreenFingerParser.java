package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.screen.ScreenFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.UUID;

@RequestScoped
public class ScreenFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof ScreenFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            ScreenFinger screenFinger = (ScreenFinger)fingerprint;

            UUID conversationID = screenFinger.getConversationID();
            Integer start = screenFinger.getStart();
            Integer end = screenFinger.getEnd();
            Integer error = screenFinger.getError();

            Boolean status = screenFinger.getStatus();

            String screenFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"status\":" + status.toString() +
                    "}";

            return super.PrefixClassName(fingerprint, screenFingerJsonString);
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
        return ScreenFinger.class.getName();
    }

}
