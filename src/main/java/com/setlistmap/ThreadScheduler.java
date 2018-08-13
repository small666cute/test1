package com.setlistmap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//使用优先级队列,这个队列是逐步的进行排序的，因此数据插入队列后打印出来是无序的，
//而一边移除一边打印则是有序的
public class ThreadScheduler {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 10));//这些随机数字0-9表示了优先级
        }
        PriorityQueue<Integer> threadQueue = new PriorityQueue<>();
        //把列表里的元素都加到队列中
        threadQueue.addAll(list);
        System.out.println("等待的线程.....");
        //打印队列中的线程
        for (Integer thread : threadQueue) {
            System.out.print(thread + ",");
        }
        System.out.println("\ndeploying threads......");
        //移除队列最前面的线程，并打印
        while (!threadQueue.isEmpty()) {
            System.out.print(threadQueue.remove() + ",");
        }
    }
}
