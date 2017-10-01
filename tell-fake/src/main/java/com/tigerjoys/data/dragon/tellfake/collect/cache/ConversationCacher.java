package com.tigerjoys.data.dragon.tellfake.collect.cache;

import com.tigerjoys.data.dragon.tellfake.collect.QObjectCacher;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Singleton
public class ConversationCacher implements QObjectCacher<String> {

    List<String> contentCached;

    ConversationCacher() {
        contentCached = new CopyOnWriteArrayList<String>();
    }
    /**
     * 缓存对象到内部变量
     *
     * @param object
     */
    @Override
    public void cache(String object) {
        contentCached.add(object);
    }

    /**
     * 获取缓存对象列表
     *
     * @return
     */
    @Override
    public List<String> get() {
        return contentCached;
    }

    /**
     * 清除缓冲区
     */
    @Override
    public void clear() {
        contentCached.clear();
    }
}
