package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

//使用交换器exchanger
//生产者生产一些东西放到篮子里，放满了就请求和空篮子交换一下，交换的结果是得到一个空篮子，
//而消费者从篮子里拿东西，篮子是空的话就请求交换满篮子，交换的结果是得到一个满篮子
//这个交换会不停的循环进行下去，直到外界停止
//请求交换的过程中，线程会进入等待状态
public class ProductorExchange {
    //创建一个实例exchanger,操作对象为整型列表并且因为这个声明为public  static 所以这个exchanger是属于类的，所以
    // 不用创建productorExhange的实例就可以访问它了
    public static Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer2());
        Thread consumer = new Thread(new Consumer2());
        producer.start();
        consumer.start();
        //当用户输入enter的时候终止这两个进程
        try {
            while (System.in.read() != '\n') {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //停止正在运行的线程的很好的方式。向线程发送中断请求，线程会自己决定什么时候停止（不一定会立刻停止，一般是做完事情停止）
        //？那用标志位的话不是会立刻停止吗？
        producer.interrupt();
        consumer.interrupt();
    }
}

class Producer2 implements Runnable {
    private static List<Integer> buffer = new ArrayList<Integer>();//保存整型的缓冲区
    private boolean okToRun = true;
    private final int BUFFSIZE = 10;

    public void run() {
        int j = 0;
        //仍然是无限循环，当main线程发来中断，把标志为重置，停止循环
        while (okToRun) {
            //如果缓冲区是空的，就添加随机数，直到填满
            if (buffer.isEmpty()) {
                try {
                    for (int i = 0; i < BUFFSIZE; i++) {
                        buffer.add((int) (Math.random() * 100));//填入随机数
                    }
                    Thread.sleep((int) (Math.random() * 1000));//随机休息，模拟生产填充需要时间的情况
                    //向用户展示（打印）一下缓冲区的内容
                    System.out.println("producer buffer:");
                    for (int i : buffer) {
                        System.out.print(i + ", ");
                    }
                    System.out.println();
                    System.out.println("交换。。。。。");
                    //填完了，请求与交换器进行一次交换，传入的参数是满的缓冲区，返回的是一个空的缓冲区
                    //请求之后，此线程会等待另一个线程到达交换点，交换必须两个线程都准备好了
                    buffer = ProductorExchange.exchanger.exchange(buffer);
                } catch (InterruptedException ex) {
                    okToRun = false;
                }
            }
        }
    }
}

class Consumer2 implements Runnable {
    private static List<Integer> buffer = new ArrayList<Integer>();
    private boolean okToRun = true;

    public void run() {
        while (okToRun) {
            try {
                if (buffer.isEmpty()) {
                    //buffer是空的话，就请求发生交换
                    buffer = ProductorExchange.exchanger.exchange(buffer);
                    //交换之后，打印一下收到的buffer，表示收到了
                    System.out.print("consumer buffer");
                    for (int i : buffer) {
                        System.out.print(i + ", ");
                    }
                    System.out.println("\n");
                    //休息一个时间，模拟消费者需要一段时间完成取的过程
                    Thread.sleep((int) (Math.random() * 1000));
                    //然后清空缓冲区
                    buffer.clear();
                }
            } catch (InterruptedException ex) {
                okToRun = false;
            }
        }
    }
}
