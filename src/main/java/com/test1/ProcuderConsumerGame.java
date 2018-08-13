package com.test1;

//生产者和消费者，使用标志位和wait(),notify()对来完成
//生产者放一个，消费者拿一个，每进行一个操作改变标志位表示空/满的状态，并进入wait(),等待另一方结束它的放/拿操作，并唤醒自己

public class ProcuderConsumerGame {
    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        new Thread(new Producer(bucket)).start();
        new Thread(new Consumer(bucket)).start();
    }
}

final class Consumer implements Runnable {
    private Bucket bucket;

    public Consumer(Bucket bucket) {
        this.bucket = bucket;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            bucket.get();
        }
    }
}

final class Producer implements Runnable {
    private Bucket bucket;

    public Producer(Bucket bucket) {
        this.bucket = bucket;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            bucket.put((int) (Math.random() * 100));
        }
    }
}

class Bucket {
    private int packofBalls;
    private boolean available = false;

    public synchronized void get() {
        if (available == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("consumer got:" + packofBalls);
        available = false;
        notify();
        //return packofBalls;
    }

    public synchronized void put(int packofBalls) {
        if (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.packofBalls = packofBalls;
        available = true;
        System.out.println("Producer put:" + packofBalls);
        notify();
    }
}

