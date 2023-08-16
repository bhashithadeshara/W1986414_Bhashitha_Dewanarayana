package org.example;

public class Shopper implements Runnable{

    private String shopperName;
    private Inventory inventory;
    private ShoppingCart cart;

    public Shopper(Inventory inventory,String shopperName) {
        this.shopperName = shopperName;
        this.inventory = inventory;
        this.cart = new ShoppingCart();
    }

    @Override
    public void run() {
        // adding items to the shopping cart
        String[] items = {"Mobile", "Laptop", "Desktop","Printer"};
        for (String item : items) {
            cart.addItem(item);
            System.out.println("Shopper " + shopperName + " added " + item + " to the cart");
            System.out.println();
            try {
                // simulate a delay
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // simulate checking out the cart
        cart.checkout(inventory, shopperName);
    }
}
