package com.tigerjoys.data.dragon.appclient.mock.conversation;

import com.tigerjoys.data.dragon.appclient.mock.QObjectMocker;
import com.tokyohot.shibuya.contract.Conversation;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class ConversationMocker implements QObjectMocker {

    /**
     * 模拟对象数据
     */
    @Override
    public Serializable mock() {
        Conversation conversation = new Conversation();
        UUID id = UUID.randomUUID();
        conversation.setID(id);
        Instant startAt = Instant.now();
        Instant endAt = Instant.now();
        conversation.setStartAt(startAt);
        conversation.setEndAt(endAt);

        return conversation;
    }

}
