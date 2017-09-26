package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.memory.MemoryFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
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

            String dataListJsonArray = "[";
            for (String data : dataList) {
                dataListJsonArray += "\"" + data + "\"" + ",";
            }
            dataListJsonArray = dataListJsonArray.substring(0, dataListJsonArray.length() - 1);
            dataListJsonArray += "]";

            String memoryFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"dataList\":" + dataListJsonArray +
                    "}";

            return super.PrefixClassName(fingerprint, memoryFingerJsonString);
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
