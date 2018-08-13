package com.String;

//这是在看对象和字符串传参，学习split的用法
public class TestString {
    public static void main(String[] args) {
        /*A a=new A(1,"1");
        System.out.println(a);
        test(a);
        System.out.println(a);*/
        String a = "1,2";
        //a=a.split(",")[0];
        String b=a.split(",")[1];
        System.out.println(b);
        /*System.out.println(a);
        test2(a);
        System.out.println(a);*/
    }

    static void test(A a) {
        a.setA(2);
        a.setB("2");
    }

    static void test2(String str) {
        str.split(",");
    }
}

class A {
    int a;
    String b;

    public A() {
    }

    public A(int a, String b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b='" + b + '\'' +
                '}';
    }
}
