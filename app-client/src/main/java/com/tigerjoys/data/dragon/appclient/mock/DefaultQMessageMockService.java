package com.tigerjoys.data.dragon.appclient.mock;

import com.tigerjoys.data.dragon.appclient.mock.conversation.ConversationMocker;
import com.tigerjoys.data.dragon.appclient.mock.fingerprint.AppFingerMocker;
import com.tigerjoys.data.dragon.appclient.mock.fingerprint.BluetoothFingerMocker;
import com.tokyohot.shibuya.contract.Conversation;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DefaultQMessageMockService implements QMessageMockService {

    private List<FingerPrintMocker> fingerPrintMockers;

    private ConversationMocker conversationMocker;

    static final Logger logger = Logger.getLogger("DefaultQMessageMockService");

    public DefaultQMessageMockService() {
        conversationMocker = new ConversationMocker();
        fingerPrintMockers = new ArrayList<>();
        fingerPrintMockers.add(new AppFingerMocker());
        fingerPrintMockers.add(new BluetoothFingerMocker());
    }

    /**
     * 模拟消息数据
     *
     * @param context
     */
    @Override
    public List<Message> mock(JMSContext context) {
        List<Message> messages = new ArrayList<>();
        if (context != null) {
            try {
                logger.info("Enter DefaultQMessageMockService");
                for (int i = 0; i < 5; i++) {
                    Conversation conversation = (Conversation) conversationMocker.mock();
                    for (QObjectMocker mocker : this.fingerPrintMockers) {
                        FingerPrintMocker fingerMocker = (FingerPrintMocker) mocker;
                        fingerMocker.setConversation(conversation);

                        ObjectMessage message = context.createObjectMessage();
                        message.setObject(fingerMocker.mock());
                        messages.add(message);
                        logger.info(fingerMocker.mock().getClass().getName() + " mocked");
                    }
                    ObjectMessage message = context.createObjectMessage();
                    message.setObject(conversation);
                    messages.add(message);
                    logger.info(conversation.getClass().getName() + " mocked");
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

}
