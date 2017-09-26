package com.tigerjoys.data.dragon.parse;

import java.io.Serializable;

public interface QObjectParser {

    /**
     * 能否处理该对象
     * @param object
     * @return
     */
    boolean isValid(Serializable object);

    /**
     * 解析对象
     * @param object
     */
    String parse(Serializable object);

    /**
     * 获取解析对象的类型名称
     * @return
     */
    String getObjectClassName();

}
