package com.lqbzTest;

public class TestFanxing {
    public static <T> T test(T t) {
        return t;
    }

    public static void main(String[] args) {
        System.out.println(test("str"));
    }
}
