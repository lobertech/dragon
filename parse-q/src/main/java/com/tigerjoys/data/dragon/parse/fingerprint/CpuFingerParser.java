package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.cpu.CpuFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
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

            StringJoiner dataListJsonArray = new StringJoiner(",", "[", "]");
            for (String data : dataList) {
                dataListJsonArray.add("\"" + data + "\"");
            }

            StringJoiner cpuFingerJsonString = new StringJoiner(",", "{", "}");
            cpuFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            cpuFingerJsonString.add("\"start\":" + start.toString());
            cpuFingerJsonString.add("\"end\":" + end.toString());
            cpuFingerJsonString.add("\"error\":" + error.toString());
            cpuFingerJsonString.add("\"dataList\":" + dataListJsonArray);
            cpuFingerJsonString.add("\"baseBand\":" + "\"" + baseBand + "\"");

            return super.PrefixClassName(fingerprint, cpuFingerJsonString.toString());
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
