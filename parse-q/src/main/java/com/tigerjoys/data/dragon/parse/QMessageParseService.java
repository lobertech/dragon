package com.tigerjoys.data.dragon.parse;

import javax.jms.Message;
import java.io.Serializable;
import java.util.List;

public interface QMessageParseService {

    /**
     * 解析消息
     * @param message
     */
    String parse(Message message);

    /**
     * 获取所有可调用的解析器对应的解析对象类型名称
     * @return
     */
    List<String> getParserObjectClassNames();

}
