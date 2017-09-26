package com.tigerjoys.data.dragon.tellfake;

import com.tigerjoys.data.dragon.tellfake.collect.QMessageCollector;
import com.tokyohot.shibuya.contract.Conversation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Message;

@RequestScoped
public class DefaultRecognitionService implements RecognitionService {

    @Inject
    private QMessageCollector qMessageCollector;

    /**
     * 搜集消息
     *
     * @param message
     */
    @Override
    public void Gather(Message message) {
        qMessageCollector.collect(message);
    }

    /**
     * 识别假量
     *
     * @param conversation
     */
    @Override
    public Boolean recognize(Conversation conversation) {
        return null;
    }

}
