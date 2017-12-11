/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.messagehandler;

import arem.prototipo.stubmemory.Memory;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.jms.JMSException;

/**
 *
 * @author 2122825
 */
public class ServiceMixMessageHandler {

    private static final ExecutorService POOL = Executors.newCachedThreadPool();
    public static final ConcurrentLinkedQueue<String> MESSAGES = new ConcurrentLinkedQueue<String>();

    public static void start(Memory memory) throws JMSException {
        POOL.execute(new ServiceMixListenTask(memory));
    }

    public static void sendTask(boolean response) {
        POOL.execute(new ServiceMixSendTask(response));
    }
}
