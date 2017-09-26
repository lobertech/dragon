package com.tigerjoys.data.dragon.parse;

import java.io.Serializable;

public abstract class BaseQObjectParser implements QObjectParser {

    protected String PrefixClassName(Serializable object, String str) {
        return "{\"" + object.getClass().getName() + "\":" + str + "}";
    }

}
