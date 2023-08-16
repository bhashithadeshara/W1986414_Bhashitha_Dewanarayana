package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {

    private Map<String, Integer> items;
    private ReentrantLock lock;

    public Inventory() {
        // initialize the inventory and the lock
        this.items = new HashMap<>();
        this.lock = new ReentrantLock();
    }


    public void updateQuantity(String item, int quantity) {
        // acquire the lock to ensure thread safety
        lock.lock();
        try {
            // update the quantity of the specified item in the inventory
            items.put(item, quantity);
            System.out.println("Updated quantity for " + item + ": " + quantity);
            System.out.println();
        } finally {
            // release the lock after the operation is complete
            lock.unlock();
        }
    }

    //check availability
    public boolean checkAvailability(String item, int quantity) {
        // acquire the lock to ensure thread safety
        lock.lock();
        try {
            // check if the requested quantity of the item is available in the inventory
            if(items.containsKey(item) && items.get(item) >= quantity){
                return true;
            }else{
                return false;
            }
        } finally {
            // release the lock after the operation is complete
            lock.unlock();
        }
    }

    //allocate items
    public void allocateItems(String item, int quantity) {
        // acquire the lock to ensure thread safety
        lock.lock();
        try {
            int currentQuantity = items.get(item);
            // reduce the quantity of the item in the inventory by the allocated quantity
            items.put(item, currentQuantity - quantity);

            System.out.println("Allocated " + quantity + " " + item + " from the inventory");
            System.out.println();
        } finally {
            // release the lock after the operation is complete
            lock.unlock();
        }
    }
}
