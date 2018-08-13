package com.test1;
//有两个桶，把球从一个桶移到另一个桶，两个方向都有
//如果线程同步的话，应该球的总数量是不变的
//这里用的是对一个方法synchronized
//使用一个标志量表示球转移的方向，因此只用了一个线程类
public class BucketBallGame {
    private int bucket[] = {10000, 10000};//两个桶，分别有10000个球
    private static boolean RIGHT_TO_LEFT;//表示球转移的方向

    public static void main(String[] args) {

        new BucketBallGame().doTransfers();
    }

    //分别创建10个实例，把球左到右，和右到左
    private void doTransfers() {
        for (int i = 0; i < 10; i++) {
            new Thread(new TransferThread(!RIGHT_TO_LEFT)).start();
            new Thread(new TransferThread(RIGHT_TO_LEFT)).start();
        }
    }

    //数量操作，+一个数字，-一个数字，并打印两个加起来的球总数
    public synchronized void transfer(boolean direction, int numToTransfer) {
        if (direction == RIGHT_TO_LEFT) {
            bucket[0] += numToTransfer;
            bucket[1] -= numToTransfer;
        } else {
            bucket[0] -= numToTransfer;
            bucket[1] += numToTransfer;
        }
        System.out.println("total:" + (bucket[0] + bucket[1]));
    }

    //线程类
    private class TransferThread implements Runnable {
        private boolean direction;

        public TransferThread(boolean direction) {

            this.direction = direction;
        }

        //做100次转移操作
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                transfer(direction, (int) (Math.random() * 2000));//要加减的方向和数字(0-1999)
                try {
                    Thread.sleep((int) (Math.random() * 100));
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
