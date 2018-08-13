package com.test1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test7 {
}

class TestSynchronized {


    public static void main(String[] args) {

        Thread t1 = new Thread(new TestThread());
        Thread t2 = new Thread(new TestThread());
        t1.start();
        t2.start();
    }
}

class TestThread implements Runnable {
    static Integer sharedVariable = 200;
    public static Object lock = new Object();
    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        synchronized (lock) {
            while (sharedVariable < 210) {
                sharedVariable++;
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(name + ":" + sharedVariable);
                }
                //System.out.println(name + "hash:" + sharedVariable.hashCode() + "   i hash:" +
                //System.identityHashCode(sharedVariable));
            }

        }
    }
}

class AtomicIntergerDemo {
    static AtomicInteger i = new AtomicInteger();

    public static class AddThread implements Runnable {
        public void run() {
            for (int k = 0; k < 10000; k++)
                i.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        System.out.println(i);
    }
}