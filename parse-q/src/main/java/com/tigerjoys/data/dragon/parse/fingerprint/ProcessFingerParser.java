package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.process.ProcessData;
import com.tokyohot.shibuya.finger.origin.process.ProcessFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
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

            String dataListJsonArray = "[";
            for (ProcessData processData : dataList) {
                dataListJsonArray += parseProcessData(processData) + ",";
            }
            dataListJsonArray = dataListJsonArray.substring(0, dataListJsonArray.length() - 1);
            dataListJsonArray += "]";

            String processFingerJsonString = "{" +
                    "\"conversationID\":" + "\"" + conversationID.toString() + "\"" +
                    "," + "\"start\":" + start.toString() +
                    "," + "\"end\":" + end.toString() +
                    "," + "\"error\":" + error.toString() +
                    "," + "\"count\":" + count.toString() +
                    "," + "\"dataList\":" + dataListJsonArray +
                    "}";

            return super.PrefixClassName(fingerprint, processFingerJsonString);
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

        String jsonProcessData = "{" +
                "\"name\":" + "\"" + name + "\"" +
                "," + "\"uid\":" + uid.toString() +
                "," + "\"pid\":" + pid.toString() +
                "," + "\"packageName\":" + "\"" + packageName + "\"" +
                "}";

        return jsonProcessData;
    }
    
}
