package com.tigerjoys.data.dragon.appclient;


import com.tigerjoys.data.dragon.appclient.mock.DefaultQMessageMockService;
import com.tigerjoys.data.dragon.appclient.mock.QMessageMockService;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestConsumeClient {

    static final Logger logger = Logger.getLogger("TestConsumeClient");

    public static void main(String[] args) {

        Context remotingCtx = null;
        try {
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            remotingCtx = new InitialContext(env);
            ConnectionFactory cf = (ConnectionFactory) remotingCtx.lookup("jms/RemoteConnectionFactory");
            Topic topic = (Topic) remotingCtx.lookup("topic/MAGICQMDBTopic");

            String text;
            final int NUM_MSGS = 3;

            try (JMSContext context = cf.createContext();) {

                for (int i = 0; i < NUM_MSGS; i++) {
                    text = "This is message " + (i + 1);
                    System.out.println("Sending message: " + text);
                    context.createProducer().send(topic, text);
                }

                new TestConsumeClient().mockQMessage(context, topic);

                System.out.println("To see if the bean received the messages,");
                System.out.println(
                        " check <install_dir>/standalone/log/server.log.");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred: {0}", e.toString());
        } finally {
            if (remotingCtx != null) {
                try {
                    remotingCtx.close();
                } catch (NamingException e) {
                    logger.severe(e.getMessage());
                }
            }
        }
        System.exit(0);
    } // main

    private void mockQMessage(JMSContext context, Destination destination) {
        logger.info("Enter the method mockQmessage");
        QMessageMockService qMessageMockService = new DefaultQMessageMockService();
        List<Message> messages = qMessageMockService.mock(context);
        for (Message msg : messages) {
            context.createProducer().send(destination, msg);
            try {
                logger.info(((ObjectMessage) msg).getObject().getClass().getName() + " sent");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
