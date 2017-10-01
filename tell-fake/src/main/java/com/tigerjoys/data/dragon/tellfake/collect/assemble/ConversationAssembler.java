package com.tigerjoys.data.dragon.tellfake.collect.assemble;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.output.JsonStream;
import com.tigerjoys.data.dragon.parse.QMessageParseService;
import com.tigerjoys.data.dragon.persist.model.FingerPrintRaw;
import com.tigerjoys.data.dragon.tellfake.collect.QMessageCollector;
import com.tigerjoys.data.dragon.tellfake.collect.QObjectCacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jms.Message;
import java.time.Instant;
import java.util.List;
import java.util.StringJoiner;

@Singleton
public class ConversationAssembler implements QMessageCollector {

    @Inject
    QMessageParseService qMessageParseService;

    @Inject
    QObjectCacher<String> qObjectCacher;

    StringBuffer conversationIDCached;

    private static final Logger logger = LogManager.getLogger(ConversationAssembler.class);

    ConversationAssembler() {
        conversationIDCached = new StringBuffer();
    }

    /**
     * 搜集每一条消息
     *
     * @param message
     */
    @Override
    public void collect(Message message) {
        String messageJS = qMessageParseService.parse(message);
        Any messageAny = JsonIterator.deserialize(messageJS);
        String currentID = messageAny.get(messageAny.keys().toArray()[0]).get("conversationID").toString();

        if (currentID.isEmpty()) currentID = messageAny.get(messageAny.keys().toArray()[0]).get("ID").toString();

        logger.info("Received Message parsed as Json string: " + messageJS);
        logger.info("JsonIter any.keys(): " + currentID);

        if (conversationIDCached.length() == 0) {
            conversationIDCached.append(currentID);
        }

        if (conversationIDCached.toString().equals(currentID)) {
            qObjectCacher.cache(messageJS);
            System.out.println("enter method add(messageJS)");
            List<String> contentCached = qObjectCacher.get();
            String contentCachedJS = JsonStream.serialize(contentCached);
            logger.info("current contentCached: " + contentCachedJS);
        }
        else {
            FingerPrintRaw fingerPrintRaw = new FingerPrintRaw();

            List<String> contentCached = qObjectCacher.get();
            String contentCachedJS = JsonStream.serialize(contentCached);
            logger.info("current contentCached: " + contentCachedJS);
            conversationIDCached.setLength(0);
            conversationIDCached.append(currentID);
            qObjectCacher.clear();
            qObjectCacher.cache(messageJS);
        }
    }

}
