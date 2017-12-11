/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.stubmemory;

import arem.prototipo.model.Item;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author rami
 */
public class InMemoryPersistence implements Memory {

    public static final ConcurrentHashMap<Integer, Item> ITEMS = new ConcurrentHashMap<>();

    public InMemoryPersistence() {
        Item i1 = new Item(1, 3, "Vaso");
        Item i2 = new Item(2, 4, "Audífonos");
        Item i3 = new Item(3, 1, "Kindle");
        Item i4 = new Item(4, 6, "TV");

        ITEMS.putIfAbsent(1, i1);
        ITEMS.putIfAbsent(2, i2);
        ITEMS.putIfAbsent(3, i3);
        ITEMS.putIfAbsent(4, i4);
    }

    @Override
    public boolean checkQuantity(int id, int quantity) throws MemoryException {
        if (ITEMS.containsKey(id)) {
            Item item = ITEMS.get(id);
            return item.getQuantity() >= quantity;
        } else {
            throw new MemoryException("El item no existe");
        }
    }

    @Override
    public void buyItem(int id, int quantity) throws MemoryException {
        if (ITEMS.containsKey(id)) {
            ITEMS.get(id).sellQuantity(quantity);
        } else {
            throw new MemoryException("El item no existe");
        }
    }

    @Override
    public List<Item> getItems() {
        return ITEMS.values().stream().collect(Collectors.toList());
    }
}
