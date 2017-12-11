/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.services;

import arem.prototipo.messagehandler.ServiceMixMessageHandler;
import arem.prototipo.model.Item;
import arem.prototipo.stubmemory.InMemoryPersistence;
import arem.prototipo.stubmemory.Memory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.jms.JMSException;
import org.springframework.stereotype.Service;

/**
 *
 * @author rami
 */
@Service
public class PrototipoServices {

    private Memory memory;

    public PrototipoServices() {
        this.memory = new InMemoryPersistence();
        try {
            ServiceMixMessageHandler.start(this.memory);
        } catch (JMSException ex) {
            Logger.getLogger(PrototipoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Item> getItems() {
        return memory.getItems();
    }

    public List<String> getMessages() {
        return ServiceMixMessageHandler.MESSAGES.stream().collect(Collectors.toList());
    }
}
