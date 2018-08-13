package com.thread;

import java.util.Random;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
//生产者产生一个Lucky number，消费者取，生产者一个，消费者10个
//使用的是linked transfer queue，消费者负责take(),生产者判断是否hasWaitingConsumer()，有就
//transfer(),传输的是个字符串，消费者得到之后把这个字符串给打印出来
public class LuckyNumberGenerator {
    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<>();//数据交换的队列
        Thread producer = new Thread(new Producer(queue));//弄1个producer进程
        producer.setDaemon(true);
        producer.start();
        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread(new Consumer(queue));//弄10个consumer进程
            consumer.setDaemon(true);
            consumer.start();
            try {
                Thread.sleep(1000);//停2秒，再创建下一个
            } catch (InterruptedException ex) {
            }
        }
        //因为把消费者进程设置为守护进程了，所以main进程结束生产者进程就结束了，而生产者进程是没有自己的结束机制，一直
        // 不停的跑。所以当生产者进程内部等待时间（sleep）长
        //了，而消费者进程创建的间隔短，就可能发生：消费者进程已经全部创建完毕并启动了，main进程已经结束了，而生产者进程
        //还在sleep的情况，就不会打印出所有的值了。可以适当设置两个sleep时间，或者不把生产者设置为守护进程，在生产者进程
        //里面设定结束机制。或者在main进程里面再sleep一会。
        /*try {
            Thread.sleep(10000000);//停2秒，再创建下一个
        } catch (InterruptedException ex) {
        }*/
    }
}

class Producer implements Runnable {
    private final TransferQueue<String> queue;

    public Producer(TransferQueue<String> queue) {
        this.queue = queue;
    }

    private String produce() {
        return "你的幸运数字是" + (new Random().nextInt(100));//这个是要放进队列的
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (queue.hasWaitingConsumer()) {//判断是否有等待的consumer
                    queue.transfer(produce());//传递给consumer
                }
                TimeUnit.SECONDS.sleep(5);//等一秒
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private final TransferQueue<String> queue;

    public Consumer(TransferQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("consumer" + Thread.currentThread().getName() + queue.take());//拿到上面传来的数据，take
            //会触发队列内部一些标记值的改变，这样生产者就知道是否有consumer在等待了
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}