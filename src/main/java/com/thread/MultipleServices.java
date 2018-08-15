package com.thread;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//要计算m的n次方的值
public class MultipleServices {
    //任务
    public static class Exp implements Callable {
        private double m;
        private int n;

        public Exp(double m, int n) {
            this.m = m;
            this.n = n;
        }

        //就是计算m的n次方
        public Double call() {
            double result = 1;
            for (int i = 0; i < n; i++) {
                result *= m;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            //for循环结束就是计算完了，打印一条消息表示计算完了，然后返回结果
            System.out.printf("%ncomputed %.02f raised to %d%n", m, n);
            return result;
        }
    }

    public static void main(String[] args) {
        //创建这个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(100);
        //然后弄个数组存储callable任务
        ArrayList<Callable<Double>> tasks = new ArrayList<Callable<Double>>();
        //然后用个循环，随机生成10对m,n，创建每个任务并把任务放到数组里
        for (int i = 0; i < 1000; i++) {
            double m = Math.random() * 10;
            int n = (int) (Math.random() * 1000);
            System.out.printf("created task for computing: " + "%.02f raised to %d \n", m, n);
            tasks.add(new Exp(m, n));
        }
        //把之前的executor作为参数，创建ExecutorCompletionService实例，这样这个service实例就能检测这个executor里的任务执行情况了
        ExecutorCompletionService service = new ExecutorCompletionService(executor);
        //然后把数组里的任务都拿出来，提交给执行器
        for (Callable<Double> task : tasks) {
            service.submit(task);
        }
        Lock lock = new ReentrantLock();
        //循环检测是否有任务结束，直到拿到tasks.size数量（10个）的，也就是拿到所有的结果为止
        for (int i = 0; i < tasks.size(); i++) {
            try {
                //进入同步代码块
                lock.lock();
                //service实例的take方法会等待第一个返回的任务结果，有的话就get到了
                Double d = (Double) service.take().get();
                //为了直观，拿到值的同时把它打印处理
                System.out.printf("第"+i+"个"+"result:%E%n", d);
                lock.unlock();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                System.out.println("task execution 发送错误");
            }
        }
        //因为上面那个循环会拿到所有的结果，所以这儿所有的任务就应该执行结束了，所有就可以把执行器关了？好像也并不是？因为shutdown只是确保不接受新任务。那这儿所有的任务结束了吗？
        executor.shutdownNow();
    }
}
