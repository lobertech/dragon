package com.tigerjoys.data.dragon.tellfake.collect.assemble;

import com.jsoniter.output.JsonStream;
import com.tigerjoys.data.dragon.parse.QMessageParseService;
import com.tigerjoys.data.dragon.tellfake.collect.QMessageCollector;
import com.tigerjoys.data.dragon.tellfake.collect.QObjectCacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Message;
import java.util.List;

@RequestScoped
public class ConversationAssembler implements QMessageCollector {

    @Inject
    private QMessageParseService qMessageParseService;

    @Inject
    private QObjectCacher<String> qObjectCacher;

    private static final Logger logger = LogManager.getLogger(ConversationAssembler.class);

    /**
     * 搜集每一条消息
     *
     * @param message
     */
    @Override
    public void collect(Message message) {
        String messageJS = qMessageParseService.parse(message);

        logger.info("Received Message parsed as Json string: " + messageJS);
        List<String> ejected = qObjectCacher.cache(messageJS);

        if (ejected != null) {
            String contentCachedJS = JsonStream.serialize(ejected);
            logger.warn("current ejected: " + contentCachedJS);
        }
    }

}
