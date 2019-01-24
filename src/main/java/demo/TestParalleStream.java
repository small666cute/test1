package demo;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

//还是不是很明白
public class TestParalleStream {
    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static List<Integer> list4 = Collections.synchronizedList(Lists.newArrayList());
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        /*IntStream.range(0, 10000).forEach(list1::add);

        IntStream.range(0, 10000).parallel().forEach(list2::add);

        IntStream.range(0, 10000).parallel().forEach(list4::add);

        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });
*/
        List<Integer> list=Lists.newArrayList();
        for(int i=0;i<2000;i++){
            for(int j=0;j<2000;j++){
                list.add(j);
            }
        }

        List<Integer> stringList =Lists.newArrayList();
        List<Integer> stringList2 =Lists.newArrayList();
        List<Integer> stringList3 =Lists.newArrayList();
        list.parallelStream().forEach(value ->{
            containThenADD(stringList,value);
            containThenADD(stringList2,value);
            containThenADD(stringList3,value);
        });
        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());
        System.out.println("list4的大小：" + list4.size());

        System.out.println("stringList的大小：" + stringList.size());
        System.out.println("stringList2的大小：" + stringList2.size());
        System.out.println("stringList3的大小：" + stringList3.size());

    }
    private static void containThenADD(List<Integer> list,Integer value){
        if(!list.contains(value)){
            list.add(value);
        }
    }
}
