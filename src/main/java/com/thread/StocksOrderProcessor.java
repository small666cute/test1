package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

//用future对象取消任务
//还有疑问
public class StocksOrderProcessor {
    static final int MAX_NUMBER_OF_ORDERS = 10000;
    //使用ExecutorService创建一个大小为100的线程池
    static private ExecutorService executor = Executors.newFixedThreadPool(100);
    //创建一个ArrayList，存储返回的future们
    static private List<Future> ordersToProcess = new ArrayList<>();

    //一个实现callable的内部私有类，订单执行的业务逻辑
    private static class OrderExecutor implements Callable {
        int id = 0;
        int count = 0;

        //id表示订单编号，构造函数会接受这个Id
        public OrderExecutor(int id) {
            this.id = id;
        }

        public Object call() throws Exception {
            try {
                //这里计50次数，就是，每个订单（任务）做的事情就是循环50次，每次都休眠一段时间
                while (count < 50) {
                    count++;
                    //休眠一段随机时间，来模拟订单的处理时间，不过这个随机时间是怎么个生成法？？
                    Thread.sleep(new Random(System.currentTimeMillis() % 100).nextInt(10));
                }
                System.out.println("successfully executed order:" + id);
            } catch (Exception ex) {
                //如果有异常，把它throw到上一层
                throw (ex);
            }
            //返回Id来确认哪些订单没有执行完？（就是会打印这个id表示一下）
            return id;
        }
    }

    public static void main(String[] args) {
        System.out.printf("submitting %d trades %n", MAX_NUMBER_OF_ORDERS);
        //提交所有的订单
        for (int i = 0; i < MAX_NUMBER_OF_ORDERS; i++) {
            SubmitOrder(i);
        }
        //然后启动取消订单的线程
        new Thread(new EvilThread(ordersToProcess)).start();
        System.out.println("cancelling a few orders at random");
        try {
            //为了有足够时间结束所有待处理的订单（？），让处理器等待30秒
            //不然主线程结束了，还有很多订单（线程）没有在模拟器里面执行
            //不是特别懂
            //这个awaitTermination本来是一个检查线程池是否terminated，然后如果在指定时间内检测到线程池terminated的话（因为是在多线程的环境下，所有要考虑到一个方法的前后语句，可能由不同的线程执行，也可能同时在执行一个语句，有可能一个线程的循环条件是另一个线程来结束的），就返回true，不然一直检测，直到时间结束，就会返回false
            //这里这条语句的作用和sleep 30秒相同，返回的结果也始终都是false,因为没有别的线程来teminate这个线程池
            boolean flag=executor.awaitTermination(30, TimeUnit.SECONDS);
            System.out.println(flag);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("checking status before shutdown");
        int count = 0;
        //然后计数多少订单被取消了
        //好吧这个future什么都知道
        for (Future f : ordersToProcess) {
            if (f.isCancelled()) {
                count++;
            }
        }
        System.out.printf("%d trades cancelled %n", count);
        //停止executor，释放资源
        executor.shutdownNow();
    }

    //创建一个callable的实例，把这个实例提交到excutor运行，然后把返回的future对象放到存储的数组里面
    //注：返回future对象，不是是这个任务/订单已经完成了结束了，因为后面是向future对象发送取消请求的
    private static void SubmitOrder(int id) {
        Callable<Integer> callable = new OrderExecutor(id);
        ordersToProcess.add(executor.submit(callable));
    }
}

//这是取消订单的线程
class EvilThread implements Runnable {
    private List<Future> orderToProcess;

    //传入待处理的订单列表（都是future们的）
    public EvilThread(List<Future> futures) {
        this.orderToProcess = futures;
    }

    public void run() {
        Random myNextKill = new Random(System.currentTimeMillis() % 100);
        //发出100个取消请求，请求取消的订单Id是随机的
        for (int i = 0; i < 100; i++) {
            int index = myNextKill.nextInt(StocksOrderProcessor.MAX_NUMBER_OF_ORDERS);
            //调用future对象（就是get(index)拿到的）的cancel方法，就可以发生取消请求了，把cancel的参数设为true，表示任务可能会在执行过程中被中断（什么意思？？）(就是说这个参数为true的话，在任务已经在执行的情况下，会发送中断请求，如果这个参数为false的话，在任务已经执行的情况下，就让这个任务执行下去了）
            boolean cancel = orderToProcess.get(index).cancel(true);
            if (cancel) {
                System.out.println("cancel order succeeded: " + index);
            } else {
                System.out.println("cancel order failed: " + index);
            }
            //请求的间隔会休息一段时间
            try {
                Thread.sleep(myNextKill.nextInt(100));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
