package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.app.AppData;
import com.tokyohot.shibuya.finger.origin.app.AppFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class AppFingerParser extends BaseQObjectParser {

    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof AppFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            AppFinger appFinger = (AppFinger)fingerprint;

            UUID conversationID = appFinger.getConversationID();
            Integer start = appFinger.getStart();
            Integer end = appFinger.getEnd();
            Integer error = appFinger.getError();

            List<AppData> appDataList = appFinger.getDataList();
            Integer appCount = appFinger.getCount();

            StringJoiner appDataJsonArray = new StringJoiner(",", "[", "]");
            for (AppData appData : appDataList) {
                appDataJsonArray.add(parseAppData(appData));
            }

            StringJoiner appFingerJsonString = new StringJoiner(",", "{", "}");
            appFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            appFingerJsonString.add("\"start\":" + start.toString());
            appFingerJsonString.add("\"end\":" + end.toString());
            appFingerJsonString.add("\"error\":" + error.toString());
            appFingerJsonString.add("\"appDataList\":" + appDataJsonArray.toString());
            appFingerJsonString.add("\"appCount\":" + appCount.toString());
            return super.PrefixClassName(fingerprint, appFingerJsonString.toString());
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
        return AppFinger.class.getName();
    }

    private String parseAppData(AppData appData) {
        String packageName = appData.getPackageName();
        String name = appData.getName();
        Boolean isSys = appData.getSys();
        Boolean isCard = appData.getCard();

        StringJoiner jsonAppData = new StringJoiner(",", "{", "}");
        jsonAppData.add("\"packageName\":\"" + packageName + "\"");
        jsonAppData.add("\"name\":\"" + name + "\"");
        jsonAppData.add("\"isSys\":" + isSys.toString());
        jsonAppData.add("\"isCard\":" + isCard.toString());

        return jsonAppData.toString();
    }

}
