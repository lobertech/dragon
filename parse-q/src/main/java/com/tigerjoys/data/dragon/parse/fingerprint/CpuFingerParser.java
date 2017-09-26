package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.cpu.CpuFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class CpuFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof CpuFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            CpuFinger cpuFinger = (CpuFinger)fingerprint;

            UUID conversationID = cpuFinger.getConversationID();
            Integer start = cpuFinger.getStart();
            Integer end = cpuFinger.getEnd();
            Integer error = cpuFinger.getError();

            List<String> dataList = cpuFinger.getDataList();
            String baseBand = cpuFinger.getBaseBand();

            String dataListJsonArray = "[";
            for (String data : dataList) {
                dataListJsonArray += "\"" + data + "\"" + ",";
            }
            dataListJsonArray = dataListJsonArray.substring(0, dataListJsonArray.length() - 1);
            dataListJsonArray += "]";

            String cpuFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"dataList\":" + dataListJsonArray +
                    "," + "\"baseBand\":" + "\"" + baseBand + "\"" +
                    "}";

            return super.PrefixClassName(fingerprint, cpuFingerJsonString);
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
        return CpuFinger.class.getName();
    }

}
