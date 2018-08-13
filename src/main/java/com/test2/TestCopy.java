package com.test2;

//练习深拷贝和浅拷贝的，可是我还是没懂。。。
public class TestCopy {
    public static void main(String[] args) {
        Age a = new Age(0);
        A test1 = new A(a, null);
        A test2 = new A(test1);
        //A test2 = test1;
        System.out.println("test1:" + test1);
        System.out.println("test2:" + test2);
        //test1.a =new Age(2);
        a.setAge(2);
        test1.b = "2";
        System.out.println("test1: " + test1);
        System.out.println("test2: " + test2);
    }

}

class A {
    Age a;
    String b;

    public A(Age a, String b) {
        this.a = a;
        this.b = b;
    }

    public A(A test) {
        this.a = new Age(test.a.getAge());
        this.b = test.b;
    }

    @Override
    public String toString() {
        return "a:"+this.a +" b:"+ this.b;
    }
}

class Age {
    int age;

    public Age(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.valueOf(age) ;
    }
}
