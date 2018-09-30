package com.thread;

import java.text.DateFormatSymbols;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

//这是一个计算矩阵和的程序
//用到了callable(和runnable相似,但可以返回值，而且不能直接通过thread运行，它需要excutor来
//运行）。exeutor（用来运行callable对象，并返回一个future对象）。future(可以用来得到callable计算得到的值）
//这里用到了线程池，不过我还没有学到
//并没有怎么懂
public class AnnualSalesCalc {
    private static int NUMBER_OF_CUSTOMERS = 100;//顾客数，矩阵的行数
    private static int NUMBER_OF_MONTHS = 12;//月数，矩阵的列数
    private static int salesMatrix[][];//声明二维数组

    //这是个计每行和的线程，因为要被main调用，所以声明为static,实现了callable接口
    //summer类是AnnualSalesCalc的内部类
    private static class Summer implements Callable {
        private int companyID;//这个是类变量吗?这个不是啊，这个跟类变量没有关系吧

        //构造函数，接受客户id的值，并存起来
        public Summer(int companyID) {
            this.companyID = companyID;
        }

        //因为实现了 Callable 接口，所有要实现call方法，call返回的是一个泛型类
        public Integer call() {
            int sum = 0;
            //用一个循环计算一行的和，把指定companyid的所有月份加起来
            for (int col = 0; col < NUMBER_OF_MONTHS; col++) {
                sum += salesMatrix[companyID][col];
            }
            //打印消息，表示求行和结束，随时都可以返回计算结果
            System.out.printf("totaling for client %02d completed%n", companyID);
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        generateMatrix();//生成矩阵
        printMatrix();//打印矩阵
        //执行者服务，用来调用callable对象
        //newfixedthreadpool是excutor的类静态方法，接受一个整型参数，表示创建的线程数目，它会创建固定的线程池，返回一个executorService实例
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //声明一个set对象，存储future对象，这个用来监控提交的任务
        //future对象接受一个参数，表示数据类型，这里是integer
        //future对象返回一个integer对象（与接受的参数类型相同）给调用方
        Set<Future<Integer>> set = new HashSet<Future<Integer>>();
        //给每一行都实例summer对象，summer对象实现了callable接口
        for (int row = 0; row < NUMBER_OF_CUSTOMERS; row++) {
            //summer类实现了callable接口
            //row行数，就代表了companyid
            Callable<Integer> callable = new Summer(row);
            //用excutor对象的submit方法，来运行这个summer对象（也就是callable对象）
            //submit：1、将callable对象提交给线程池的某个线程
            //2、返回一个future对象
            //summer类里面的call()方法里面做的事情会异步执行，即：加一行的操作会异步执行
            //把下面这个submit这一行注释掉，试试新方法futuretask
            //Future<Integer> future = executor.submit(callable);
            FutureTask<Integer> future = new FutureTask<Integer>(callable);
            //future.run();
            //或者不用future.run();,而是
            executor.submit(future);
            //把future对象放到set集合里面
            set.add(future);
        }
        //计算总和
        int sum = 0;
        for (Future<Integer> future : set) {
            //future对象的get方法，用来获取计算的结果，结果很可能是完全无序的（因为这个set是无序的）（这个问题和18章的哪个问题类似了？）
            try{
            sum += future.get();//ei这儿好奇怪，为什么catch也行，不catch也行？
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
        System.out.printf("%nthe annual turnover (bags): %s%n%n", sum);
        //关闭执行者服务，释放分配的资源
        executor.shutdown();
    }

    //创建二维数组（矩阵），数组的元素为0-99的随机数
    private static void generateMatrix() {
        salesMatrix = new int[NUMBER_OF_CUSTOMERS][NUMBER_OF_MONTHS];
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
                salesMatrix[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    //遍历矩阵，并且打印
    private static void printMatrix() {
        System.out.println("\t\t");//制表
        String[] monthDisplayNames = (new DateFormatSymbols()).getShortMonths();//打印月份
        for (String strNames : monthDisplayNames) {
            System.out.printf("%8s", strNames);
        }
        System.out.printf("%n%n");
        //逐行打印，每行前面都有一个cliend id
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            System.out.printf("client id:%02d", i);
            for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
                System.out.printf("%8d", salesMatrix[i][j]);
            }
            System.out.println();
        }
        System.out.printf("%n%n");
    }
}
