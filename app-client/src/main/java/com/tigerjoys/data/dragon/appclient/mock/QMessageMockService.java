package com.tigerjoys.data.dragon.appclient.mock;

import javax.jms.JMSContext;
import javax.jms.Message;
import java.util.List;

public interface QMessageMockService {

    /**
     * 模拟消息数据
     * @param
     */
    List<Message> mock(JMSContext context);

}
