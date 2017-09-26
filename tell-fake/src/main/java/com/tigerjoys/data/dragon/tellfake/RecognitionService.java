package com.tigerjoys.data.dragon.tellfake;

import com.tokyohot.shibuya.contract.Conversation;

import javax.jms.Message;

public interface RecognitionService {

    /**
     * 搜集消息
     * @param message
     */
    void Gather(Message message);

    /**
     * 识别假量
     * @param conversation
     */
    Boolean recognize(Conversation conversation);
}
