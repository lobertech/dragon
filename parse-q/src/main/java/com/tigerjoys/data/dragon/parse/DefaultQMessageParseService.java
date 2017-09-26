package com.tigerjoys.data.dragon.parse;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class DefaultQMessageParseService implements QMessageParseService {

    @Inject
    private Instance<QObjectParser> objectParsers;

    /**
     * 解析消息
     *
     * @param message
     */
    @Override
    public String parse(Message message) {
        String result = null;
        ObjectMessage msg = null;
        Serializable obj = null;
        if (message instanceof ObjectMessage) {
            msg = (ObjectMessage) message;
            try {
                obj = msg.getObject();
                for (QObjectParser i : this.objectParsers) {
                    if (i.isValid(obj)) {
                        result = i.parse(obj);
                        break;
                    }
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取所有可调用的解析器对应的解析对象类型名称
     *
     * @return
     */
    @Override
    public List<String> getParserObjectClassNames() {
        List<String> names = new ArrayList<>();
        if (objectParsers != null) {
            for (QObjectParser i : this.objectParsers) {
                names.add(i.getObjectClassName());
            }
        }
        return names;
    }

}
