package com.tigerjoys.data.dragon.tellfake.collect;

import javax.jms.Message;

public abstract class BaseEntityAssembler implements QMessageCollector {

    /**
     * 缓存多个消息为一条记录
     * @param message
     */
    protected abstract void cache(Message message);

}
