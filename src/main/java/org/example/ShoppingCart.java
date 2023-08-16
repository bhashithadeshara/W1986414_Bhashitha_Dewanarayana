package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ShoppingCart {
    private Map<String, Integer> ShoppingCart;
    private ReentrantLock lock;

    public ShoppingCart() {
        // initialize the cart items and the lock
        this.ShoppingCart = new HashMap<>();
        this.lock = new ReentrantLock();
    }

    public void addItem(String item) {
        // acquire the lock to ensure thread safety
        lock.lock();
        try {
            // add the item to the shopping cart or increment its quantity if it already exists
            ShoppingCart.put(item, ShoppingCart.getOrDefault(item, 0) + 1);
        } finally {
            // release the lock after the operation is complete
            lock.unlock();
        }
    }


    public void checkout(Inventory inventory, String shopperName) {
        // acquire the lock to ensure thread safety
        lock.lock();

        try {
            // iterate through the items in the cart
            for (Map.Entry<String, Integer> entry : ShoppingCart.entrySet()) {
                String item = entry.getKey();
                // check if the requested quantity is available in the item list
                int quantity = entry.getValue();

                if (inventory.checkAvailability(item, quantity)) {
                    // allocate the items from the inventory
                    inventory.allocateItems(item, quantity);
                    System.out.println(shopperName + " checked out " + quantity + " " + item);
                    System.out.println();
                } else {
                    // insufficient quantity in the inventory
                    System.out.println(shopperName + " couldn't check out " + quantity + " " + item + "{} Insufficient quantity.");
                    System.out.println();
                }
            }
        } finally {
            // release the lock after the operation is complete
            lock.unlock();
        }
    }
}
