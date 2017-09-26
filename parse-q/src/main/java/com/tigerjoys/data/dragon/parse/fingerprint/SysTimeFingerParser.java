package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.sysTime.SysTimeFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.UUID;

@RequestScoped
public class SysTimeFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof SysTimeFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            SysTimeFinger sysTimeFinger = (SysTimeFinger)fingerprint;

            UUID conversationID = sysTimeFinger.getConversationID();
            Integer start = sysTimeFinger.getStart();
            Integer end = sysTimeFinger.getEnd();
            Integer error = sysTimeFinger.getError();

            Long startSystemTime = sysTimeFinger.getStartSystemTime();

            String sysTimeFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"startSystemTime\":" + startSystemTime.toString() +
                    "}";

            return super.PrefixClassName(fingerprint, sysTimeFingerJsonString);
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
        return SysTimeFinger.class.getName();
    }

}
