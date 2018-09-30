package com.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

//数组多线程排序
//不是很明白
public class ParalleMergeSort {
    private static ForkJoinPool threadPool;
    private static final int THRESHOLD = 16;

    //这个方法用于把数组排序
    private static void sort(Comparable[] objectArray) {
        //创建一个临时数组，大小和待排序数组相同
        Comparable[] destArray = new Comparable[objectArray.length];
        //创建一个sortTask对象，调用invoke方法，把它提交给线程池，这个线程池是在main函数里创建的那个线程池
        //sortTask的4个参数：待排序数组，排序后数组，待排序数组的开始和结尾索引
        threadPool.invoke(new SortTask(objectArray, destArray, 0, objectArray.length - 1));
    }

    //因为这个排序任务不需要返回一个值，所以继承RecursiveAction类，如果比如是计算数列和的就应该继承RecursiveTask
    static class SortTask extends RecursiveAction {
        private Comparable[] sourceArray;
        private Comparable[] destArray;
        private int lowerIndex, upperIndex;

        public SortTask(Comparable[] sourceArray, Comparable[] destArray, int lowerIndex, int upperIndex) {
            this.sourceArray = sourceArray;
            this.lowerIndex = lowerIndex;
            this.destArray = destArray;
            this.upperIndex = upperIndex;
        }

        //重写父类的compute()方法
        //检测待排序元素（数组）的大小，如果太小了（预先定义一个阈值，这里是16），就直接调用insertionSort排序
        @Override
        protected void compute() {
            if (upperIndex - lowerIndex < THRESHOLD) {
                insertionSort(sourceArray, lowerIndex, upperIndex);
                return;
            }
            //如果超过阈值，就创建两个子任务，递归进行调用，一个子任务接受原来数组的一半，作为自己的源数组（待排序数组）
            //这个是数组中点
            int midIndex = (lowerIndex + upperIndex) >>> 1;
            //这是两个子任务，分别处理源数组的前一半和后一半，调用invokeAll提交这两个任务线程池
            //然后这个里面又会检测数组大小，这样不断分解成小任务
            invokeAll(new SortTask(sourceArray, destArray, lowerIndex, midIndex), new SortTask(sourceArray, destArray, midIndex + 1, upperIndex));
            //调用这个方法，然后？（留给自己练习）
            merge(sourceArray, destArray, lowerIndex, midIndex, upperIndex);
        }
    }

    private static void merge(Comparable[] sourceArray, Comparable[] destArray, int lowIndex, int midIndex, int upperIndex) {
        if (sourceArray[midIndex].compareTo(sourceArray[midIndex + 1]) <= 0) {
            return;
        }
        //把sourceArray拷到destArray里，总共拷前一半
        System.arraycopy(sourceArray, lowIndex, destArray, lowIndex, midIndex - lowIndex + 1);
        int i = lowIndex;
        int j = midIndex + 1;
        int k = lowIndex;
        while (k < j && j <= upperIndex) {
            if (destArray[i].compareTo(sourceArray[j]) <= 0) {
                sourceArray[k++] = destArray[i++];
            } else {
                sourceArray[k++] = sourceArray[j++];
            }
        }
        System.arraycopy(destArray, i, sourceArray, k, j - k);
    }

    private static void insertionSort(Comparable[] objectArray, int lowerIndex, int upperIndex) {
        for (int i = lowerIndex + 1; i <= upperIndex; i++) {
            int j = i;
            Comparable tempObject = objectArray[j];
            while (j > lowerIndex && tempObject.compareTo(objectArray[j - 1]) < 0) {
                objectArray[j] = objectArray[j - 1];
                --j;
            }
            objectArray[j] = tempObject;
        }
    }

    //生成大小元素大小在0——1000之间的数组
    public static Double[] createRandomData(int length) {
        Double[] data = new Double[length];
        for (int i = 0; i < data.length; i++) {
            data[i] = length * Math.random();
        }
        return data;
    }

    public static void main(String[] args) {
        //获取当前运行代码的机器上可用的处理器的数目
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("number of processors: " + processors);
        //然后创建大小等于处理器数目的线程池，实例化ForkJoinPool
        threadPool = new ForkJoinPool(processors);
        //然后产生一个随机数组
        Double[] data = createRandomData(1000);
        //打印未排序的初始数组
        System.out.println("original unsorted data:");
        for (Double d : data) {
            System.out.printf("%3.2f ", (double) d);
        }
        //排序这个数组，升序
        sort(data);
        //打印排序后的数组
        System.out.println("\n\nSorted array:");
        for (Double d : data) {
            System.out.printf("%3.2f ", d);
        }
    }
}
