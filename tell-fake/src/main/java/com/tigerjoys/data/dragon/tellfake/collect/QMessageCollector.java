package com.tigerjoys.data.dragon.tellfake.collect;

import javax.jms.Message;

public interface QMessageCollector {

    /**
     * 搜集每一条消息
     * @param message
     */
    void collect(Message message);

}
