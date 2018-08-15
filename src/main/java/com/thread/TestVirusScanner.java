package com.thread;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.*;

public class TestVirusScanner {
}

//使用ScheduledExecutorService这个类达到定时执行任务的目的
//注意这个例子里面仅仅提交了一个任务，所以只有一个任务在定时执行，也只返回了一个future对象
//不过这个例子已经被我改乱了
class VirusScanner {
    private static JFrame appFrame;
    private static JLabel statusString;
    private int scanNumber = 0;
    //ScheduledExecutorService，这个可以定时执行的线程池
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private GregorianCalendar calendar = new GregorianCalendar();
    private static VirusScanner app = new VirusScanner();

    //scanDisk方法是实际的任务操作
    public void scanDisk() {
        //这个runnable显示窗口，然后sleep()一会假装在扫描病毒，然后窗口消失，表示这次扫描已经结束了
        class Scanner implements Runnable {
            int i;

            public Scanner(int i) {
                this.i = i;
            }

            @Override
            public void run() {
                try {
                    //开始显示窗口
                    appFrame.setVisible(true);
                    scanNumber++;
                    System.out.println(Thread.currentThread().getName() + " " + "scanNumber:"+scanNumber+" scanner:"+i);
                    Calendar cal = Calendar.getInstance();
                    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
                    //在窗口中显示当前时间和第几个scan
                    statusString.setText(" scan " + scanNumber + "started at " + df.format(cal.getTime())+"scanner:"+i);
                    //1000+一个随机的数，表示这个窗口至少显示1秒，
                    Thread.sleep(1000 + new Random().nextInt(10000));
                    //窗口消失
                    appFrame.setVisible(false);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        Runnable scanner1 = new Scanner(1);
        Runnable scanner2 = new Scanner(2);
        final Runnable test = new Runnable() {
            private int i = 0;

            @Override
            public void run() {
                i++;
                System.out.println(Thread.currentThread().getName() + " test:" + i);
            }
        };
        //scheduleAtFixedRate方法指定任务按照一定的频率执行，就运行上面那个runnable里面的事情，
        //表示任务会最初延迟1秒，然后每隔15秒运行一次
        //返回一个future对象，用于后面取消任务
        final ScheduledFuture<?> scanTest = scheduler.scheduleAtFixedRate(test, 1, 10, TimeUnit.SECONDS);
        final ScheduledFuture<?> scanManger = scheduler.scheduleAtFixedRate(scanner1, 1, 5, TimeUnit.SECONDS);
        final ScheduledFuture<?> scanManger2 = scheduler.scheduleAtFixedRate(scanner2, 1, 15, TimeUnit.SECONDS);
        //schedule方法的3个参数：callable或runnable的接口，延迟时间，时间的单位，返回一个Future对象
        //会按照延迟时间执行callable或runnable的接口里指定的任务，
        //这个runnable是执行取消操作的，它在60秒之后运行一次
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                //取消任务，关闭调度器，关闭窗口
                scanManger.cancel(true);
                try{
                Thread.sleep(2000);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
                scheduler.shutdown();
                appFrame.dispose();
            }
        }, 20, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        appFrame = new JFrame();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        appFrame.setSize(400, 70);
        appFrame.setLocation(dimension.width / 2 - appFrame.getWidth() / 2, dimension.height / 2 - appFrame.getWidth() / 2);
        statusString = new JLabel();
        appFrame.add(statusString);
        appFrame.setVisible(false);
        //调用这个方法之后，主线程就结束了，但scandisk方法里面创建的线程会运行下去直到它们运行结束
        app.scanDisk();
    }
}
