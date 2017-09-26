package com.tigerjoys.data.dragon.magicq.jms.mdb;

import com.tigerjoys.data.dragon.tellfake.RecognitionService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.*;
import java.io.Serializable;

@MessageDriven(name = "ConsumeTopicMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/MAGICQMDBTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class ConsumeTopicMDB implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @Inject
    private RecognitionService recognitionService;

    private final static Logger LOGGER = Logger.getLogger(ConsumeTopicMDB.class.toString());

    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        Serializable obj = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                obj = msg.getObject();
                LOGGER.info("Received Message from topic: " + obj);
                recognitionService.Gather(message);
            } else {
                LOGGER.warn("Message of wrong type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            mdc.setRollbackOnly();
            throw new RuntimeException(e);
        }
    }
}
