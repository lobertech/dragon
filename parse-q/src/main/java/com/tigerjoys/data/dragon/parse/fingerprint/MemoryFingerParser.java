package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.memory.MemoryFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class MemoryFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof MemoryFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            MemoryFinger memoryFinger = (MemoryFinger)fingerprint;

            UUID conversationID = memoryFinger.getConversationID();
            Integer start = memoryFinger.getStart();
            Integer end = memoryFinger.getEnd();
            Integer error = memoryFinger.getError();

            List<String> dataList = memoryFinger.getDataList();

            StringJoiner dataListJsonArray = new StringJoiner(",", "[", "]");
            for (String data : dataList) {
                dataListJsonArray.add("\"" + data + "\"");
            }

            StringJoiner memoryFingerJsonString = new StringJoiner(",", "{", "}");
            memoryFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            memoryFingerJsonString.add("\"start\":" + start.toString());
            memoryFingerJsonString.add("\"end\":" + end.toString());
            memoryFingerJsonString.add("\"error\":" + error.toString());
            memoryFingerJsonString.add("\"dataList\":" + dataListJsonArray);

            return super.PrefixClassName(fingerprint, memoryFingerJsonString.toString());
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
        return MemoryFinger.class.getName();
    }

}
