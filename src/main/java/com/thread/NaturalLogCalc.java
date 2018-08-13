package com.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
//计算一个ln里面的求和，有10项，分别用10个线程
//使用屏障器，barrier来等待所有的线程完成再继续计算
public class NaturalLogCalc {
    private static final int numberofTerms = 10;//需要计算10项，每一项都用一个线程去计算
    private static double[] termArray = new double[numberofTerms];//存储这10个数字
    private static final float x = 0.2f;//这是一个计算过程中的常量

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(numberofTerms, new Runnable() {//屏障,大概是通过看有numberofTerms(10)个线程在等待就打破屏障吧？
            //在屏障被打破后，即10个值都计算完成的时候，这个匿名类会被执行
            @Override
            public void run() {
                System.out.println("计算序列和.....");
                double sum = 0;
                for (double term : termArray) {
                    sum += term;//遍历数组，把数都加起来
                }
                System.out.println("ln (1-" + ")equals" + -sum);//把结果打印出来
            }
        });
        for (int i = 0; i < numberofTerms; i++) {
            new Thread(new TermCalc(i, barrier)).start();
        }
        System.out.println("等待汇合中。。。。。");
    }

    //每个计算的线程
    private static class TermCalc implements Runnable {
        private int termIndex;
        private CyclicBarrier barrier;

        public TermCalc(int termIndex, CyclicBarrier barrier) {
            this.termIndex = termIndex;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            double result = Math.pow(x, termIndex + 1) / (termIndex + 1);
            termArray[termIndex] = result;//把计算的值放在数组里
            System.out.println("term " + (termIndex + 1) + ": " + result);
            try {
                barrier.await();//然后等待
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (BrokenBarrierException ex) {
                ex.printStackTrace();
            }
        }
    }
}
