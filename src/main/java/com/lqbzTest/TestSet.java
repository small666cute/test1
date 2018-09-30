package com.lqbzTest;

import java.util.HashSet;

public class TestSet {
    public static void main(String[] args) {
        HashSet<Integer> test=new HashSet<>();
        test.add(1);
        test.add(1);
        test.remove(1);
        for (Integer i:test) {
            System.out.println(i);
        }
    }
}
