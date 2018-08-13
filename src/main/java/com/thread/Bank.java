package com.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
//使用信号量Semaphore来进行资源的申请和释放，资源的数目通过Semaphore来设置
public class Bank {
    private final static int COUNT = 100;//控制客户访问的数目
    private final static Semaphore semaphore = new Semaphore(2,true);//信号量，第一个参数是许可的数目，第二个是表示先到先得（FIFO），falsed的话则不一定

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {  //客户100个循环
            final int count = i;//为什么不按顺序！！！！！？：因为这里启动线程的顺序不一定是从0-顺序排列，所以申请许可的顺序也不是顺序的
            new Thread(){
                @Override
                public void run() {
                    try{
                        if (semaphore.tryAcquire(10,TimeUnit.MILLISECONDS)){//线程试图获得许可，设置客户等待的时间
                            try {//如果获取不到则放弃（离开），如果获取就请求服务
                                Teller.getService(count);//count表示当前迭代的次数
                            }finally {
                                semaphore.release();//释放许可
                            }
                        }
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
class Teller{
    static public void getService(int i){
        try{
            System.out.println("serving: "+i);//打印当前迭代的次数，即表示当前执行的线程的Id
            Thread.sleep((long) (Math.random()*5));//模拟占用时间
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
