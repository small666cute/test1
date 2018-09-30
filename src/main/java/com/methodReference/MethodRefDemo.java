package com.methodReference;

public class MethodRefDemo {
    public static String stringOp(StringFunc sf,String s){
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "lambda";
        String outStr=stringOp(MyStringOps::strReverse,inStr);

    }
}
