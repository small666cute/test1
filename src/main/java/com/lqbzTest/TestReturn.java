package com.lqbzTest;

//看三目运算符怎么用（怎么会有这么呆的问题。。）
public class TestReturn {
    public static void main(String[] args) {
        Integer temp = testBoolean(new Boolean(false));
        System.out.println(temp);
    }

    static Integer testBoolean(Boolean b) {
        return b ? new Integer(1) : new Integer(2);
    }
}
