package com.fanxing;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        System.out.println(l1.getClass().getCanonicalName());
        System.out.println(l2.getClass().getCanonicalName());
        System.out.println(l1.getClass() == l2.getClass());

    }
}
