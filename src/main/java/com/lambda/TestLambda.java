package com.lambda;

//练习lambda
public class TestLambda {
}

//一个函数式接口
interface MyNumber {
    Double getValue();
}

interface NumericTest {
    boolean test(int n);
}

class lambdaDemo {
    public static void main(String[] args) {
        MyNumber myNum;
        //必须返回的值是double/Double（和上面的要实现的抽象方法兼容),
        myNum = () -> 123.45;
        System.out.println("()->123.45:" + myNum.getValue());
        myNum = () -> Math.random() * 100;
        System.out.println("()->Math.random()*100:" + myNum.getValue());
        System.out.println("()->Math.random()*100:" + myNum.getValue());
        /*myNum =() -> new Integer(123);
        System.out.println("() -> new Integer(123):"+myNum.getValue());*/
    }
}

class lambdaDemo2 {
    public static void main(String[] args) {
        NumericTest isEven = (n) -> (n % 2) == 0;
        if (isEven.test(10)) System.out.println("10 is even");
        if (!isEven.test(9)) System.out.println("9 is not even");
        NumericTest isNonNeg = (n) -> n >= 0;
        if (isNonNeg.test(1)) System.out.println("is is non-negative");
        if (!isNonNeg.test(-1)) System.out.println("-1 is negative");
    }
}
