package com.tigerjoys.data.dragon.parse.fingerprint;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.finger.origin.photo.PhotoFinger;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RequestScoped
public class PhotoFingerParser extends BaseQObjectParser {
    
    /**
     * 能否处理该对象
     *
     * @param fingerprint
     * @return
     */
    @Override
    public boolean isValid(Serializable fingerprint) {
        return fingerprint instanceof PhotoFinger;
    }

    /**
     * 解析对象
     *
     * @param fingerprint
     */
    @Override
    public String parse(Serializable fingerprint) {
        if (isValid(fingerprint)) {
            PhotoFinger photoFinger = (PhotoFinger)fingerprint;

            UUID conversationID = photoFinger.getConversationID();
            Integer start = photoFinger.getStart();
            Integer end = photoFinger.getEnd();
            Integer error = photoFinger.getError();

            List<String> dataList = photoFinger.getDataList();
            Integer count = photoFinger.getCount();
            Integer picCount = photoFinger.getPicCount();

            StringJoiner dataListJsonArray = new StringJoiner(",", "[", "]");
            for (String data : dataList) {
                dataListJsonArray.add("\"" + data + "\"");
            }

            StringJoiner photoFingerJsonString = new StringJoiner(",", "{", "}");
            photoFingerJsonString.add("\"conversationID\":\"" + conversationID.toString() + "\"");
            photoFingerJsonString.add("\"start\":" + start.toString());
            photoFingerJsonString.add("\"end\":" + end.toString());
            photoFingerJsonString.add("\"error\":" + error.toString());
            photoFingerJsonString.add("\"dataList\":" + dataListJsonArray);
            photoFingerJsonString.add("\"count\":" + count.toString());
            photoFingerJsonString.add("\"picCount\":" + picCount.toString());

            return super.PrefixClassName(fingerprint, photoFingerJsonString.toString());
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
        return PhotoFinger.class.getName();
    }

}
