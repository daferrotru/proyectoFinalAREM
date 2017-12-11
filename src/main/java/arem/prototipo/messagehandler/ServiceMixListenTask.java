/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.messagehandler;

import arem.prototipo.stubmemory.Memory;
import arem.prototipo.stubmemory.MemoryException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author rami
 */
public class ServiceMixListenTask implements Runnable {

    private final Memory memory;

    public ServiceMixListenTask(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void run() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://34.214.141.194:61616?jms.useAsyncSend=true");

            // Create a Connection
            Connection connection = connectionFactory.createConnection("smx", "smx");
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("salida");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            while (true) {
                Message message = consumer.receive();

                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                    ServiceMixMessageHandler.MESSAGES.add("Item - Quantity: " + text);
                    handleRequest(text);

                } else {
                    System.out.println("Received: " + message);
                }
            }
//            consumer.close();
//            session.close();
//            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ServiceMixListenTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleRequest(String text) {
        try {
            String[] split = text.split("-");
            int item_id = Integer.parseInt(split[0]);
            int item_quantity = Integer.parseInt(split[1]);
            if (memory.checkQuantity(item_id, item_quantity)) {
                memory.buyItem(item_id, item_quantity);
                ServiceMixMessageHandler.sendTask(true);
            } else {
                ServiceMixMessageHandler.sendTask(false);
            }
        } catch (MemoryException ex) {
            Logger.getLogger(ServiceMixListenTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
