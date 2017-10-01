package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.usb.UsbFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class UsbFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof UsbFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            UsbFinger usbFinger = (UsbFinger)fingerprint;

            UUID conversationID = usbFinger.getConversationID();
            Integer start = usbFinger.getStart();
            Integer end = usbFinger.getEnd();
            Integer error = usbFinger.getError();

            Boolean status = usbFinger.getStatus();

            StringJoiner usbFingerJsonString = new StringJoiner(",", "{", "}");
            usbFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            usbFingerJsonString.add("\"start\":" + start.toString());
            usbFingerJsonString.add("\"end\":" + end.toString());
            usbFingerJsonString.add("\"error\":" + error.toString());
            usbFingerJsonString.add("\"status\":" + status.toString());

            return super.PrefixClassName(fingerprint, usbFingerJsonString.toString());
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
        return UsbFinger.class.getName();
    }

}
