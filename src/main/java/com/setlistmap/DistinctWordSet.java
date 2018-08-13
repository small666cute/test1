package com.setlistmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

//hashset
public class DistinctWordSet {
    public static void main(String[] args) {
        int count = 0;
        Set<String> words = new HashSet<>();
        //Scanner类用于读取键盘输入
        Scanner in = new Scanner(System.in);
        String str;
        //从用户输入中读取多个名字，直到用户输入空
        //in.nextLine()返回一个字符串
        while (!(str = in.nextLine()).equals("")) {
            count++;
            //把输入的字符串添加到set里面，如果出现重复的，add会拒绝添加，但不会提示什么
            words.add(str);
        }
        System.out.println(".....");
        System.out.println("总共有这么多词了：" + count);
        System.out.println("不一样的词有这么多：" + words.size());
        System.out.println("....");
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
