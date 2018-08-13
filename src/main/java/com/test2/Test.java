package com.test2;

public class Test{
    public static void main(String[] args) {
        test3();
        test1();
    }
    public static void test3(){
        String s0="helloworld";
        String s1="helloworld";
        String s2="hello"+"world";
        System.out.println("===========test3============");
        System.out.println(s0==s1); //true 可以看出s0跟s1是指向同一个对象
        System.out.println(s0==s2); //true 可以看出s0跟s2是指向同一个对象
    }
    public static void test1() {
        String a = "hello2";
        //final String b = "hello";
        String d = "hello";
        //String c = b + 2;
        String e = d + 2;
        String f="hello"+2;
        //System.out.println((a == c));
        System.out.println((a == e));
        System.out.println((a==f));
    }
}
/*public class Test {
    public static void main(String[] args) {
        MyClass myClass1 = new MyClass();
        MyClass myClass2 = new MyClass();
        System.out.println(myClass1.i);
        System.out.println(myClass1.j);
        System.out.println(myClass2.i);
        System.out.println(myClass2.j);

    }
}*/

class MyClass {
    public final double i = Math.random();
    public static double j = Math.random();
}

/*
public class Test {
    public static void main(String[] args) {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        String f="hello"+2;
        System.out.println((a == c));
        System.out.println((a == e));
        System.out.println((a==f));
    }
}
*/

/*public class Test {
    public static void main(String[] args)  {
        final TestFinal test=new TestFinal(1,"1");
        System.out.println(test);
        test=testFinal(test);
        System.out.println(test);

    }
    public static TestFinal testFinal(TestFinal test2){
        test2 = new TestFinal(1,"1");
        return test2;
    }
}*/
class TestFinal{
    Integer a;
    String b;

    public TestFinal(Integer a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return String.valueOf(a)+b;
    }
}
