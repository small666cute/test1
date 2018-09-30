package com.lqbzTest;

public class TestString {
    public static void main(String[] args) {
        String str1="rc001";
        String str2="xxxxrc001";
        String str3="xxxxxxxrc002";
        System.out.println(str2.endsWith(str1));
        System.out.println(str3.endsWith(str1));
    }
}
