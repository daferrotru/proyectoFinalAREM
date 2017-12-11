/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arem.prototipo.stubmemory;

import arem.prototipo.model.Item;
import java.util.List;

/**
 *
 * @author rami
 */
public interface Memory {

    public boolean checkQuantity(int id, int quantity) throws MemoryException;

    public void buyItem(int id, int quantity) throws MemoryException;
    
    public List<Item> getItems();
}
