/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.messagehandler;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author rami
 */
public class ServiceMixSendTask implements Runnable {

    private final boolean response;

    public ServiceMixSendTask(boolean response) {
        this.response = response;
    }

    @Override
    public void run() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://34.214.141.194:61616?jms.useAsyncSend=true");
            // Create a Connection
            Connection connection = connectionFactory.createConnection("smx", "smx");
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("bonitaEvents");

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a messages
            String text = response ? "true" : "false";
            System.out.println("Sent: " + text);
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        } catch (JMSException e) {
            System.out.println("Caught: " + e);
        }
    }

}
