package com.fanshe;

import java.lang.reflect.Field;

class A{
    int a;
}
public class Test3 {
    public static void main(String[] args) {
        A test_a=new A();
        test_a.a=10;
        System.out.println("test_a.q= "+test_a.a);
        Class c=A.class;
       /* try{
            Field field_a = c.getField("a");
            int ra =field_a.getInt(test_a);
        }*/
    }
}
