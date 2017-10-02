package com.tigerjoys.data.dragon.tellfake.collect.cache;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.tigerjoys.data.dragon.tellfake.collect.QObjectCacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ConversationCacher implements QObjectCacher<String> {

    private String conversationIDCached;
    private List<String> contentCached;

    private static final Logger logger = LogManager.getLogger(ConversationCacher.class);

    ConversationCacher() {
        conversationIDCached = null;
        contentCached = new ArrayList<>();
    }

    /**
     * 缓存对象到缓冲区
     * 若有新的ID存入则返回新ID到来之前的缓冲区内容，并重新开始缓存新信息
     * 否则返回null
     * @param object
     */
    @Override
    public List<String> cache(String object) {
        List<String> result = null;
        String currentID = getID(object);
        if (currentID.isEmpty()) {
            logger.error("Found error message object that had empty conversation id!");
        } else {
            if (conversationIDCached == null) {
                conversationIDCached = currentID;
            }

            if (conversationIDCached.equals(currentID)) {
                contentCached.add(object);
            } else {
                result = new ArrayList<>();
                for (String str : contentCached) {
                    result.add(str);
                }
                conversationIDCached = currentID;
                contentCached.clear();
                contentCached.add(object);
            }
        }
        return result;
    }

    /**
     * 提取待缓存的Json串中的ID信息
     * @param messageJS
     * @return
     */
    private String getID(String messageJS) {
        Any messageAny = JsonIterator.deserialize(messageJS);
        String currentID = messageAny.get(messageAny.keys().toArray()[0]).get("conversationID").toString();

        if (currentID.isEmpty()) currentID = messageAny.get(messageAny.keys().toArray()[0]).get("ID").toString();

        return currentID;
    }
}
