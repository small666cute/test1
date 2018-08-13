package com.thread;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;
//用移相器实现一个赛马
//等到马都准备好了，才将它们一起放行
//移相器可以重复使用是个什么意思？
//没怎么懂
public class HorseRace {
    private final int NUMBER_OF_HORSES = 12;//马的最大数量
    private final static int INIT_PARTIES = 1;//初始的参与者（马），1个
    private final static Phaser manager = new Phaser(INIT_PARTIES);//根据初始值创建移相器，manager为移相器实例

    public static void main(String[] args) {
        Thread raceMonitor = new Thread(new RaceMonitor());//racemonitor 监视已经到达起跑地点的马的数量
        raceMonitor.setDaemon(true);
        raceMonitor.start();
        new HorseRace().mangerRace();//调用自己的mangerRace方法
    }

    public void mangerRace() {
        ArrayList<Horse> horseArray = new ArrayList<Horse>();//数组存储horse对象
        for (int i = 0; i < NUMBER_OF_HORSES; i++) {
            horseArray.add(new Horse());
        }
        runRace(horseArray);//开始比赛，参数为马的数组
    }

    private void runRace(Iterable<Horse> team) {
        log("等所有的马都到了，就开始跑了");
        for (final Horse horse : team) {
            final String dev = horse.toString();//字符串，马的Id
            log("assign " + dev + "to the race");
            manager.register();//使用移相器的register方法，注册每一匹马
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((new Random()).nextInt(1000));//等一个随机时间
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    log(dev + ",等一下别的马儿");
                    manager.arriveAndAwaitAdvance();//用移相器的arriveandawaitadvance，开始等别的
                    horse.run();
                }
            }.start();
        }
        try {
            Thread.sleep(1000);//所有的都到达后，主线程休息1秒
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        log("所有的马都到了，开始跑");
        manager.arriveAndDeregister();//释放每个马，开始跑，开始。这个移相器可以再次使用，？
    }

    private static void log(String msg) {
        System.out.println(msg);
    }

    private static class Horse implements Runnable {
        private final static AtomicInteger idSource = new AtomicInteger();//产生一个Id,这是一个竞争变量，这是一个static的变量，是类的
        private final int id = idSource.incrementAndGet();//原子递增

        @Override
        public void run() {
            log(toString() + ":跑");//打印 马+id+跑
        }

        @Override
        public String toString() {
            return "马儿" + id;
        }
    }

    private static class RaceMonitor implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("有几匹马已经准备好了:" + HorseRace.manager.getArrivedParties());//周期性的打印已经到达起跑线的马数量
                try {
                    Thread.sleep(1);//休息一会继续打印
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
