package com.tigerjoys.data.dragon.tellfake.collect.assemble;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.tigerjoys.data.dragon.parse.QMessageParseService;
import com.tigerjoys.data.dragon.tellfake.collect.BaseEntityAssembler;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Message;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ConversationAssembler extends BaseEntityAssembler {

    @Inject
    QMessageParseService qMessageParseService;

    String conversationIDCached;

    List<String> contentCached;

    private final static Logger logger = Logger.getLogger(ConversationAssembler.class);

    ConversationAssembler() {
        conversationIDCached = null;
        contentCached = new ArrayList<>();
    }
    /**
     * 缓存多个消息为一条记录
     *
     * @param message
     */
    @Override
    protected void cache(Message message) {
        String messageJS = qMessageParseService.parse(message);
        Any messageAny = JsonIterator.deserialize(messageJS);
        String currentID = messageAny.get(messageAny.keys().toArray()[0]).get("conversationID").toString();

        logger.info("Received Message parsed as Json string: " + messageJS);
        logger.info("JsonIter any.keys(): " + messageAny.get(messageAny.keys().toArray()[0]).get("conversationID"));

        if (conversationIDCached.equals(currentID)) {
            contentCached.add(messageJS);
        }
        else {

            conversationIDCached = currentID;
            contentCached.clear();
        }
    }

    /**
     * 搜集每一条消息
     *
     * @param message
     */
    @Override
    public void collect(Message message) {
        cache(message);
    }

}
