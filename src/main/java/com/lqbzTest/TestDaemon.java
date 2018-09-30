package com.lqbzTest;

import java.util.concurrent.TimeUnit;

public class TestDaemon {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
        try{
            Thread.sleep(5000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }

}

class ADaemon implements Runnable {

    public void run() {
        try {
            System.out.println("start ADaemon...");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        } finally {
            System.out.println("This shoud be always run ?");
        }
    }

}
