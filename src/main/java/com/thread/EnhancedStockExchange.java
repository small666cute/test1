package com.thread;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
//通过使用信号CountDownLatch来同步
//等到所有的线程start以后，再放行，等到所有的线程都结束以后，再结束main线程，使用await()来等到信号数值减为0
public class EnhancedStockExchange {
    public static void main(String[] args) {
        BlockingQueue<Integer> orderQueue = new LinkedBlockingQueue<Integer>();
        CountDownLatch startSignal = new CountDownLatch(1);//锁1，开始信号，开始信号-1就变成0，所以一个操作就会释放所有等待的线程
        CountDownLatch stopSignal = new CountDownLatch(200);//锁2，停止信号，因为生产者和消费者总共200，每个线程都会-1
        Seller seller = new Seller(orderQueue, startSignal, stopSignal);
        Thread[] sellerThread = new Thread[100];
        for (int i = 0; i < 100; i++) {
            sellerThread[i] = new Thread(seller);
            sellerThread[i].start();
        }
        Buyer buyer = new Buyer(orderQueue, startSignal, stopSignal);
        Thread[] buyerThread = new Thread[100];
        for (int i = 0; i < 100; i++) {
            buyerThread[i] = new Thread(buyer);
            buyerThread[i].start();
        }
        System.out.println("开始运行了");
        startSignal.countDown();//创建完所有的线程之后，才把信号-1，现在所以线程才可以开始执行（这样就不存在由于程序中按顺序启动，影响到线程的优先级的问题了）
        try {
            while (System.in.read() != '\n') ;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("结束啦");
        for (Thread t : sellerThread) {
            t.interrupt();
        }
        for (Thread t : buyerThread) {
            t.interrupt();
        }
        try {
            stopSignal.await();//main等待stopsignal减到0，一直等，减到0，即所有的线程都结束了，才继续执行（不过感觉不是守护进程的话，反正也会等到线程执行完了再结束啊，还是说main线程会做一些后面的清理操作，所有需要main线程最后结束呢？）
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("关啦");
    }
}

class Seller implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private static int id;
    private CountDownLatch startLatch, stopLatch;

    public Seller(BlockingQueue orderQueue, CountDownLatch startLatch, CountDownLatch stopLatch) {
        this.orderQueue = orderQueue;
        this.startLatch = startLatch;
        this.stopLatch = stopLatch;
    }

    public void run() {
        try {
            startLatch.await();//会强行等待startlatch信号
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while (shutdownRequest == false) {
            Integer quantity = (int) (Math.random() * 100);
            try {
                orderQueue.put(quantity);
                System.out.println("sell order producer : " + Thread.currentThread().getName() + ": " + quantity);
            } catch (InterruptedException iex) {
                shutdownRequest = true;
            }
        }
        stopLatch.countDown();//每个线程都将停止信号stoplatch-1，表示我执行完了
    }
}

class Buyer implements Runnable {
    private BlockingQueue orderQueue;
    private boolean shutdownRequest = false;
    private CountDownLatch startLatch, stopLatch;

    public Buyer(BlockingQueue orderQueue, CountDownLatch startLatch, CountDownLatch stopLatch) {
        this.orderQueue = orderQueue;
        this.startLatch = startLatch;
        this.stopLatch = stopLatch;
    }

    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        while (shutdownRequest == false) {
            try {
                Integer quantity = (Integer) orderQueue.take();
                System.out.println("buy order consumer   " + Thread.currentThread().getName() + ":" + quantity);
            } catch (InterruptedException iex) {
                shutdownRequest = true;
            }
        }
        stopLatch.countDown();
    }
}
