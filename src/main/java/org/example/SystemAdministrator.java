package org.example;

public class SystemAdministrator implements Runnable{
    private Inventory inventory;

    public SystemAdministrator(Inventory inventory) {
        this.inventory = inventory;
    }

    public void run() {
        // update quantities when new shipment arrived
        String[] items = {"Mobile", "Laptop", "Desktop","Printer"};

        for (String item : items) {
            int quantity = (int) (Math.random() * 10) + 1;
            inventory.updateQuantity(item, quantity);
            try {
                // simulate a delay
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
