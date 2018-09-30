package com.lqbzTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class TestT<T>{

    public  void test(T t){
        System.out.println(t);
    }

}
public class TestFanxingFanshe {
    public static void main(String[] args) {

        Class clzT = TestT.class;
        System.out.println(clzT);
        TestT<Integer> testT = new TestT<Integer>();
        System.out.println(testT.getClass());
        try {
            Method tMethod = clzT.getDeclaredMethod("test",Object.class);
            tMethod.setAccessible(true);
            try {
                tMethod.invoke(new TestT<Integer>(),1);
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }
}
