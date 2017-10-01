package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.process.ProcessData;
import com.tokyohot.shibuya.finger.origin.process.ProcessFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class ProcessFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof ProcessFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            ProcessFinger processFinger = (ProcessFinger)fingerprint;

            UUID conversationID = processFinger.getConversationID();
            Integer start = processFinger.getStart();
            Integer end = processFinger.getEnd();
            Integer error = processFinger.getError();

            Integer count = processFinger.getCount();
            List<ProcessData> dataList = processFinger.getDataList();

            StringJoiner dataListJsonArray = new StringJoiner(",", "[", "]");
            for (ProcessData processData : dataList) {
                dataListJsonArray.add(parseProcessData(processData));
            }

            StringJoiner processFingerJsonString = new StringJoiner(",", "{", "}");
            processFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            processFingerJsonString.add("\"start\":" + start.toString());
            processFingerJsonString.add("\"end\":" + end.toString());
            processFingerJsonString.add("\"error\":" + error.toString());
            processFingerJsonString.add("\"count\":" + count.toString());
            processFingerJsonString.add("\"dataList\":" + dataListJsonArray);

            return super.PrefixClassName(fingerprint, processFingerJsonString.toString());
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
        return ProcessFinger.class.getName();
    }

    private String parseProcessData(ProcessData processData) {
        String name = processData.getName();
        Integer uid = processData.getUid();
        Integer pid = processData.getPid();
        String packageName = processData.getPackageName();

        StringJoiner jsonProcessData = new StringJoiner(",", "{", "}");
        jsonProcessData.add("\"name\":\"" + name + "\"");
        jsonProcessData.add("\"uid\":" + uid.toString());
        jsonProcessData.add("\"pid\":" + pid.toString());
        jsonProcessData.add("\"packageName\":\"" + packageName + "\"");

        return jsonProcessData.toString();
    }
    
}
