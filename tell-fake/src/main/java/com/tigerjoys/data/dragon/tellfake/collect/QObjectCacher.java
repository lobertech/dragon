package com.tigerjoys.data.dragon.tellfake.collect;

import java.util.List;

public interface QObjectCacher<T> {

    /**
     * 缓存对象到内部变量
     * @param object
     */
    void cache(T object);

    /**
     * 获取缓存对象列表
     * @return
     */
    List<T> get();

    /**
     * 清除缓冲区
     */
    void clear();

}
