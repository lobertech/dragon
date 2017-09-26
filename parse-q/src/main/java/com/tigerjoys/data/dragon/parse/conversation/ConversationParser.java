package com.tigerjoys.data.dragon.parse.conversation;

import com.tigerjoys.data.dragon.parse.BaseQObjectParser;
import com.tokyohot.shibuya.contract.Conversation;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@RequestScoped
public class ConversationParser extends BaseQObjectParser {

    /**
     * 能否处理该对象
     *
     * @param conversation
     * @return
     */
    @Override
    public boolean isValid(Serializable conversation) {
        return conversation instanceof Conversation;
    }

    /**
     * 解析对象
     *
     * @param conversation
     */
    @Override
    public String parse(Serializable conversation) {
        if (isValid(conversation)) {
            Conversation conversationObj = (Conversation) conversation;

            UUID ID = conversationObj.getID();
            Instant startAt = conversationObj.getStartAt();
            Instant endAt = conversationObj.getEndAt();

            String conversationJsonString = "{" +
                    "\"ID\":" + "\"" + ID.toString() + "\"" +
                    "," + "\"startAt\":" + "\"" + startAt.toString() + "\"" +
                    "," + "\"endAt\":" + "\"" + endAt.toString() + "\"" +
                    "}";
            return super.PrefixClassName(conversation, conversationJsonString);
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
        return Conversation.class.getName();
    }

}
