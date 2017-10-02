package com.tigerjoys.data.dragon.tellfake.collect;

import java.util.List;

public interface QObjectCacher<T> {

    /**
     * 缓存对象到缓冲区
     * 若对象满足某条件则返回条件到达前的缓冲区内容，并重新开始缓存新的信息
     * 否则返回null
     * @param object
     */
    List<T> cache(T object);

}
