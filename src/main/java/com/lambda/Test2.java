package com.lambda;

import java.util.ArrayList;
import java.util.Collections;

public class Test2 {
}
//使用方法引用，在一个集合中找到最大值

//这是要进行找最大值的类
class MyClass{
    private int val;

    public MyClass(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}

class UseMethodRef{
    //这个方法与Comparator接口的compare()方法兼容，所以就相当于实现了compare()方法，然后传入一个Comparator接口类型的对象了
    static int compareMC(MyClass a,MyClass b){
        return a.getVal()-b.getVal();
    }

    public static void main(String[] args) {
        ArrayList<MyClass> a1=new ArrayList<MyClass>();
        a1.add(new MyClass(1));
        a1.add(new MyClass(2));
        a1.add(new MyClass(3));
        //调用Collections.max方法得到一个集合的最大值，传入集合a1，和相当于Comparator接口实现的方法引用
        MyClass maxValObj = Collections.max(a1,UseMethodRef::compareMC);
        System.out.println("maximum value is :"+maxValObj.getVal());
    }
}
