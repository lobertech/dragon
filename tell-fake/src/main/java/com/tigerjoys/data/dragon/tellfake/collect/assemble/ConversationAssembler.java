package com.tigerjoys.data.dragon.tellfake.collect.assemble;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.output.JsonStream;
import com.tigerjoys.data.dragon.parse.QMessageParseService;
import com.tigerjoys.data.dragon.persist.dao.FingerPrintRawDao;
import com.tigerjoys.data.dragon.persist.model.FingerPrintRaw;
import com.tigerjoys.data.dragon.tellfake.collect.QMessageCollector;
import com.tigerjoys.data.dragon.tellfake.collect.QObjectCacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Message;
import java.util.List;

/**
 * @author welon
 */
@RequestScoped
public class ConversationAssembler implements QMessageCollector {

    @Inject
    private QMessageParseService qMessageParseService;

    @Inject
    private QObjectCacher<String> qObjectCacher;

    @EJB
    private FingerPrintRawDao fingerPrintRawDao;

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

            String id = getID(ejected.get(0));
            FingerPrintRaw fingerPrintRaw = fingerPrintRawDao.findById(id);
            if (fingerPrintRaw == null) {
                fingerPrintRaw = new FingerPrintRaw();
            }
            for (String fieldJS : ejected) {
                Any fieldAny = JsonIterator.deserialize(fieldJS);
                String fieldClassName = (String) fieldAny.keys().toArray()[0];
                int index = fieldClassName.lastIndexOf(".");
                String fieldShortName = fieldClassName.substring(index+1);

                logger.warn("current fieldShortName: " + fieldShortName);
                switch (fieldShortName.toLowerCase()) {
                    case "conversation":
                        fingerPrintRaw.setConversationJS(fieldJS);
                        break;
                    case "devicedefinition":
                        fingerPrintRaw.setDevicedefinitionJS(fieldJS);
                        break;
                    case "appfinger":
                        fingerPrintRaw.setAppfingerJS(fieldJS);
                        break;
                    case "batteryfinger":
                        fingerPrintRaw.setBatteryfingerJS(fieldJS);
                        break;
                    case "bluetoothfinger":
                        fingerPrintRaw.setBluetoothfingerJS(fieldJS);
                        break;
                    case "cpufinger":
                        fingerPrintRaw.setCpufingerJS(fieldJS);
                        break;
                    case "devicefinger":
                        fingerPrintRaw.setDevicefingerJS(fieldJS);
                        break;
                    case "headsetfinger":
                        fingerPrintRaw.setHeadsetfingerJS(fieldJS);
                        break;
                    case "memoryfinger":
                        fingerPrintRaw.setMemoryfingerJS(fieldJS);
                        break;
                    case "netfinger":
                        fingerPrintRaw.setNetfingerJS(fieldJS);
                        break;
                    case "photofinger":
                        fingerPrintRaw.setPhotofingerJS(fieldJS);
                        break;
                    case "processfinger":
                        fingerPrintRaw.setProcessfingerJS(fieldJS);
                        break;
                    case "screenfinger":
                        fingerPrintRaw.setScreenfingerJS(fieldJS);
                        break;
                    case "simfinger":
                        fingerPrintRaw.setSimfingerJS(fieldJS);
                        break;
                    case "systimefinger":
                        fingerPrintRaw.setSystimefingerJS(fieldJS);
                        break;
                    case "usbfinger":
                        fingerPrintRaw.setUsbfingerJS(fieldJS);
                        break;
                    default:
                        break;
                }
            }
            if (fingerPrintRaw.getId() == null) {
                fingerPrintRaw.setId(id);
                fingerPrintRawDao.create(fingerPrintRaw);
            } else {
                fingerPrintRawDao.update(fingerPrintRaw);
            }
        }
    }

    private String getID(String messageJS) {
        Any messageAny = JsonIterator.deserialize(messageJS);
        String currentID = messageAny.get(messageAny.keys().toArray()[0]).get("conversationID").toString();

        if (currentID.isEmpty()) currentID = messageAny.get(messageAny.keys().toArray()[0]).get("ID").toString();

        return currentID;
    }

}
