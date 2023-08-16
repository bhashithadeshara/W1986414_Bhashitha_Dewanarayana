package org.example;

public class Main {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        // create user threads
        Thread userThread1 = new Thread(new Shopper(inventory,"Shopper A"));
        Thread userThread2 = new Thread(new Shopper(inventory,"Shopper B"));
        Thread userThread3 = new Thread(new Shopper(inventory,"Shopper C"));


        // create admin thread
        Thread adminThread = new Thread(new SystemAdministrator(inventory));

        // start user and admin threads
        userThread1.start();
        userThread2.start();
        userThread3.start();
        adminThread.start();

        // wait for all threads to complete
        try {
            userThread1.join();
            userThread2.join();
            userThread3.join();
            adminThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}